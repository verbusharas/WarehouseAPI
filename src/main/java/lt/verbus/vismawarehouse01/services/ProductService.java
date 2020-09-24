package lt.verbus.vismawarehouse01.services;

import lt.verbus.vismawarehouse01.exception.ResourceNotFoundException;
import lt.verbus.vismawarehouse01.model.Product;
import lt.verbus.vismawarehouse01.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveToDatabase(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, Product productDetails) throws ResourceNotFoundException {
        Product product =
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResourceNotFoundException("Project not found on :: " + productId));
        product.setType(productDetails.getType());
        product.setExpiryDate(productDetails.getExpiryDate());
        product.setQuantity(productDetails.getQuantity());
        return productRepository.save(product);
    }

    public Map<String, Boolean> deleteProduct(Long productId) throws Exception {
        Product product =
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found on :: " + productId));
        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public List<Product> findByTypeAndMaxQuantity(String type, int maxQuantity) {
        return productRepository.findAll().stream()
                .filter(p -> p.getType().equals(type))
                .filter(p -> p.getQuantity() < maxQuantity)
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
