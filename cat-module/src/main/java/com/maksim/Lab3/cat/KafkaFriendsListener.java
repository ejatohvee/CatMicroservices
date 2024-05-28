package com.maksim.Lab3.cat;

import com.maksim.Lab3.cat.Services.ICatService;
import com.maksim.Lab3.contracts.catFriends.requests.makeFriends;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "cat-friends-topic", groupId = "cat-friends-group")
@Slf4j
@RequiredArgsConstructor
public class KafkaFriendsListener {

    private final ICatService catService;

    @KafkaHandler
    public void makeFriends(makeFriends command) {
        catService.makeFriends(command.catOneId(), command.catTwoId());
        log.info("Make friends command called");
    }

    @KafkaHandler(isDefault = true)
    public void object(Object object) {
        log.warn("Unknown type" + object);
    }
}
