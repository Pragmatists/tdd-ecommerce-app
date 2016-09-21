package pl.pragmatists.trainings.ecommerce.carttotal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import pl.pragmatists.trainings.ecommerce.addtocart.CartRepository;
import pl.pragmatists.trainings.ecommerce.cart.Cart;
import pl.pragmatists.trainings.ecommerce.cart.CartItem;
import pl.pragmatists.trainings.ecommerce.common.Money;
import pl.pragmatists.trainings.ecommerce.product.persistence.Product;
import pl.pragmatists.trainings.ecommerce.product.persistence.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.pragmatists.trainings.ecommerce.carttotal.CartTotalTest.CartBuilder.aCart;
import static pl.pragmatists.trainings.ecommerce.carttotal.CartTotalTest.ProductBuilder.aProduct;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional()
public class CartTotalTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void total_for_one_product() throws Exception {

        aCart().ofUserWithId(5).withItem(
                aProduct().priced(3, 50).savedIn(productRepository), 4
        ).savedIn(cartRepository);

        mvc.perform(get("/user/5/cart"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value("3,50"));

    }

    public static class CartBuilder {
        private long userId = -1L;
        private List<CartItem> items = new ArrayList<>();

        public static CartBuilder aCart() {
            return new CartBuilder();
        }

        public CartBuilder ofUserWithId(int userId) {
            this.userId = userId;
            return this;
        }

        public CartBuilder withItem(Product product, int quantity) {
            items.add(new CartItem(product, quantity));
            return this;
        }

        public Cart savedIn(CartRepository em) {
            Cart cart = new Cart(userId).withItems(items);
            em.save(cart);
            return cart;
        }
    }

    public static class ProductBuilder {

        private static long nextId = 1;
        private String name = "soup";
        private Money price = new Money(1, 0);

        public static ProductBuilder aProduct() {
            return new ProductBuilder();

        }

        public ProductBuilder priced(int dollars, int cents) {
            price = new Money(dollars, cents);
            return this;
        }

        public Product savedIn(ProductRepository em) {
            Product product = new Product(nextId++, name, price);
            em.save(product);
            return product;
        }
    }
}
