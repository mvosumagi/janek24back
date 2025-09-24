package ee.janek24back.persistence.company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByNumber(String number);
    Optional<Company> findByNumber(String number);

}