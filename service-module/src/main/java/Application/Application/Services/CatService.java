package Application.Application.Services;

import Application.Abstractions.Repositories.ICatRepository;
import Application.Contracts.ICatService;
import Application.Entities.CatEntity;
import Application.Entities.PersonEntity;
import Application.EntityMappers.CatEntityMapper;
import Application.EntityMappers.PersonEntityMapper;
import Application.Models.Cat;
import Application.Models.CatColor;
import Application.Models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@ComponentScan(basePackages = "Application.Abstractions.Repositories")
@Transactional
public class CatService implements ICatService {
    private final ICatRepository catRepository;

    public CatService(@Autowired ICatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @Override
    public CompletableFuture<Void> makeFriends(UUID catOneId, UUID catTwoId) {
        Cat firstCat = getCatById(catOneId).join();
        Cat secondCat = getCatById(catTwoId).join();
        CatEntity firstCatEntity = CatEntityMapper.mapToEntity(firstCat);
        CatEntity secondCatEntity = CatEntityMapper.mapToEntity(secondCat);
        return CompletableFuture.runAsync(() -> {
            try {
                if (catRepository.findById(firstCatEntity.getCatsId()).isEmpty() || catRepository.findById(secondCatEntity.getCatsId()).isEmpty()) {
                    throw new IllegalArgumentException("Cat not found");
                }

                firstCatEntity.addFriend(secondCatEntity);
                secondCatEntity.addFriend(firstCatEntity);

                CompletableFuture.runAsync(() -> catRepository.save(firstCatEntity));
                CompletableFuture.runAsync(() -> catRepository.save(secondCatEntity));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public CompletableFuture<List<Cat>> getCatsByColor(CatColor color) {
        CompletableFuture<List<CatEntity>> catsEntities = CompletableFuture.supplyAsync(() -> catRepository.findByColor(color));

        return catsEntities.thenApply(catEntities -> catEntities.stream()
                .map(CatEntityMapper::mapToModel)
                .collect(Collectors.toList()));
    }

    @Override
    public CompletableFuture<Void> changeOwner(Cat cat, Person newOwner) {
        CatEntity catEntity = CatEntityMapper.mapToEntity(cat);
        PersonEntity personEntity = PersonEntityMapper.mapToEntity(newOwner);
        return CompletableFuture.runAsync(() -> {
            try {
                catEntity.setOwner(personEntity);

                catRepository.save(catEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public CompletableFuture<Cat> getCatById(UUID id) {
        return catRepository.findById(id)
                .map(catEntity -> CompletableFuture.completedFuture(CatEntityMapper.mapToModel(catEntity)))
                .orElseThrow(() -> new IllegalArgumentException("Cat not found. Id: " + id));
    }

    @Override
    public CompletableFuture<Void> addCat(Cat cat) {
        CatEntity catEntity = CatEntityMapper.mapToEntity(cat);
        return CompletableFuture.runAsync(() -> catRepository.save(catEntity));
    }

    @Override
    public CompletableFuture<Void> updateCat(UUID id, Cat cat) {
        return CompletableFuture.supplyAsync(() -> catRepository.findById(id))
                .thenCompose(existingCatOptional -> {
                    if (existingCatOptional.isEmpty()) {
                        throw new IllegalArgumentException("Cat not found");
                    }
                    CatEntity existingCat = existingCatOptional.get();
                    existingCat.setName(cat.getName());
                    existingCat.setCatColor(cat.getCatColor());
                    existingCat.setBreed(cat.getBreed());
                    existingCat.setBirthdayDate(cat.getBirthdayDate());
                    existingCat.setOwner(PersonEntityMapper.mapToEntity(cat.getOwner()));
                    return CompletableFuture.runAsync(() -> catRepository.save(existingCat));
                });
    }

    @Override
    public CompletableFuture<Object> deleteCat(UUID id) {
        return catRepository.findById(id)
                .map(catEntity -> {
                    catRepository.deleteById(id);
                    return CompletableFuture.completedFuture(null);
                })
                .orElseThrow(() -> new IllegalArgumentException("Cat not found"));
    }
}
