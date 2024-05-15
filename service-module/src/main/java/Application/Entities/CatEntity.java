package Application.Entities;

import Application.Models.CatColor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cats", schema= "CatsSchema")
@Getter
@Setter
public class CatEntity {
    @Column(name = "name")
    public String name;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID catsId;

    @Enumerated(EnumType.STRING)
    private CatColor catColor;

    @Column(name = "breed")
    private String breed;

    @Column(name = "birthday_date")
    private LocalDate birthdayDate;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private PersonEntity owner;

    @ManyToMany
    @JoinTable(
            name = "cat_friends",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_cat_id")
    )
    private List<CatEntity> friends = new ArrayList<>();

    public CatEntity(String name, CatColor catColor, String breed, LocalDate birthdayDate, PersonEntity owner) {
        this.name = name;
        this.catColor = catColor;
        this.breed = breed;
        this.birthdayDate = birthdayDate;
        this.owner = owner;
    }

    public CatEntity() {
    }
}