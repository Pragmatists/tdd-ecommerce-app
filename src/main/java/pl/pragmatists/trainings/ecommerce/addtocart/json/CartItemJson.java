package pl.pragmatists.trainings.ecommerce.addtocart.json;

public class CartItemJson {
    public long productId;
    public int quantity;

    public CartItemJson(){

    }

    public CartItemJson(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
