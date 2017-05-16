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
        String checkoutReceipt = checkout.price(inputItems);
        assertThat(checkoutReceipt, Is.is("£0.60"));
    }

    @Test
    public void canCheckoutMultipleItemsContainingApplesAndOranges() throws Exception {
        List<String> inputItems =  Arrays.asList("Apple", "Apple", "Orange", "Apple");
        String checkoutReceipt = checkout.price(inputItems);
        assertThat(checkoutReceipt, Is.is("£1.45"));
    }

    @Test(expected = RuntimeException.class)
    public void shouldComplainOnUnknownItems() throws Exception {
        List<String> inputItems =  Arrays.asList("Guava", "Orange");
        checkout.price(inputItems);
    }

    @Test
    public void applyOfferOnAppleWithBuyOneGetOneFree() throws Exception {
        List<String> inputItems =  Arrays.asList("Apple", "Apple");
        String checkoutReceipt = checkout.price(inputItems);
        assertThat(checkoutReceipt, Is.is("£0.60"));
    }

    @Test
    public void applyOfferThreeForPriceOfTwoOnOranges() throws Exception {
        List<String> inputItems =  Arrays.asList("Orange", "Orange", "Orange");
        String checkoutReceipt = checkout.price(inputItems);
        assertThat(checkoutReceipt, Is.is("£0.50"));
    }

    @Test
    public void applyAllOffersForOrangesAndApple() throws Exception {
        List<String> inputItems =  Arrays.asList("Orange","Apple", "Orange", "Orange", "Apple");
        String checkoutReceipt = checkout.price(inputItems);
        assertThat(checkoutReceipt, Is.is("£1.10"));
    }
}