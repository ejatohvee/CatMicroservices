package com.maksim.Lab3.cat;

import Models.Cat;
import com.maksim.Lab3.cat.Services.ICatService;
import com.maksim.Lab3.contracts.cat.requests.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@KafkaListener(topics = "cat-topic", groupId = "cat-group")
@Slf4j
@RequiredArgsConstructor
public class KafkaCatListener {

    private final ICatService catService;

    @KafkaHandler
    public void addCat(addCat command) {
        catService.addCat(command.cat());
        log.info("Cat added");
    }

    @KafkaHandler
    public void changeOwner(changeOwner command) {
        catService.changeOwner(command.cat(), command.newOwner());
        log.info("Owner changed");
    }

    @KafkaHandler
    public void deleteCat(deleteCat command) {
        catService.deleteCat(command.id());
        log.info("Called delete command");
    }

    @KafkaHandler
    @SendTo
    public CompletableFuture<Cat> getCatById(getCatById command) {
        CompletableFuture<Cat> cat = catService.getCatById(command.id());
        log.info("Gt cat by id command was done");
        return cat;
    }

    @KafkaHandler
    public void updateCat(updateCat command) {
        catService.updateCat(command.id(), command.cat());
        log.info("Called cat update command");
    }

    @KafkaHandler(isDefault = true)
    public void object(Object object) {
        log.warn("Unknown type" + object);
    }
}