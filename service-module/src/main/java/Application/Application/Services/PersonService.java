package Application.Application.Services;

import Application.Abstractions.Repositories.IPersonRepository;
import Application.Contracts.ICatService;
import Application.Contracts.IPersonService;
import Application.Entities.PersonEntity;
import Application.EntityMappers.PersonEntityMapper;
import Application.Models.Cat;
import Application.Models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@ComponentScan(basePackages = "Application.Abstractions.Repositories")
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
}
