package lt.verbus.vismawarehouse01.model;


import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @ApiModelProperty(value = "This is the auto-generated id of the product")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ApiModelProperty(value = "This is the type of the product", required = true)
    @Column(name = "type", nullable = false)
    private String type;

    @ApiModelProperty(value = "This is the expiration date of the product", required = true)
    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @ApiModelProperty(value = "This is remaining quantity of the product", required = true)
    @Column(name = "quantity", nullable = false)
    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
