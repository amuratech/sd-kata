package com.kylas.kata.cart;

import java.util.Objects;

public class Product {

  private String name;
  private long price;

  public Product(String name, long price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public long getPrice() {
    return price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(name, product.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
