package lt.verbus.vismawarehouse01.services;

import lt.verbus.vismawarehouse01.model.Product;
import lt.verbus.vismawarehouse01.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll().stream()
                .filter(p -> p.getExpiryDate().isAfter(LocalDate.of(2020,10,3)))
                .collect(Collectors.toList());
    }

    public List<Product> findByTypeAndMaxQuantity(String type, int quantity) {
        return productRepository.findAll().stream()
                .filter(p -> p.getType().equals(type))
                .filter(p -> p.getQuantity() < quantity)
                .collect(Collectors.toList());
    }

}
