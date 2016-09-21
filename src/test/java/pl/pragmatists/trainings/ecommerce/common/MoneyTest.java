package pl.pragmatists.trainings.ecommerce.common;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTest {

    @Test
    public void add_money() {
        assertThat(new Money(3,50).add(new Money(4,20))).isEqualTo(new Money(7,70));
    }

}