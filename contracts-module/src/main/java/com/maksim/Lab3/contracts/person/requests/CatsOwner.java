package com.maksim.Lab3.contracts.person.requests;

import java.util.UUID;

public record CatsOwner(UUID catId, String personName) {
}
