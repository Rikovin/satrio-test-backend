package testing.satrio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testing.satrio.backend.model.ERole;
import testing.satrio.backend.model.Role;

import java.util.Optional;

@Repository
public interface XRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}