package pl.pragmatists.trainings.ecommerce.product.persistence;

import org.springframework.data.repository.Repository;

public interface ProductRepository extends Repository<Product, Long> {
    Product findOne(long productId);

    Product save(Product product);
}
