package lt.verbus.vismawarehouse01.services;

import lt.verbus.vismawarehouse01.exception.ResourceNotFoundException;
import lt.verbus.vismawarehouse01.model.Product;
import lt.verbus.vismawarehouse01.repository.ProductRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveToDatabase(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product productDetails) {
        return productRepository.save(productDetails);
    }

    public Map<String, Boolean> deleteProduct(Long productId) {
        try {
            productRepository.deleteById(productId);
        } catch (EmptyResultDataAccessException e) {
            // TODO: user friendly exception handling
        }
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public List<Product> findByTypeAndMaxQuantity(String type, int maxQuantity) {
        return productRepository.findAll().stream()
                .filter(p -> p.getType().equals(type))
                .filter(p -> p.getQuantity() <= maxQuantity)
                .collect(Collectors.toList());
    }

    public List<Product> findByExpiresBefore(Date date) {
        LocalDate localDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return productRepository.findAll().stream()
                .filter(p -> p.getExpiryDate().isBefore(localDate))
                .collect(Collectors.toList());
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findProductById(Long productId) throws ResourceNotFoundException {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product of id " + productId + ": not found"));
    }
}
