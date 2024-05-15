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
        personEntity.setCats(mapToEntityList(person.getCats()));
        personEntity.setPersonId(person.getPersonId());
        return personEntity;
    }

    public static Person mapToModel(PersonEntity personEntity) {
        Person person = new Person(
            personEntity.getName(),
            personEntity.getBirthdayDate());
        person.setCats(mapToModelList(personEntity.getCats()));
        person.setPersonId(personEntity.getPersonId());
        return person;
    }

    private static List<CatEntity> mapToEntityList(List<Cat> cats) {
        List<CatEntity> catEntities = new ArrayList<>();
        for (Cat cat : cats) {
            catEntities.add(CatEntityMapper.mapToEntity(cat));
        }
        return catEntities;
    }

    private static List<Cat> mapToModelList(List<CatEntity> cats) {
        List<Cat> catModels = new ArrayList<>();
        for (CatEntity cat : cats) {
            catModels.add(CatEntityMapper.mapToModel(cat));
        }
        return catModels;
    }
}
