package com.hmrc.checkout;

import java.math.BigDecimal;

@FunctionalInterface
public interface Offer {
    BigDecimal applyOffer(Integer quantity, BigDecimal price);
}
