package DtoMappers;

import Dtos.CatDto;
import Models.Cat;

import java.util.stream.Collectors;

public class CatDtoMapper {

    public static CatDto mapToDto(Cat cat) {
        CatDto dto = new CatDto();
        dto.setName(cat.getName());
        dto.setColor(cat.getColor());
        dto.setBreed(cat.getBreed());
        dto.setBirthdayDate(cat.getBirthdayDate());
        dto.setFriends(cat.getFriends().stream().map(CatDtoMapper::mapToDto).collect(Collectors.toList()));
        return dto;
    }

    public static Cat mapToModel(CatDto dto) {
        Cat cat = new Cat(
                dto.getName(),
            dto.getColor(),
            dto.getBreed(),
            dto.getBirthdayDate());

        cat.setFriends(dto.getFriends().stream().map(CatDtoMapper::mapToModel).collect(Collectors.toList()));
        return cat;
    }
}
