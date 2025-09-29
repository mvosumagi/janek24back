package ee.janek24back.persistence.usercompany;

import ee.janek24back.persistence.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserCompanyRepository extends JpaRepository<UserCompany, Integer> {
    boolean existsByUserAndCompany(ee.janek24back.persistence.user.User user, ee.janek24back.persistence.company.Company company);

    @Query("select u from UserCompany u where u.user = :user")
    Optional<UserCompany> findCompanyBy(User user);


}