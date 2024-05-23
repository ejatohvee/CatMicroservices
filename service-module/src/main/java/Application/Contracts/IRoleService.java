package Application.Contracts;

import Application.Entities.RoleEntity;

import java.util.Optional;

public interface IRoleService {
    Optional<RoleEntity> findUserByName(String name);
    RoleEntity getUserRole();
}
