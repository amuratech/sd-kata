package com.kylas.kata.cart;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CartTest {

  @Test
  public void givenProduct_shouldAddProduct() {
    Product doveSoap = new Product("Dove soap", 20);
    Cart cart = new Cart();
    cart.addProduct(doveSoap);
    long price = cart.getPrice();
    assertThat(price).isEqualTo(20);
  }

  @Test
  public void givenProduct_tryToAdd_shouldReturnResponse() {
    Product doveSoap = new Product("Dove soap", 20);
    Cart cart = new Cart();
    String productResponse = cart.addProduct(doveSoap);
    assertThat(productResponse).isEqualTo("1 Dove soap");
  }

  @Test
  public void given2Product_shouldGetTotalAs40() {
    Product doveSoap = new Product("Dove soap", 20);
    Cart cart = new Cart();
    cart.addProduct(doveSoap, 2);
    assertThat(cart.getPrice()).isEqualTo(40);
    assertThat(cart.getProduct()).isEqualTo("2 Dove soap");

  }

  @Test
  public void given2Product_tryToAdd1More_shouldGetTotalAs60() {
    Cart cart = new Cart();
    Product doveSoap = new Product("Dove soap", 20);
    cart.addProduct(doveSoap, 2);
    cart.addProduct(doveSoap, 1);
    long price = cart.getPrice();
    assertThat(price).isEqualTo(60);
    assertThat(cart.getProduct()).isEqualTo("3 Dove soap");
  }

  @Test
  public void given2SoapAnd3Deo_shouldGetPriceAs190() {
    Cart cart = new Cart();
    Product doveSoap = new Product("Dove soap", 20);
    Product axeDeo = new Product("Axe Deo", 50);
    cart.addProduct(doveSoap, 2);
    cart.addProduct(axeDeo, 3);
    assertThat(cart.getPrice()).isEqualTo(190);
    assertThat(cart.getProduct()).isEqualTo("2 Dove soap and 3 Axe Deo");
  }

}
