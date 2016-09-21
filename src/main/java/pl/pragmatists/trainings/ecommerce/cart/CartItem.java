package pl.pragmatists.trainings.ecommerce.cart;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class CartItem {
    @Id
    private  long id;
    private long productId;
    private int quantity;
    @ManyToOne
    Cart cart;

    private CartItem() {

    }

    public CartItem(long productId, int quantity) {

        this.productId = productId;
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
        return id == cartItem.id &&
                productId == cartItem.productId &&
                quantity == cartItem.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, quantity);
    }
}
