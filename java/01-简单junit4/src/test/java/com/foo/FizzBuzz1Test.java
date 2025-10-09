package com.foo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FizzBuzz1Test {

  @Test
  public void fizz_buzz() throws Exception {
    FizzBuzz1 fizzBuzz = new FizzBuzz1();
    fizzBuzz.fizzBuzz(15);
    assertThat(sb.toString(), is("1\n" +
        "2\nFizz\n4\nBuzz\nFizz\n" +
        "7\n8\n" +
        "Fizz\nBuzz\n" +
        "11\nFizz\n" +
        "13\n14\n" +
        "FizBuzz\n"));
  }

  StringBuilder sb = new StringBuilder();
  PrintStream origin;

  @Before
  public void wrap_out() {
    origin = System.out;
    PrintStream s = new PrintStream(System.out) {
      public void println(int x) {
        sb.append(x).append('\n');
      };

      public void println(String x) {
        sb.append(x).append('\n');
      };
    };
    System.setOut(s);
  }

  @After
  public void unwrap() {
    System.setOut(origin);
  }
}
