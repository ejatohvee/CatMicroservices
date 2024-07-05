package com.maksim.Lab3.persons;

import com.maksim.Lab3.contracts.person.requests.AddPerson;
import com.maksim.Lab3.contracts.person.requests.CatsOwner;
import com.maksim.Lab3.contracts.person.requests.DeletePerson;
import com.maksim.Lab3.contracts.person.requests.UpdatePerson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import com.maksim.Lab3.persons.Services.IPersonService;

import java.util.concurrent.CompletableFuture;

@Service
@KafkaListener(topics = "person-topic", groupId = "person-consumer-group")
@RequiredArgsConstructor
@Slf4j
public class KafkaPersonListener {
    private final IPersonService personService;

    @KafkaHandler
    public void addPerson(AddPerson command) {
        personService.addPerson(command.person());
        log.info("Person was added");
    }

    @KafkaHandler
    public void updatePerson(UpdatePerson command) {
        personService.updatePerson(command.id(),command.person());
        log.info("Person was updated");
    }

    @KafkaHandler
    @SendTo
    public CompletableFuture<Object> deletePerson(DeletePerson command) {
        CompletableFuture<Object> object = personService.deletePerson(command.id());
        log.info("Person was deleted");
        return object;
    }

    @KafkaHandler
    @SendTo
    public boolean catsOwner(CatsOwner command) {
        boolean ans = personService.catsOwner(command.catId(), command.personName());
        log.info("Cat's owner was checked");
        return ans;
    }

    @KafkaHandler(isDefault = true)
    public void object(Object object) {
        log.warn("Unknown type" + object);
    }
}
