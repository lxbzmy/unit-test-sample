#include <cppunit/extensions/HelperMacros.h>
#include <stdexcept>
#include "../src/Stack.hpp"

class StackTest : public CppUnit::TestFixture {
    CPPUNIT_TEST_SUITE(StackTest);
    CPPUNIT_TEST(testEmptyStackSize);
    CPPUNIT_TEST(testPushIncreasesSize);
    CPPUNIT_TEST(testPopDecreasesSize);
    CPPUNIT_TEST(testPopEmptyStackThrows);
    CPPUNIT_TEST_SUITE_END();

public:
    void testEmptyStackSize() {
        Stack<std::string> stack;
        CPPUNIT_ASSERT_EQUAL((size_t)0, stack.size());
    }

    void testPushIncreasesSize() {
        Stack<std::string> stack;
        stack.push("🐶");
        CPPUNIT_ASSERT_EQUAL((size_t)1, stack.size());
    }

    void testPopDecreasesSize() {
        Stack<std::string> stack;
        stack.push("🐶");
        stack.pop();
        CPPUNIT_ASSERT_EQUAL((size_t)0, stack.size());
    }

    void testPopEmptyStackThrows() {
        Stack<std::string> stack;
        CPPUNIT_ASSERT_THROW(stack.pop(), std::out_of_range);
    }
};

CPPUNIT_TEST_SUITE_REGISTRATION(StackTest);