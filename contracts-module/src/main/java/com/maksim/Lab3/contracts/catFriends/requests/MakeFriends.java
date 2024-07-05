package com.maksim.Lab3.contracts.catFriends.requests;

import java.util.UUID;

public record MakeFriends(UUID catOneId, UUID catTwoId) {
}
