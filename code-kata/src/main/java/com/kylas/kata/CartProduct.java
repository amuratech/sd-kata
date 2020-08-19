package com.kylas.kata;

public enum CartProduct {
  DOVE_SOAP(20),
  AXE_DEO(50);

  private final int unitPrice;

  CartProduct(int unitPrice) {
    this.unitPrice = unitPrice;
  }

  public int getUnitPrice() {
    return unitPrice;
  }
}
