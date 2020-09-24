package lt.verbus.vismawarehouse01.controller;

import io.swagger.v3.oas.annotations.Operation;
import lt.verbus.vismawarehouse01.exeption.ResourceNotFoundException;
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
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    //1. Create products in system database
    @Operation(summary = "Adds new product")
    @PostMapping("/products")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.saveToDatabase(product);
    }

    //2. Update product info in system database
    @Operation(summary = "Updates existing product info")
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable(value = "id") Long productId, @Valid @RequestBody Product productDetails)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(productService.updateProduct(productId, productDetails));
    }

    //3. Remove product from system database
    @Operation(summary = "Removes product")
    @DeleteMapping("/products/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId) throws Exception {
        return productService.deleteProduct(productId);
    }

    //4. Get deficit products
    @Operation(summary = "Returns products of specified type and not exceeding specified quantity) " +
            "(e.g. /products/of?type=dairy&max_quantity=3")
    @GetMapping("/products/of")
    public List<Product> findByTypeAndMaxQuantity(@RequestParam Map<String, String> requestParams) {
        String type = requestParams.get("type");
        int maxQuantity = Integer.parseInt(requestParams.get("max_quantity"));
        return productService.findByTypeAndMaxQuantity(type, maxQuantity);
    }

    //5. Get products about to expire
    @Operation(summary = "Returns all products expiring before specified date " +
            "(e.g. /products/expires_before?date=2020-11-11")
    @GetMapping("/products/expires_before")
    public List<Product> findByExpiresBefore(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return productService.findByExpiresBefore(date);
    }

    @Operation(summary = "Returns all products")
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @Operation(summary = "Returns specified product")
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductsById(@PathVariable(value = "id") Long productId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(productService.findProductById(productId));
    }

}