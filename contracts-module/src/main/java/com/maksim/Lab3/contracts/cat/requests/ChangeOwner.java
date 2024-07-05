package com.maksim.Lab3.contracts.cat.requests;

import Models.Cat;
import Models.Person;

public record ChangeOwner(Cat cat, Person newOwner) {
}
