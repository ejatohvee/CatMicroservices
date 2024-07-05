package Dtos;

import Models.CatColor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CatDto {
    private String name;
    private CatColor color;
    private String breed;
    private LocalDate birthdayDate;
    private List<CatDto> friends = new ArrayList<>();
}