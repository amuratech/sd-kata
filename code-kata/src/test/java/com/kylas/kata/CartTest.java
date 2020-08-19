package com.kylas.kata;

import static com.kylas.kata.CartProduct.AXE_DEO;
import static com.kylas.kata.CartProduct.DOVE_SOAP;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CartTest {

  @Test
  public void givenAShoppingCart_shouldBeEmptyOnStart() {
    Cart cart = new Cart();
    assertThat(cart.isEmpty()).isTrue();
  }

  @Test
  public void givenAShoppingCart_shouldBeAbleToAddAProduct() {
    Cart cart = new Cart();

    boolean isAdded = cart.addProduct(DOVE_SOAP);
    assertThat(isAdded).isTrue();
    assertThat(cart.hasProductWithQuantity(DOVE_SOAP, 1)).isTrue();
  }

  @Test
  public void givenAShoppingCart_shouldAddMultipleProducts() {
    Cart cart = new Cart();

    cart.addProduct(DOVE_SOAP);
    cart.addProduct(DOVE_SOAP);

    assertThat(cart.hasProductWithQuantity(DOVE_SOAP, 2)).isTrue();
  }

  @Test
  public void givenAShoppingCartWithProducts_shouldGetTotalPrice() {
    Cart cart = new Cart();

    cart.addProduct(DOVE_SOAP);
    cart.addProduct(DOVE_SOAP);

    assertThat(cart.getTotalPrice()).isEqualTo(40);
  }

  @Test
  public void givenAShoppingCartWithMultipleProducts_shouldGetTotalPrice() {
    Cart cart = new Cart();

    cart.addProduct(DOVE_SOAP);
    cart.addProduct(DOVE_SOAP);
    cart.addProduct(DOVE_SOAP);

    assertThat(cart.getTotalPrice()).isEqualTo(60);
    assertThat(cart.hasProductWithQuantity(DOVE_SOAP, 3)).isTrue();
  }

  @Test
  public void givenShoppingCartWith2DoveSoapsAnd3AxeDeos_shouldCalculateTotalPrice() {
    Cart cart = new Cart();

    cart.addProduct(DOVE_SOAP, 2);
    cart.addProduct(AXE_DEO, 3);

    assertThat(cart.getTotalPrice()).isEqualTo(190);
    assertThat(cart.hasProductWithQuantity(DOVE_SOAP, 2)).isTrue();
    assertThat(cart.hasProductWithQuantity(AXE_DEO, 3)).isTrue();

  }
}
