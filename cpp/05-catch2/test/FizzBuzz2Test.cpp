#define CATCH_CONFIG_MAIN
#include <catch2/catch_test_macros.hpp>
#include "../src/FizzBuzz2.hpp"

TEST_CASE("FizzBuzz2 tests", "[FizzBuzz2]") {
    FizzBuzz2 sut;

    SECTION("when 1 then 1") {
        REQUIRE(sut.fizzBuzz(1) == "1");
    }

    SECTION("3 then Fizz") {
        REQUIRE(sut.fizzBuzz(3) == "Fizz");
    }

    SECTION("5 then Buzz") {
        REQUIRE(sut.fizzBuzz(5) == "Buzz");
    }

    SECTION("15 then FizzBuzz") {
        REQUIRE(sut.fizzBuzz(15) == "FizzBuzz");
    }
}
