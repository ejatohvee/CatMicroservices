package com.maksim.Lab3.externalInterface.RequestsMappers;

import Dtos.CatDto;
import Dtos.PersonDto;
import Models.Cat;
import Models.CatColor;
import Models.Person;
import com.maksim.Lab3.contracts.cat.requests.*;

import java.util.UUID;

public class CatRequestsMapper {
    public static AddCat addCat(CatDto catDto) {
        Cat cat = new Cat(catDto.getName(), catDto.getColor(), catDto.getBreed(), catDto.getBirthdayDate());
        return new AddCat(cat);
    }

    public static ChangeOwner changeOwner(CatDto catDto, PersonDto personDto) {
        Cat cat = new Cat(catDto.getName(), catDto.getColor(), catDto.getBreed(), catDto.getBirthdayDate());
        Person person = new Person(personDto.getName(), personDto.getBirthdayDate());
        return new ChangeOwner(cat, person);
    }

    public static DeleteCat deleteCat(UUID id) {
        return new DeleteCat(id);
    }

    public static GetCatById getCatById(UUID id) {
        return new GetCatById(id);
    }

    public static GetCatsByColor getCatsByColor(CatColor catColor) {
        return new GetCatsByColor(catColor);
    }

    public static UpdateCat updateCat(UUID id, CatDto catDto) {
        Cat cat = new Cat(catDto.getName(), catDto.getColor(), catDto.getBreed(), catDto.getBirthdayDate());
        return new UpdateCat(id, cat);
    }
}
