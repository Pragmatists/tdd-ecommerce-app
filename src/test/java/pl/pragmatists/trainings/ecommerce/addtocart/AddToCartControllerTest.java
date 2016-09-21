package pl.pragmatists.trainings.ecommerce.addtocart;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.pragmatists.trainings.ecommerce.cart.Cart;
import pl.pragmatists.trainings.ecommerce.cart.CartItem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AddToCartControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void add_one_product() throws Exception {
        mvc.perform(post("/user/5/cart/items")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(
                        new JSONObject()
                                .put("items", new JSONArray()
                                        .put(new JSONObject()
                                                .put("productId", 1L)
                                                .put("quantity", 3)
                                        )
                                ).toString()
                )
        );

        assertThat(firstCart().userId()).isEqualTo(5L);
        assertThat(firstCart().items()).containsExactly(new CartItem(1L, 3));
    }

    private Cart firstCart() {
        return cartRepository.findAll().iterator().next();
    }

}
