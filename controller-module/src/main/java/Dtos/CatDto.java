package Dtos;

import Application.Models.CatColor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CatDto {
    private String name;
    private CatColor catColor;
    private String breed;
    private LocalDate birthdayDate;
    private List<CatDto> friends = new ArrayList<>();
}