package pl.pragmatists.trainings.ecommerce.product.persistence;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    private long id;
    private String name;
    @Embedded
    private Price price;

    private Product() {

    }

    public Product(long id, String name, Price price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
