package Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Cat {
    public String name;
    private UUID catsId;
    private CatColor color;
    private String breed;
    private LocalDate birthdayDate;
    private Person owner;
    private List<Cat> friends = new ArrayList<>();

    public Cat(String name, CatColor color, String breed, LocalDate birthdayDate) {
        this.name = name;
        this.color = color;
        this.breed = breed;
        this.birthdayDate = birthdayDate;
    }
}