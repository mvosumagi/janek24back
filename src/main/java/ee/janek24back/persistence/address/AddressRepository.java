package ee.janek24back.persistence.address;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findTopByUser_IdOrderByIdDesc(Integer userId);
}