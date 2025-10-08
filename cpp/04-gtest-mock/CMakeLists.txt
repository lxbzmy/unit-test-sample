cmake_minimum_required(VERSION 3.10)
project(UnitTestSampleCpp04)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Add gtest as subdirectory
set(BUILD_SHARED_LIBS OFF CACHE BOOL "" FORCE)
add_subdirectory(../third_party/googletest googletest)

# Source files
set(SOURCES
    src/Bomb.cpp
    src/Tick.cpp
    src/Money.cpp
    src/RateService.cpp
    src/CurrencyCalculator.cpp
)

# Test files
set(TEST_SOURCES
    test/TickTest.cpp
    test/CurrencyCalculatorTest.cpp
)

# Create library
add_library(${PROJECT_NAME} ${SOURCES})

# Create test executable
add_executable(${PROJECT_NAME}Test ${TEST_SOURCES})
target_link_libraries(${PROJECT_NAME}Test ${PROJECT_NAME} gtest_main gmock)

# Enable testing
enable_testing()
add_test(NAME ${PROJECT_NAME}Test COMMAND ${PROJECT_NAME}Test)
