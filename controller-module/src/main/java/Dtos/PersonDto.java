package Dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PersonDto {
    public String name;
    private LocalDate birthdayDate;
    private List<CatDto> cats = new ArrayList<>();
}
