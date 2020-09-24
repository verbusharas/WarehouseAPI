package lt.verbus.vismawarehouse01.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lt.verbus.vismawarehouse01.config.SwaggerConfig;
import lt.verbus.vismawarehouse01.exeption.ResourceNotFoundException;
import lt.verbus.vismawarehouse01.model.Product;
import lt.verbus.vismawarehouse01.repository.ProductRepository;
import lt.verbus.vismawarehouse01.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@Api(tags = { SwaggerConfig.TAG_1 })
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "This will provide a list of all products in the warehouse.", notes = "Some other notes about the API. ...")
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAll();/* productRepository.findAll(); */
    }
    @GetMapping("/products/filters")
    public List<Product> getByTypeAndMaxQuantity(@RequestParam Map<String, String> requestParams) throws Exception {
        String type = requestParams.get("type");
        int maxQuantity = Integer.parseInt(requestParams.get("max_quantity"));
        return productService.findByTypeAndMaxQuantity(type, maxQuantity);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductsById(@PathVariable(value = "id") Long productId)
            throws ResourceNotFoundException {
        Product product =
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found on :: " + productId));

        return ResponseEntity.ok().body(product);

    }

    @PostMapping("/products")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable(value = "id") Long productId, @Valid @RequestBody Product productDetails)
            throws ResourceNotFoundException {

        Product product =
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResourceNotFoundException("Project not found on :: " + productId));

        product.setType(productDetails.getType());
        product.setExpiryDate(productDetails.getExpiryDate());
        product.setQuantity(productDetails.getQuantity());
        final Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/product/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId) throws Exception {

        Product product =
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found on :: " + productId));

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
