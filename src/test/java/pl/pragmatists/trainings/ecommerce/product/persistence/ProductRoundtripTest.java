package pl.pragmatists.trainings.ecommerce.product.persistence;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.pragmatists.trainings.ecommerce.common.Money;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class ProductRoundtripTest {

    @Autowired
    private TestEntityManager em;

    @Test
    public void save_and_load_product() {
        Product product = new Product(1L, "cup", new Money(1,25));

        em.persistAndFlush(product);
        em.clear();

        Product fetched = em.find(Product.class, 1L);
        assertThat(fetched).isEqualToComparingFieldByFieldRecursively(product);
    }
}
