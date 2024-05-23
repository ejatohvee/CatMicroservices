package Application.Abstractions.Repositories;

import Application.Entities.CatEntity;
import Application.Models.CatColor;
import io.micrometer.common.lang.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ICatRepository extends JpaRepository<CatEntity, UUID> {
    List<CatEntity> findByColor(CatColor catColor);
    void deleteById(@Nullable UUID id);
}