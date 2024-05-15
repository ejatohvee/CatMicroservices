package Application.Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Cat {
    public String name;
    private UUID catsId;
    private CatColor catColor;
    private String breed;
    private LocalDate birthdayDate;
    private Person owner;
    private List<Cat> friends = new ArrayList<>();

    public Cat(String name, CatColor catColor, String breed, LocalDate birthdayDate) {
        this.name = name;
        this.catColor = catColor;
        this.breed = breed;
        this.birthdayDate = birthdayDate;
    }
}