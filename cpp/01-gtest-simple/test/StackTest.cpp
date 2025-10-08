#include <gtest/gtest.h>
#include <stdexcept>
#include "../src/Stack.hpp"

TEST(StackTest, 空堆栈的size等于0) {
    Stack<std::string> stack;
    EXPECT_EQ(stack.size(), 0);
}

TEST(StackTest, 放入一个对象后size加1) {
    Stack<std::string> stack;
    stack.push("🐶");
    EXPECT_EQ(stack.size(), 1);
}

TEST(StackTest, pop一个对象后size减1) {
    Stack<std::string> stack;
    stack.push("🐶");
    stack.pop();
    EXPECT_EQ(stack.size(), 0);
}

TEST(StackTest, 空栈pop报异常) {
    Stack<std::string> stack;
    EXPECT_THROW(stack.pop(), std::out_of_range);
}
