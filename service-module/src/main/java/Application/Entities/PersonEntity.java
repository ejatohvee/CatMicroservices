package Application.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="Persons", schema = "CatsSchema")
@Getter
@Setter
public class PersonEntity {
    @Column(name = "name")
    public String name;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID personId;

    @Column(name = "birthday_date")
    private LocalDate birthdayDate;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CatEntity> cats = new ArrayList<>();

    public PersonEntity(String name, LocalDate birthdayDate) {
        this.name = name;
        this.birthdayDate = birthdayDate;
    }

    public PersonEntity() {}
}