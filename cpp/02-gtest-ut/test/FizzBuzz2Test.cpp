#include <gtest/gtest.h>
#include "../src/FizzBuzz2.hpp"

class FizzBuzz2Test : public ::testing::Test {
protected:
    FizzBuzz2 sut;
};

TEST_F(FizzBuzz2Test, when_1_then_1) {
    EXPECT_EQ("1", sut.fizzBuzz(1));
}

TEST_F(FizzBuzz2Test, _3_then_fizz) {
    EXPECT_EQ("Fizz", sut.fizzBuzz(3));
}

TEST_F(FizzBuzz2Test, _5_then_buzz) {
    EXPECT_EQ("Buzz", sut.fizzBuzz(5));
}

TEST_F(FizzBuzz2Test, _15_then_fizzbuzz) {
    EXPECT_EQ("FizzBuzz", sut.fizzBuzz(15));
}
