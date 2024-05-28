package Dtos;


import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class PersonDto {
    public String name;
    private LocalDate birthdayDate;
    private List<CatDto> cats = new ArrayList<>();
}
