package ro.itschool.Final.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.Final.project.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
