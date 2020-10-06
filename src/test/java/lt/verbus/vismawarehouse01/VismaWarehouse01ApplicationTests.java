package lt.verbus.vismawarehouse01;


import lt.verbus.vismawarehouse01.model.Product;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VismaWarehouse01Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VismaWarehouse01ApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetAllProducts() {

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/products",
                HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetProductById() {
        Product product = restTemplate.getForObject(getRootUrl() + "/products/1", Product.class);
        System.out.println(product.getType());
        Assert.assertNotNull(product);
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setType("dairy");
        product.setExpiryDate(LocalDate.now());
        product.setQuantity(2);

        ResponseEntity<Product> postResponse = restTemplate.postForEntity(getRootUrl() + "/products", product, Product.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testDeletePost() {
        int id = 2;
        Product product = restTemplate.getForObject(getRootUrl() + "/products/" + id, Product.class);
        Assert.assertNotNull(product);

        restTemplate.delete(getRootUrl() + "/products/" + id);

        try {
            product = restTemplate.getForObject(getRootUrl() + "/products/" + id, Product.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
        }
    }
}
