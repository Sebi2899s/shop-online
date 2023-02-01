package ro.itschool.Final.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.Final.project.entity.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
}
