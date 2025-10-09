package com.foo;

/**
 * <pre>
 * 功能：输出从 1 到 n 数字的字符串表示。
 *
 * 1. 如果 n 是3的倍数，输出“Fizz”；
 * 2. 如果 n 是5的倍数，输出“Buzz”；
 * 3. 如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 * </pre>
 */
public class FizzBuzz1 {

  void fizzBuzz(int n) {
    for (int i = 1; i <= n; i++) {
      String v1;
      boolean n是3的倍数 = i % 3 == 0;
      boolean n是5的倍数 = i % 5 == 0;
      if (n是3的倍数 && n是5的倍数) {
        v1 = "FizBuzz";
      } else if (n是3的倍数) {
        v1 = "Fizz";
      } else if (n是5的倍数) {
        v1 = "Buzz";
      } else {
        v1 = String.valueOf(i);
      }
      System.out.println(v1);
    }
  }
}
