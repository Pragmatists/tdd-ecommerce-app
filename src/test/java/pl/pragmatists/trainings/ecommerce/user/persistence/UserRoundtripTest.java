package pl.pragmatists.trainings.ecommerce.user.persistence;


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
public class UserRoundtripTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void save_and_load_user() {
        User product = new User(1L, "Anna");

        userRepository.save(product);

        em.flush();
        em.clear();
        User fetched = userRepository.findOne(1L);
        assertThat(fetched).isEqualToComparingFieldByField(product);
    }
}
