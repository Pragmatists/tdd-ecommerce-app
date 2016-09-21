package pl.pragmatists.trainings.ecommerce.addtocart;

import org.springframework.data.repository.CrudRepository;
import pl.pragmatists.trainings.ecommerce.cart.Cart;

public interface CartRepository extends CrudRepository<Cart, Long>{
    Cart findByUserId(Long userId);
}
