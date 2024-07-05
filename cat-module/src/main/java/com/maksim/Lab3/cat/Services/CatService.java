package com.maksim.Lab3.cat.Services;

import Entities.CatEntity;
import Entities.PersonEntity;
import EntityMappers.CatEntityMapper;
import EntityMappers.PersonEntityMapper;
import Models.Cat;
import Models.CatColor;
import Models.Person;
import com.maksim.Lab3.cat.Services.ICatService;
import com.maksim.Lab3.daos.ICatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Transactional
@ComponentScan(basePackages = "com.maksim.Lab3.daos")
public class CatService implements ICatService {
    private final ICatRepository catRepository;

    @Autowired
    public CatService(ICatRepository catRepository) {
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
                    existingCat.setColor(cat.getColor());
                    existingCat.setBreed(cat.getBreed());
                    existingCat.setBirthdayDate(cat.getBirthdayDate());
                    existingCat.setOwner(PersonEntityMapper.mapToEntity(cat.getOwner()));
                    System.out.println(existingCat.getCatsId());
                    return CompletableFuture.runAsync(() -> catRepository.save(existingCat));
                });
    }

    @Override
    public CompletableFuture<Void> deleteCat(UUID id) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<CatEntity> optionalCatEntity = catRepository.findById(id);

            System.out.println(optionalCatEntity.isPresent());

            if (optionalCatEntity.isPresent()) {

                System.out.println(id);

                catRepository.delete(optionalCatEntity.get());

                return null;

            } else {
                throw new IllegalArgumentException("Cat not found");
            }

        });
    }
}
