package ee.janek24back.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.username = :username and u.password = :password and u.status = :status")
    Optional<User> findUserBy(String username, String password, String status);

    @Query("select (count(u) > 0) from User u where upper(u.username) = upper(:username)")
    boolean userExistsBy(String username);
    Optional<User> findByUsernameIgnoreCase(String username);
    List<User> findAllByUsernameIgnoreCaseIn(Collection<String> usernames);
}
