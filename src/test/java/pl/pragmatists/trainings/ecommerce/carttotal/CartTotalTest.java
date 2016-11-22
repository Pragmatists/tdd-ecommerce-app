package pl.pragmatists.trainings.ecommerce.carttotal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import pl.pragmatists.trainings.ecommerce.cart.Cart;
import pl.pragmatists.trainings.ecommerce.cart.CartItem;
import pl.pragmatists.trainings.ecommerce.common.Money;
import pl.pragmatists.trainings.ecommerce.product.persistence.Product;

import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureTestEntityManager
public class CartTotalTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestEntityManager em;

    @Test
    public void total_for_one_product() throws Exception {
        Product handkerchief = em.persistAndFlush(new Product(1L, "handkerchief", new Money(3, 50)));
        CartItem handkerchiefInCart = new CartItem(handkerchief, 1);
        em.persistAndFlush(new Cart(5L).withItems(newArrayList(handkerchiefInCart)));

        mvc.perform(get("/user/5/cart"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value("3,50"));

    }

}
