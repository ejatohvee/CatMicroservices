package com.maksim.Lab3.cat.Services;

import Models.Cat;
import Models.CatColor;
import Models.Person;

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

    CompletableFuture<Void> deleteCat(UUID id);
}