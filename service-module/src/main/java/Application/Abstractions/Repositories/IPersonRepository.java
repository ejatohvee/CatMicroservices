package Application.Abstractions.Repositories;

import Application.Entities.PersonEntity;
import io.micrometer.common.lang.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Repository
public interface IPersonRepository extends JpaRepository<PersonEntity, UUID> {
    void deleteById(@Nullable UUID id);
}