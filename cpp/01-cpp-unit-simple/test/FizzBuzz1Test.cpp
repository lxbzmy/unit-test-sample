#include <cppunit/extensions/HelperMacros.h>
#include <sstream>
#include <iostream>
#include "../src/FizzBuzz1.hpp"

class FizzBuzz1Test : public CppUnit::TestFixture {
    CPPUNIT_TEST_SUITE(FizzBuzz1Test);
    CPPUNIT_TEST(testFizzBuzz);
    CPPUNIT_TEST_SUITE_END();

public:
    void testFizzBuzz() {
        FizzBuzz1 fizzBuzz;
        
        std::stringstream buffer;
        std::streambuf* old = std::cout.rdbuf(buffer.rdbuf());
        
        fizzBuzz.fizzBuzz(15);
        
        std::cout.rdbuf(old);
        
        std::string expected = "1\n2\nFizz\n4\nBuzz\nFizz\n7\n8\nFizz\nBuzz\n11\nFizz\n13\n14\nFizBuzz\n";
        CPPUNIT_ASSERT_EQUAL(expected, buffer.str());
    }
};

CPPUNIT_TEST_SUITE_REGISTRATION(FizzBuzz1Test);