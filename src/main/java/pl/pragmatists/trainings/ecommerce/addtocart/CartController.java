package pl.pragmatists.trainings.ecommerce.addtocart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.pragmatists.trainings.ecommerce.addtocart.json.CartItemJson;
import pl.pragmatists.trainings.ecommerce.addtocart.json.CartJson;
import pl.pragmatists.trainings.ecommerce.cart.Cart;
import pl.pragmatists.trainings.ecommerce.cart.CartItem;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @RequestMapping(value = "/user/{userId}/cart/items", method = RequestMethod.POST)
    public ResponseEntity add(@PathVariable Long userId, @RequestBody CartJson cartJson) {

        Stream<CartItemJson> stream = cartJson.items.stream();
        Stream<CartItem> rStream = stream.map(c -> new CartItem(c.productId, c.quantity));
        Cart cart = new Cart(userId).withItems(rStream.collect(Collectors.toList()));

        cartRepository.save(cart);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
