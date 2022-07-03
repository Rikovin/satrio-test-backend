package testing.satrio.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testing.satrio.backend.model.Cif;

@Repository
public interface CifRepository extends JpaRepository<Cif, Long>{

}