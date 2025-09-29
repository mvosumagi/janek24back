package ee.janek24back.persistence.address;

import ee.janek24back.persistence.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("select a from Address a where a.user = :user order by a.user.id DESC")
    List<Address> findAddressesBy(User user);


}