package com.kylas.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cart {

  private final List<CartProduct> products;

  public Cart() {
    this.products = new ArrayList<>();
  }

  public boolean isEmpty() {
    return true;
  }

  public boolean addProduct(CartProduct product) {
    products.add(product);
    return true;
  }

  public boolean hasProductWithQuantity(CartProduct product, int quantity) {
    List<CartProduct> availableProducts = products
        .stream()
        .filter(p -> p.equals(product))
        .collect(Collectors.toList());

    return availableProducts.size() == quantity;
  }

  public int getTotalPrice() {
    return products
        .stream()
        .map(CartProduct::getUnitPrice)
        .reduce(0, Integer::sum);
  }

  public boolean addProduct(CartProduct product, int quantity) {
    for(int i = 0; i < quantity; i++) {
      products.add(product);
    }
    return true;
  }
}
