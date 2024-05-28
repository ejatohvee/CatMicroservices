package com.maksim.Lab3.contracts.cat.requests;

import Models.Cat;
import Models.Person;

public record changeOwner(Cat cat, Person newOwner) {
}
