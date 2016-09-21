package pl.pragmatists.trainings.ecommerce.product.persistence;

public class Price {
    private int priceAsCents;

    private Price()
    { }
    public Price(int dollars, int cents) {
        this.priceAsCents = dollars * 100 + cents;
    }
}
