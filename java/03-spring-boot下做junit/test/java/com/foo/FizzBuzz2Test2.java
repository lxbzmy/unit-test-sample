package com.foo;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FizzBuzz2Test2 {

  FizzBuzz2 sut = new FizzBuzz2();

  @Test
  void when_1_then_1() throws Exception {
    assertEquals("1", sut.fizzBuzz(1));
  }

  @Test
  void _3_then_fizz() throws Exception {
    //assertEquals的阅读顺序是和人理解是相反的，因此日常用的其他断言框架会多一些：hamcrest, assertj
    assertThat(sut.fizzBuzz(3), is("Fizz"));
  }

  @Test
  void _5_then_buzz() throws Exception {
    assertThat(sut.fizzBuzz(5), is("Buzz"));
  }

  @Test
  void _15_then_fizzbuzz() throws Exception {
    assertThat(sut.fizzBuzz(15), is("FizzBuzz"));
  }
}
