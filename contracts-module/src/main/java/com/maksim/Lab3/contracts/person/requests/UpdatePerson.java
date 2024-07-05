package com.maksim.Lab3.contracts.person.requests;

import Models.Person;

import java.util.UUID;

public record UpdatePerson(UUID id, Person person) {
}
