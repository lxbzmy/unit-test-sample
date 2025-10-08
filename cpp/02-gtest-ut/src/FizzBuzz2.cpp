#include "FizzBuzz2.hpp"
#include <iostream>

void FizzBuzz2::fizzBuzz0(int n) {
    for (int i = 1; i <= n; i++) {
        std::string v = fizzBuzz(i);
        std::cout << v << std::endl;
    }
}

std::string FizzBuzz2::fizzBuzz(int i) {
    bool n是3的倍数 = i % 3 == 0;
    bool n是5的倍数 = i % 5 == 0;
    if (n是3的倍数 && n是5的倍数) {
        return "FizzBuzz";
    } else if (n是3的倍数) {
        return "Fizz";
    } else if (n是5的倍数) {
        return "Buzz";
    } else {
        return std::to_string(i);
    }
}
