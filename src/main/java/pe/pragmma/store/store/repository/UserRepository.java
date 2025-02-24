package pe.pragmma.store.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.pragmma.store.store.repository.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsernameAndActive(String username, Boolean active);

    @Query("SELECT u FROM UserEntity u WHERE u.id=:id and u.active=:active")
    Optional<UserEntity> findByIdAndActive(@Param("id") Integer id,@Param("active") Boolean active);

    @Query("SELECT u FROM UserEntity u WHERE u.active=:active")
    List<UserEntity> findAllActive(@Param("active") Boolean active);


}
