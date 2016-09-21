package pl.pragmatists.trainings.ecommerce.cart;


import pl.pragmatists.trainings.ecommerce.common.Money;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue
    private long id;
    private long userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartItem> items;

    private Cart() {

    }

    public Cart(Long userId) {
        this.userId = userId;
    }

    public long userId() {
        return userId;
    }

    public List<CartItem> items() {
        return items;
    }

    public Cart withItems(List<CartItem> items) {
        this.items = new ArrayList<>();
        items.forEach(this::add);
        return this;
    }

    private void add(CartItem cartItem) {
        cartItem.cart = this;
        items.add(cartItem);
    }

    public Money total() {
        return items.stream().map(CartItem::getPrice).reduce(new Money(0,0), Money::add);
    }
}
