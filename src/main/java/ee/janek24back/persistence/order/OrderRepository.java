package ee.janek24back.persistence.order;

import ee.janek24back.persistence.teenus.Teenus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {


    @Query("select o from Order o where o.user.id = :userId")
    List<Order> findBy(Integer userId);




}