package com.maksim.Lab3.daos;

import Entities.PersonEntity;
import io.micrometer.common.lang.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPersonRepository extends JpaRepository<PersonEntity, UUID> {
    void deleteById(@Nullable UUID id);
}