#include "FizzBuzz1.hpp"

void FizzBuzz1::fizzBuzz(int n) {
    for (int i = 1; i <= n; i++) {
        std::string v1;
        bool n是3的倍数 = i % 3 == 0;
        bool n是5的倍数 = i % 5 == 0;
        if (n是3的倍数 && n是5的倍数) {
            v1 = "FizBuzz";
        } else if (n是3的倍数) {
            v1 = "Fizz";
        } else if (n是5的倍数) {
            v1 = "Buzz";
        } else {
            v1 = std::to_string(i);
        }
        std::cout << v1 << std::endl;
    }
}
