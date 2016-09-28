package pl.pragmatists.trainings.ecommerce.addtocart;

import static java.util.Arrays.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.pragmatists.trainings.ecommerce.addtocart.json.CartItemJson;
import pl.pragmatists.trainings.ecommerce.addtocart.json.CartJson;
import pl.pragmatists.trainings.ecommerce.cart.Cart;
import pl.pragmatists.trainings.ecommerce.cart.CartItem;
import pl.pragmatists.trainings.ecommerce.product.persistence.ProductRepository;

@Controller
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/user/{userId}/cart/items", method = RequestMethod.POST)
    public ResponseEntity add(@PathVariable Long userId, @RequestBody CartItemJson cartItemJson) {
        CartItem cartItem = new CartItem(productRepository.findOne(cartItemJson.productId), cartItemJson.quantity);
        Cart cart = new Cart(userId).withItems(asList(cartItem));
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
