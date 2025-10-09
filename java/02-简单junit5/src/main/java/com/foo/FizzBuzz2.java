package com.foo;

/**
 * <pre>
 * 功能：输出从 1 到 n 数字的字符串表示。
 *
 * 1. 如果 n 是3的倍数，输出“Fizz”；
 * 2. 如果 n 是5的倍数，输出“Buzz”；
 * 3. 如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 * </pre>
 */
public class FizzBuzz2 {

  void fizzBuzz0(int n) {
    for (int i = 1; i <= n; i++) {
      String v = fizzBuzz(i);
      System.out.println(v);
    }
  }

  public String fizzBuzz(int i) {
    boolean n是3的倍数 = i % 3 == 0;
    boolean n是5的倍数 = i % 5 == 0;
    if (n是3的倍数 && n是5的倍数) {
      return ("FizzBuzz");
    } else if (n是3的倍数) {
      return ("Fizz");
    } else if (n是5的倍数) {
      return ("Buzz");
    } else {
      return String.valueOf(i);
    }
  }
}
