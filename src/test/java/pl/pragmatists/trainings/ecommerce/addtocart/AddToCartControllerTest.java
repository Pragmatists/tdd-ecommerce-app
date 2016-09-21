package pl.pragmatists.trainings.ecommerce.addtocart;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import pl.pragmatists.trainings.ecommerce.cart.Cart;
import pl.pragmatists.trainings.ecommerce.cart.CartItem;
import pl.pragmatists.trainings.ecommerce.common.Money;
import pl.pragmatists.trainings.ecommerce.product.persistence.Product;
import pl.pragmatists.trainings.ecommerce.product.persistence.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AddToCartControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void add_one_product() throws Exception {
        Product product = new Product(1L, "cup", new Money(3, 50));
        productRepository.save(product);

        mvc.perform(post("/user/5/cart/items")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(
                        new JSONObject()
                                .put("productId", 1L)
                                .put("quantity", 3)
                                .toString()
                )
        );

        assertThat(firstCart().userId()).isEqualTo(5L);
        assertThat(firstCart().items()).containsExactly(new CartItem(product, 3));
    }

    private Cart firstCart() {
        return cartRepository.findAll().iterator().next();
    }

}
