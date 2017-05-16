package com.hmrc.checkout;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class CheckoutTest {


    private Checkout checkout = new Checkout();

    @Test
    public void canCheckoutAnApple() throws Exception {
        List<String> inputItems =  Arrays.asList("Apple");
        String checkoutReciept = checkout.price(inputItems);
        assertThat(checkoutReciept, Is.is("£0.60"));
    }

    @Test
    public void canCheckoutMultipleItemsContainingApplesAndOranges() throws Exception {
        List<String> inputItems =  Arrays.asList("Apple", "Apple", "Orange", "Apple");
        String checkoutReciept = checkout.price(inputItems);
        assertThat(checkoutReciept, Is.is("£2.05"));
    }

    @Test(expected = RuntimeException.class)
    public void shouldComplainOnUnknownItems() throws Exception {
        List<String> inputItems =  Arrays.asList("Guava", "Orange");
        checkout.price(inputItems);
    }

}