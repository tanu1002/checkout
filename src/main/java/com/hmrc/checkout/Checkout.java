package com.hmrc.checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


public class Checkout {


    private final Prices prices = new Prices();
    private final Offers offers = new Offers();



    public String price(List<String> inputItems) {
        Optional<BigDecimal> output = calculatePrice(inputItems);
        if (output.isPresent()) {
            return "£"  + output.get();
        } else {
            throw new RuntimeException("Unable to checkout");
        }
    }

    private Optional<BigDecimal> calculatePrice(List<String> inputItems) {
        Map<String, List<String>> groupedByItem = inputItems.stream().collect(Collectors.groupingBy(item -> item));
        Map<String, Integer> groupedByCount = groupedByItem.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue().size()));

        return groupedByCount.entrySet().stream().map(entry -> calculateWithOffer(entry)).reduce((x, y) -> x.add(y));

    }

    private BigDecimal calculateWithOffer(Map.Entry<String, Integer> entry) {
        return offers.applyOffer(entry.getKey(), entry.getValue(), prices.getPrice(entry.getKey())).setScale(2, RoundingMode.CEILING);
    }

}
