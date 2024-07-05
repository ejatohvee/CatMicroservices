package com.maksim.Lab3.externalInterface.RequestsMappers;

import com.maksim.Lab3.contracts.catFriends.requests.MakeFriends;

import java.util.UUID;

public class CatFriendsMapper {
    public static MakeFriends makeFriends(UUID catOneId, UUID catTwoId) {
        return new MakeFriends(catOneId, catTwoId);
    }
}
