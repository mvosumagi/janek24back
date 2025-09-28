package ee.janek24back.persistence.usercompany;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserCompanyRepository extends JpaRepository<UserCompany, Integer> {
    boolean existsByUserAndCompany(ee.janek24back.persistence.user.User user, ee.janek24back.persistence.company.Company company);
    Optional<UserCompany> findFirstByUser_Id(Integer userId);
}