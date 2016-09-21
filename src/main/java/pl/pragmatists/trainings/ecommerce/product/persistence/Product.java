package pl.pragmatists.trainings.ecommerce.product.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    private long id;
    private String name;

    private Product() {

    }

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
