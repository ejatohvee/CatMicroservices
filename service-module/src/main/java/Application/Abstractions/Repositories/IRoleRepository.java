package Application.Abstractions.Repositories;

import Application.Entities.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends CrudRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(String name);
}
