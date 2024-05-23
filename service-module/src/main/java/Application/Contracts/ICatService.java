package Application.Contracts;

import Application.Models.Cat;
import Application.Models.CatColor;
import Application.Models.Person;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ICatService {
    CompletableFuture<Void> makeFriends(UUID catOneId, UUID catTwoId);
    CompletableFuture<Void> changeOwner(Cat cat, Person newOwner);

    CompletableFuture<List<Cat>> getCatsByColor(CatColor color);

    CompletableFuture<Cat> getCatById(UUID id);

    CompletableFuture<Void> addCat(Cat cat);

    CompletableFuture<Void> updateCat(UUID id, Cat cat);

    CompletableFuture<Object> deleteCat(UUID id);
}