package ro.itschool.Final.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.Final.project.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByName(String name);

    List<Product> findByDeletedIsFalse();
}
