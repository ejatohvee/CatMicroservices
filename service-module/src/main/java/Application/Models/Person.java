package Application.Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Person {
    public String name;
    private UUID personId;
    private LocalDate birthdayDate;
    private List<Cat> cats = new ArrayList<>();

    public Person(String name, LocalDate birthdayDate) {
        this.name = name;
        this.birthdayDate = birthdayDate;
    }
}