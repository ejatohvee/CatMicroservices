package Application.EntityMappers;

import Application.Entities.CatEntity;
import Application.Entities.PersonEntity;
import Application.Models.Cat;
import Application.Models.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonEntityMapper {
    public static PersonEntity mapToEntity(Person person) {
        PersonEntity personEntity = new PersonEntity(
            person.getName(),
            person.getBirthdayDate());
        personEntity.setPersonId(person.getPersonId());
        return personEntity;
    }

    public static Person mapToModel(PersonEntity personEntity) {
        Person person = new Person(
            personEntity.getName(),
            personEntity.getBirthdayDate());
        person.setPersonId(personEntity.getPersonId());
        return person;
    }
}
