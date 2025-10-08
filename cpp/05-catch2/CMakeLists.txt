cmake_minimum_required(VERSION 3.10)
project(UnitTestSampleCpp05)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Add Catch2
add_subdirectory(../third_party/catch2 catch2)
include_directories(../third_party/catch2/single_include)

# Source files
set(SOURCES
    src/FizzBuzz2.cpp
    src/Bomb.cpp
    src/Tick.cpp
    src/Money.cpp
    src/CurrencyCalculator.cpp
)

# Test files
set(TEST_SOURCES
    test/FizzBuzz2Test.cpp
    test/TickTest.cpp
    test/CurrencyCalculatorTest.cpp
)

# Create library
add_library(${PROJECT_NAME} ${SOURCES})

# Create test executable
add_executable(${PROJECT_NAME}Test ${TEST_SOURCES})
target_link_libraries(${PROJECT_NAME}Test ${PROJECT_NAME} Catch2::Catch2WithMain)

# Enable testing
enable_testing()
add_test(NAME ${PROJECT_NAME}Test COMMAND ${PROJECT_NAME}Test)
