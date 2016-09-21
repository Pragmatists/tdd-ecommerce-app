package pl.pragmatists.trainings.ecommerce.addtocart.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class CartJsonTest {
    @Autowired
    private JacksonTester<CartJson> json;

    @Test
    public void deserialize() throws Exception {
        String content = new JSONObject()
                .put("items", new JSONArray()
                        .put(new JSONObject()
                                .put("productId", 1L)
                                .put("quantity", 3)
                        )
                ).toString();
        CartJson expected = new CartJson();
        expected.items = new ArrayList<>();
        expected.items.add(new CartItemJson(1L, 3));
        assertThat(json.parse(content)).isEqualToComparingFieldByFieldRecursively(expected);

    }
}
