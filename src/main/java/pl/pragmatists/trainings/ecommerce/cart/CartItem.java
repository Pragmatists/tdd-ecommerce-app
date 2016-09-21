package pl.pragmatists.trainings.ecommerce.cart;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.pragmatists.trainings.ecommerce.common.Money;
import pl.pragmatists.trainings.ecommerce.product.persistence.Product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CartItem {
    @Id
    @GeneratedValue
    private  long id;

    @ManyToOne
    private Product product;

    private int quantity;
    @ManyToOne
    Cart cart;
    private CartItem() {

    }

    public CartItem(Product product, int quantity) {
        this.product = product;
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
                .append(quantity, cartItem.quantity)
                .append(product, cartItem.product)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(product)
                .append(quantity)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("product", product)
                .append("quantity", quantity)
                .append("cart", cart)
                .toString();
    }

    public Money getPrice() {
        return product.getPrice();
    }
}
