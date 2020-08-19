package com.kylas.kata.cart;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Cart {

  Map<Product, Integer> items = new HashMap<>();

  public String addProduct(Product product) {
    return this.addProduct(product, 1);
  }

  public String addProduct(Product product, int qty) {

    Integer quentity = items.getOrDefault(product, 0);
    quentity += qty;
    items.put(product, quentity);
    return quentity + " " + product.getName();
  }

  public long getPrice() {
    return items.keySet().stream()
        .map(product1 -> product1.getPrice() * items.get(product1))
        .mapToLong(value -> value).sum();

  }

  public String getProduct() {
    return items.keySet().stream()
        .map(product -> items.get(product) + " " + product.getName())
        .collect(Collectors.joining(" and "));
  }
}
