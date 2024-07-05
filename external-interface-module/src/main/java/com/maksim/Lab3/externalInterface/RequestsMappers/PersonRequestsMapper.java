package com.maksim.Lab3.externalInterface.RequestsMappers;

import Dtos.PersonDto;
import Models.Person;
import com.maksim.Lab3.contracts.person.requests.*;

import java.util.UUID;

public class PersonRequestsMapper {
    public static AddPerson addperson(PersonDto personDto) {
        Person person = new Person(personDto.getName(), personDto.getBirthdayDate());
        return new AddPerson(person);
    }

    public static CatsOwner catsOwner(UUID catId, String personName) {
        return new CatsOwner(catId, personName);
    }

    public static DeletePerson deletePerson(UUID id) {
        return new DeletePerson(id);
    }

    public static GetPersonById getPersonById(UUID id) {
        return new GetPersonById(id);
    }

    public static UpdatePerson updatePerson(UUID id, PersonDto personDto) {
        Person person = new Person(personDto.getName(), personDto.getBirthdayDate());
        return new UpdatePerson(id, person);
    }
}
