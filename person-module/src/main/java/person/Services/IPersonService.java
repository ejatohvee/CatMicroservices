package person.Services;

import Models.Cat;
import Models.Person;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IPersonService {
    CompletableFuture<Person> getPersonById(UUID id);

    CompletableFuture<Void> addPerson(Person person);

    CompletableFuture<Void> updatePerson(UUID id, Person person);

    CompletableFuture<Object> deletePerson(UUID id);

    CompletableFuture<Void> addCat(Cat cat, UUID personId);

    boolean catsOwner(UUID catId, String personName);
}