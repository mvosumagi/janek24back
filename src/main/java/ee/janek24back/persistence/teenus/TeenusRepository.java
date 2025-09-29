package ee.janek24back.persistence.teenus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeenusRepository extends JpaRepository<Teenus, Integer> {
    @Query("select t from Teenus t where t.user.id = :userId")
    List<Teenus> findBy(Integer userId);

}