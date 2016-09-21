package pl.pragmatists.trainings.ecommerce.carttotal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.pragmatists.trainings.ecommerce.addtocart.CartRepository;
import pl.pragmatists.trainings.ecommerce.cart.Cart;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartTotalTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void total_for_one_product() throws Exception {

//        CartBuilder.aCart().ofUserWithId().withItem(4L, 4)

        mvc.perform(get("/user/5/cart"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("").value(""));

    }

    private Cart firstCart() {
        return cartRepository.findAll().iterator().next();
    }

}
