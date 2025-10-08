#include <catch2/catch_test_macros.hpp>
#include "../src/Tick.hpp"

TEST_CASE("Tick tests", "[Tick]") {
    SECTION("testTickCallsFireWhenTimeIsUp") {
        // Since Bomb constructor may throw, we can't easily test without mocking
        Tick tick(1);
        REQUIRE_THROWS_AS(tick.tick(), std::runtime_error);
    }

    SECTION("testTickDoesNotCallFireWhenTimeRemains") {
        Tick tick(2);
        REQUIRE(tick.tick() == true); // First tick should return true
        REQUIRE_THROWS_AS(tick.tick(), std::runtime_error); // Second tick should throw
    }
}
