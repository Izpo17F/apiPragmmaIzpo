package pe.pragmma.store.store.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.pragmma.store.store.repository.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query("SELECT p FROM ProductEntity p WHERE p.id = :id AND p.active = :active")
    Optional<ProductEntity> findByIdAndActive(@Param("id") Integer id, @Param("active") Boolean active);

    @Query("SELECT p FROM ProductEntity p WHERE p.active = true")
    List<ProductEntity> findByActive();
}
