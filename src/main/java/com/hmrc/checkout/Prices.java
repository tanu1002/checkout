package com.hmrc.checkout;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class Prices {

    private Map<String, BigDecimal> prices = new HashMap<String, BigDecimal>(){{
        put("Apple", new BigDecimal("0.60"));
        put("Orange", new BigDecimal("0.25"));
    }};

    public BigDecimal getPrice(String itemName) {
        return prices.get(itemName);
    }

}
