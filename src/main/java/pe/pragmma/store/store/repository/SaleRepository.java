package pe.pragmma.store.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.pragmma.store.store.repository.entity.SaleEntity;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity,Integer> {
}
