cmake_minimum_required(VERSION 3.10)
project(UnitTestSampleCpp)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Ensure UTF-8 source encoding for test names/messages
if(MSVC)
    add_compile_options(/utf-8)
else()
    add_compile_options(-finput-charset=UTF-8)
endif()

# Add gtest as subdirectory
set(BUILD_SHARED_LIBS OFF CACHE BOOL "" FORCE)
add_subdirectory(../third_party/googletest googletest)

# Source files
set(SOURCES
    src/FizzBuzz1.cpp
    src/FizzBuzz1.hpp
    src/Stack.hpp
)

# Test files
set(TEST_SOURCES
    test/FizzBuzz1Test.cpp
    test/StackTest.cpp
)

# Create library
add_library(${PROJECT_NAME} ${SOURCES})

# Create test executable
add_executable(${PROJECT_NAME}Test ${TEST_SOURCES})
target_link_libraries(${PROJECT_NAME}Test ${PROJECT_NAME} gtest_main)

# Enable testing
enable_testing()
include(GoogleTest)
gtest_discover_tests(${PROJECT_NAME}Test)
