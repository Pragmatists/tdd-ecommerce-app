package pl.pragmatists.trainings.ecommerce.addtocart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.pragmatists.trainings.ecommerce.addtocart.json.CartItemJson;
import pl.pragmatists.trainings.ecommerce.addtocart.json.CartJson;
import pl.pragmatists.trainings.ecommerce.cart.Cart;
import pl.pragmatists.trainings.ecommerce.cart.CartItem;
import pl.pragmatists.trainings.ecommerce.product.persistence.ProductRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/user/{userId}/cart/items", method = RequestMethod.POST)
    public ResponseEntity add(@PathVariable Long userId, @RequestBody CartJson cartJson) {

        Stream<CartItemJson> stream = cartJson.items.stream();
        Stream<CartItem> rStream = stream.map(c -> new CartItem(productRepository.findOne(c.productId), c.quantity));
        Cart cart = new Cart(userId).withItems(rStream.collect(Collectors.toList()));

        cartRepository.save(cart);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/user/{userId}/cart", method = RequestMethod.GET)
    public @ResponseBody CartJson get(@PathVariable Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        CartJson cartJson = new CartJson();
        cartJson.total = cart.total().toString();
        return cartJson;
    }

}
