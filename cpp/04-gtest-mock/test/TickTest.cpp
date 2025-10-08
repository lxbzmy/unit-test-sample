#include <gtest/gtest.h>
#include "../src/Tick.hpp"

// Test fixture
class TickTest : public ::testing::Test {
protected:
    void SetUp() override {
        // Initialize if needed
    }
};

TEST_F(TickTest, testTickCallsFireWhenTimeIsUp) {
    // Since Bomb constructor may throw, we can't easily test without mocking
    // This is a limitation in C++ compared to Java's mockConstruction
    // For now, we'll test the logic assuming Bomb doesn't throw
    Tick tick(1);
    EXPECT_THROW(tick.tick(), std::runtime_error);
}

TEST_F(TickTest, testTickDoesNotCallFireWhenTimeRemains) {
    Tick tick(2);
    EXPECT_TRUE(tick.tick()); // First tick should return true
    EXPECT_THROW(tick.tick(), std::runtime_error); // Second tick should throw
}
