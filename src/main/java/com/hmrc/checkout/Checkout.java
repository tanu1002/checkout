package com.hmrc.checkout;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class Checkout {

    private Map<String, BigDecimal> prices = new HashMap<String, BigDecimal>(){{
        put("Apple", new BigDecimal("0.60"));
        put("Orange", new BigDecimal("0.25"));
    }};

    public String price(List<String> inputItems) {
        Optional<BigDecimal> output = inputItems.stream().map(item -> getPrice(item)).reduce((x, y) -> x.add(y));
        if (output.isPresent()) {
            return "£"  + output.get();
        } else {
            throw new RuntimeException("Unable to checkout");
        }
    }

    private BigDecimal getPrice(String item) {
        return prices.get(item);
    }
}
