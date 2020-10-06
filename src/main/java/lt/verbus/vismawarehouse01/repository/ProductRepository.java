package lt.verbus.vismawarehouse01.repository;

import lt.verbus.vismawarehouse01.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
