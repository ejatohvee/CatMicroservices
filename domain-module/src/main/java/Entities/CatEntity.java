package Entities;

import Models.CatColor;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cats", schema= "CatsSchema")
@Data
public class CatEntity {
    @Column(name = "name")
    public String name;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID catsId;

    @Enumerated(EnumType.STRING)
    private CatColor color;

    @Column(name = "breed")
    private String breed;

    @Column(name = "birthday_date")
    private LocalDate birthdayDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private PersonEntity owner;

    @ManyToMany
    @JoinTable(
            name = "cat_friends",
            schema = "CatsSchema",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_cat_id")
    )
    private List<CatEntity> friends = new ArrayList<>();

    public CatEntity(String name, CatColor color, String breed, LocalDate birthdayDate, PersonEntity owner) {
        this.name = name;
        this.color = color;
        this.breed = breed;
        this.birthdayDate = birthdayDate;
        this.owner = owner;
    }

    public CatEntity() {
    }

    public void addFriend(CatEntity cat){
        friends.add(cat);
    }
}