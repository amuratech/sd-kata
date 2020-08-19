package com.kylas.kata;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {

  @Test
  public void testSetup(){
    Assertions.assertThat(2+2).isEqualTo(4);
  }
}