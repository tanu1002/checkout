package com.hmrc.checkout;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Offers {


    private Map<String, Offer> offers = new HashMap<String, Offer>(){{
        put("Apple", (count, price) -> buyOneGetOneFree(count, price));
        put("Orange", (count, price) -> threeForThePriceOfTwo(count, price));
    }};

    private BigDecimal threeForThePriceOfTwo(Integer count, BigDecimal price) {
        return new BigDecimal((price.doubleValue() * 2) * (count / 3) + (price.doubleValue() * (count % 3)));
    }

    private BigDecimal buyOneGetOneFree(Integer count, BigDecimal price) {
        return new BigDecimal(price.doubleValue() * (count / 2) + price.doubleValue() * (count % 2));
    }

    public BigDecimal applyOffer(String itemName, Integer count, BigDecimal price) {
        if (offers.containsKey(itemName)) {
            return offers.get(itemName).applyOffer(count, price);
        }
        return new BigDecimal(price.doubleValue() * count);
    }

}
