package com.foo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import uk.org.webcompere.systemstubs.rules.SystemOutRule;


import org.junit.Rule;
import org.junit.Test;

public class FizzBuzz1AltTest {


  @Rule
  public SystemOutRule systemOutRule = new SystemOutRule();

  @Test
  public void fizz_buzz() throws Exception {
    FizzBuzz1 fizzBuzz = new FizzBuzz1();
    fizzBuzz.fizzBuzz(15);
    
    assertThat(systemOutRule.getText(), is("1\n" +
        "2\nFizz\n4\nBuzz\nFizz\n" +
        "7\n8\n" +
        "Fizz\nBuzz\n" +
        "11\nFizz\n" +
        "13\n14\n" +
        "FizBuzz\n"));
  }

}
