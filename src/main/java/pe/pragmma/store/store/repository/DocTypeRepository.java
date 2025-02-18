package pe.pragmma.store.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.pragmma.store.store.repository.entity.DocTypeEntity;

import java.util.Optional;

@Repository
public interface DocTypeRepository extends JpaRepository<DocTypeEntity, Integer> {

    Optional<DocTypeEntity> findByName(String name);
}
