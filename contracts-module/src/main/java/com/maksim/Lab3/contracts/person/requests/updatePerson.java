package com.maksim.Lab3.contracts.person.requests;

import Models.Person;

import java.util.UUID;

public record updatePerson(UUID id, Person person) {
}
