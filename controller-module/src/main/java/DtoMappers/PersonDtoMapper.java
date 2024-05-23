package DtoMappers;

import Application.Models.Person;
import Dtos.PersonDto;

import java.util.stream.Collectors;

public class PersonDtoMapper {

    public static PersonDto mapToDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setName(person.getName());
        personDto.setBirthdayDate(person.getBirthdayDate());
//        personDto.setCats(person.getCats().stream()
//                .map(CatDtoMapper::mapToDto)
//                .collect(Collectors.toList()));
        return personDto;
    }

    public static Person mapToModel(PersonDto personDto) {

//        person.setCats(personDto.getCats().stream()
//                .map(CatDtoMapper::mapToModel)
//                .collect(Collectors.toList()));
        return new Person(
        personDto.getName(),
        personDto.getBirthdayDate());
    }
}
