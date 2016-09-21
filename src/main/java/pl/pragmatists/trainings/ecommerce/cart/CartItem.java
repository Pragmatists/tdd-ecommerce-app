package pl.pragmatists.trainings.ecommerce.cart;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import pl.pragmatists.trainings.ecommerce.common.Money;
import pl.pragmatists.trainings.ecommerce.product.persistence.Product;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CartItem {

    @Id
    private long productId;

    @Embedded
    private Money price;

    private int quantity;
    @ManyToOne
    Cart cart;

    private CartItem() {

    }

    public CartItem(Product product, int quantity) {
        this.productId = product.getId();
        this.price = product.getPrice();
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CartItem cartItem = (CartItem) o;

        return new EqualsBuilder()
                .append(productId, cartItem.productId)
                .append(price, cartItem.price)
                .append(quantity, cartItem.quantity)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(productId)
                .append(price)
                .append(quantity)
                .toHashCode();
    }

    public Money getPrice() {
        return price;
    }
}
