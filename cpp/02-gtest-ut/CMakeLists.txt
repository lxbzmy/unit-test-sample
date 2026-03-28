cmake_minimum_required(VERSION 3.10)
project(UnitTestSampleCpp02)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

option(ENABLE_COVERAGE "Enable gcov/lcov coverage instrumentation" OFF)
if(ENABLE_COVERAGE)
    if(NOT CMAKE_BUILD_TYPE STREQUAL "Debug")
        message(WARNING "ENABLE_COVERAGE is best used with CMAKE_BUILD_TYPE=Debug")
    endif()
    if(CMAKE_CXX_COMPILER_ID MATCHES "GNU|Clang")
        add_compile_options(--coverage -O0 -g)
        add_link_options(--coverage)
    endif()
endif()

# Add gtest as subdirectory
set(BUILD_SHARED_LIBS OFF CACHE BOOL "" FORCE)
add_subdirectory(../third_party/googletest googletest)

# Source files
set(SOURCES
    src/FizzBuzz2.cpp
)

# Test files
set(TEST_SOURCES
    test/FizzBuzz2Test.cpp
)

# Create library
add_library(${PROJECT_NAME} ${SOURCES})

# Create test executable
add_executable(${PROJECT_NAME}Test ${TEST_SOURCES})
target_link_libraries(${PROJECT_NAME}Test ${PROJECT_NAME} gtest_main)

# Enable testing
enable_testing()
add_test(NAME ${PROJECT_NAME}Test COMMAND ${PROJECT_NAME}Test)
