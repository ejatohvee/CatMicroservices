package com.maksim.Lab3.contracts.cat.requests;

import Models.Cat;

import java.util.UUID;

public record updateCat(UUID id, Cat cat) {
}
