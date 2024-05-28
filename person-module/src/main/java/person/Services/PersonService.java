package person.Services;

import Entities.PersonEntity;
import EntityMappers.PersonEntityMapper;
import Models.Cat;
import Models.Person;
import com.maksim.Lab3.daos.IPersonRepository;
import com.maksim.Lab3.cat.Services.ICatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class PersonService implements IPersonService {
    private final IPersonRepository personRepository;
    private final ICatService catService;

    public PersonService(@Autowired IPersonRepository personRepository, ICatService catService) {
        this.personRepository = personRepository;
        this.catService = catService;
    }

    @Override
    public CompletableFuture<Person> getPersonById(UUID id) {
        return personRepository.findById(id)
                .map(personEntity -> CompletableFuture.completedFuture(PersonEntityMapper.mapToModel(personEntity)))
                .orElseThrow(() -> new IllegalArgumentException("Cat not found. Id: " + id));
    }

    @Override
    public CompletableFuture<Void> addPerson(Person person) {
        PersonEntity personEntity = PersonEntityMapper.mapToEntity(person);
        return CompletableFuture.runAsync(() -> personRepository.save(personEntity));
    }

    @Override
    public CompletableFuture<Void> updatePerson(UUID id, Person person) {
        return CompletableFuture.supplyAsync(() -> personRepository.findById(id))
                .thenCompose(existingPersonOptional -> {
                    if (existingPersonOptional.isEmpty()) {
                        throw new IllegalArgumentException("Person not found");
                    }
                    PersonEntity existingPerson = existingPersonOptional.get();
                    existingPerson.setName(person.getName());
                    existingPerson.setBirthdayDate(person.getBirthdayDate());
                    return CompletableFuture.runAsync(() -> personRepository.save(existingPerson));
                });
    }

    @Override
    public CompletableFuture<Object> deletePerson(UUID id) {
        return personRepository.findById(id)
                .map(catEntity -> {
                    personRepository.deleteById(id);
                    return CompletableFuture.completedFuture(null);
                })
                .orElseThrow(() -> new IllegalArgumentException("Cat not found"));
    }

    @Override
    public CompletableFuture<Void> addCat(Cat cat, UUID personId) {
        Optional<PersonEntity> optionalPerson = personRepository.findById(personId);
        if (optionalPerson.isPresent()) {
            PersonEntity personEntity = optionalPerson.get();
            Person person = PersonEntityMapper.mapToModel(personEntity);
            cat.setOwner(person);
            catService.addCat(cat);
        }
        else throw new IllegalArgumentException();
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public boolean catsOwner(UUID catId, String personName) {
        Optional<Cat> catOptional = Optional.ofNullable(catService.getCatById(catId).join());

        if (catOptional.isEmpty()) {
            throw new IllegalArgumentException("Cat not found");
        }

        String ownerName = catOptional.get().getOwner().getName();
        return ownerName.equals(personName);
    }
}
