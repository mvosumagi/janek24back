package ee.janek24back.persistence.usercompany;

import ee.janek24back.persistence.company.Company;
import ee.janek24back.persistence.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCompanyRepository extends JpaRepository<UserCompany, Integer> {
    boolean existsByUserAndCompany(User user, Company company);
}