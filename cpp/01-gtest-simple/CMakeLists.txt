cmake_minimum_required(VERSION 3.16)
project(UnitTestSampleCpp)

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
add_test(
    NAME ${PROJECT_NAME}Test
    COMMAND ${PROJECT_NAME}Test --gtest_output=xml:${PROJECT_NAME}-report.xml
)
# cmake 3.16，不支持 ctest输出report，要通过底层框架输出报告
# 再高版本时ctest支持输出报告了，可以改成下面的方式
# include(GoogleTest)
# gtest_discover_tests(${PROJECT_NAME}Test
#   EXTRA_ARGS --gtest_output=xml:${PROJECT_NAME}-report.xml
# )
# 如果直接用这个形式，xml内容不全。
# 所以很纠结 3.16 还差些
# - addtest ，ctest只有一个case，日志不对
# - gtest_discover_tests + EXTRA_ARGS ：日志对，xml不对
# - gtest_discover_tests + EXTRA_ARGS + GTEST_OUTPUT环境变量：日志对，另外输出的xml对
# - 直接运行XXTest程序输出是对的