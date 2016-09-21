package pl.pragmatists.trainings.ecommerce.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Money {
    private int moneyAsCents;

    private Money() {
    }

    public Money(int dollars, int cents) {
        this.moneyAsCents = dollars * 100 + cents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Money money = (Money) o;

        return new EqualsBuilder()
                .append(moneyAsCents, money.moneyAsCents)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(moneyAsCents)
                .toHashCode();
    }

    public Money add(Money other) {
        int newValue = this.moneyAsCents + other.moneyAsCents;
        return new Money(newValue / 100, newValue % 100);
    }

    @Override
    public String toString() {
        return moneyAsCents/100 + ","+moneyAsCents % 100;
    }
}
