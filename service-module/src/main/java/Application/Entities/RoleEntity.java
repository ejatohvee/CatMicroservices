package Application.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "roles", schema = "CatsSchema")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "name")
    private String name;
}
