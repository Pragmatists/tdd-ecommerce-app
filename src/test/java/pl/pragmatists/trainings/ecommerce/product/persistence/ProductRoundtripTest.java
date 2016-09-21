package pl.pragmatists.trainings.ecommerce.product.persistence;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class ProductRoundtripTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void save_and_load_product() {
        Product product = new Product(1L, "cup");

        productRepository.save(product);

        em.flush();
        Product fetched = productRepository.findOne(1L);
        assertThat(fetched).isEqualToComparingFieldByField(product);
    }
}
