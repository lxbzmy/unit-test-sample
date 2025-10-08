#include <gtest/gtest.h>
#include <sstream>
#include <iostream>
#include "../src/FizzBuzz1.hpp"

TEST(FizzBuzz1Test, fizz_buzz) {
    FizzBuzz1 fizzBuzz;
    
    std::stringstream buffer;
    std::streambuf* old = std::cout.rdbuf(buffer.rdbuf());
    
    fizzBuzz.fizzBuzz(15);
    
    std::cout.rdbuf(old);
    
    std::string expected = "1\n2\nFizz\n4\nBuzz\nFizz\n7\n8\nFizz\nBuzz\n11\nFizz\n13\n14\nFizBuzz\n";
    EXPECT_EQ(buffer.str(), expected);
}
