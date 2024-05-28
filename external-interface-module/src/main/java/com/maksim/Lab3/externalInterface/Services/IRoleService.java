package com.maksim.Lab3.externalInterface.Services;

import Entities.RoleEntity;

import java.util.Optional;

public interface IRoleService {
    Optional<RoleEntity> findUserByName(String name);
    RoleEntity getUserRole();
}
