package lt.verbus.vismawarehouse01.controller;

import io.swagger.v3.oas.annotations.Operation;
import lt.verbus.vismawarehouse01.exception.ResourceNotFoundException;
import lt.verbus.vismawarehouse01.model.Product;
import lt.verbus.vismawarehouse01.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Adds new product")
    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.saveToDatabase(product);
    }

    @Operation(summary = "Updates existing product info")
    @PutMapping
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product productDetails) {
        return ResponseEntity.ok(productService.updateProduct(productDetails));
    }

    @Operation(summary = "Removes product")
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId) {
        return productService.deleteProduct(productId);
    }

    @Operation(summary = "Returns products of specified type and not exceeding specified quantity) " +
            "(e.g. /products/of?type=dairy&max_quantity=3")
    @GetMapping("/of")
    public List<Product> findByTypeAndMaxQuantity(@RequestParam String type,  @RequestParam String max_quantity) {
        int maxQuantity = Integer.parseInt(max_quantity);
        return productService.findByTypeAndMaxQuantity(type, maxQuantity);
    }

    @Operation(summary = "Returns all products expiring before specified date " +
            "(e.g. /products/expires_before?date=2020-11-11")
    @GetMapping("/expires_before")
    public List<Product> findByExpiresBefore(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return productService.findByExpiresBefore(date);
    }

    @Operation(summary = "Returns all products")
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @Operation(summary = "Returns specified product")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductsById(@PathVariable(value = "id") Long productId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(productService.findProductById(productId));
    }

}