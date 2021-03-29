package com.epam.training.webshop.core.cart;

import com.epam.training.webshop.core.finance.bank.Bank;
import com.epam.training.webshop.core.finance.money.Money;
import com.epam.training.webshop.core.product.model.Product;

import java.util.*;

public class Cart {

    private List<Product> productList;
    private final Bank bank;

    public static Cart of(Bank bank, Product... products) {
        return new Cart(bank, new LinkedList<Product>(Arrays.asList(products.clone())));
    }

    public Cart(Bank bank) {
        this(bank, new LinkedList<>());
    }

    Cart(Bank bank, List<Product> productList) {
        this.bank = bank;
        this.productList = productList;
    }

    public void add(Product product) {
        productList.add(product);
    }

    public Money getAggregatedNetPrice() {
        Money aggregatedPrice = new Money(0D, Currency.getInstance("HUF"));
        for (Product product : productList) {
            aggregatedPrice = aggregatedPrice.add(product.getNetPrice(), bank);
        }
        return aggregatedPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(productList, cart.productList) && Objects.equals(bank, cart.bank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productList, bank);
    }
}