import Application.Abstractions.Repositories.ICatRepository;
import Application.Application.Services.CatService;
import Application.Models.Cat;
import Application.Models.CatColor;
import Application.Models.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.eq;

import java.time.LocalDate;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
//public class CatServiceTests {
//
//    @Mock
//    private ICatRepository catRepository;
//
//    @InjectMocks
//    private CatService catService;
//
//    public CatServiceTests() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testAddCat() {
//        Person owner = new Person();
//        owner.setName("Saks");
//        owner.setBirthdayDate(LocalDate.of(2004, 11, 20));
//        Cat expectedCat = new Cat("Kitty", CatColor.White, "default", LocalDate.of(2023, 3, 20), owner);
//
//        when(catRepository.addCat(eq("Kitty"), eq(CatColor.White), eq("default"), eq(LocalDate.of(2023, 3, 20)), eq(owner))).thenReturn(CompletableFuture.completedFuture(expectedCat));
//        CompletableFuture<Cat> catFuture = catRepository.addCat("Kitty", CatColor.White, "default", LocalDate.of(2023, 3, 20), owner);
//
//        Cat savedCat = catFuture.join();
//        assertNotNull(savedCat);
//        assertEquals(expectedCat, savedCat);
//    }
//
//    @Test
//    public void testAddFriend() {
//        CatService catFriendshipService = new CatService(catRepository);
//
//        Person owner1 = new Person();
//        owner1.setName("Saks");
//        owner1.setBirthdayDate(LocalDate.of(2004, 11, 20));
//        Person owner2 = new Person();
//        owner2.setName("Nesaks");
//        owner2.setBirthdayDate(LocalDate.of(2004, 11, 20));
//
//        Cat cat1 = new Cat("Kitty1", CatColor.White, "default", LocalDate.of(2023, 3, 20), owner1);
//        cat1.setCatsId(UUID.randomUUID()); // Задаем известный идентификатор
//        Cat cat2 = new Cat("Kitty2", CatColor.White, "default", LocalDate.of(2023, 3, 20), owner2);
//        cat2.setCatsId(UUID.randomUUID()); // Задаем известный идентификатор
//
//
//        when(catRepository.addCat(eq("Kitty1"), eq(CatColor.White), eq("default"), eq(LocalDate.of(2023, 3, 20)), eq(owner1))).thenReturn(CompletableFuture.completedFuture(cat1));
//
//        when(catRepository.addCat(eq("Kitty2"), eq(CatColor.White), eq("default"), eq(LocalDate.of(2023, 3, 20)), eq(owner2))).thenReturn(CompletableFuture.completedFuture(cat2));
//
//        CompletableFuture<Cat> catFuture1 = catRepository.addCat("Kitty1", CatColor.White, "default", LocalDate.of(2023, 3, 20), owner1);
//        CompletableFuture<Cat> catFuture2 = catRepository.addCat("Kitty2", CatColor.White, "default", LocalDate.of(2023, 3, 20), owner2);
//
//        CompletableFuture<Void> friendship1 = catFuture1.thenCombine(catFuture2, (firstCat, secondCat) -> {
//            catFriendshipService.makeFriends(firstCat, secondCat);
//            return null;
//        });
//
//        assertTrue(friendship1.isDone());
//    }
//
//    @Test
//    public void testChangeOwner() {
//        CatService catService = new CatService(catRepository);
//
//        Person owner1 = new Person();
//        owner1.setName("Old Owner");
//        owner1.setBirthdayDate(LocalDate.of(1990, 1, 1));
//
//        Person owner2 = new Person();
//        owner2.setName("New Owner");
//        owner2.setBirthdayDate(LocalDate.of(1995, 5, 5));
//
//        Cat cat = new Cat("Kitty", CatColor.White, "default", LocalDate.of(2020, 1, 1), owner1);
//        UUID initialOwnerId = UUID.randomUUID(); // Задаем известный идентификатор для первого владельца
//        cat.setCatsId(initialOwnerId);
//
//        CompletableFuture<Void> changeOwnerFuture = catService.changeOwner(cat, owner2);
//        changeOwnerFuture.join();
//
//        assertTrue(changeOwnerFuture.isDone());
//
//        assertEquals(owner2, cat.getOwner());
//    }
//}