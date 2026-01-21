cmake_minimum_required(VERSION 3.10)
project(UnitTestSampleCpp)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

include(ExternalProject)

# Build CppUnit from local tar.gz
ExternalProject_Add(cppunit
  URL file://${CMAKE_SOURCE_DIR}/../third_party/cppunit-1.12.1.tar.gz
  DOWNLOAD_EXTRACT_TIMESTAMP TRUE
  PREFIX ${CMAKE_BINARY_DIR}/cppunit
  CONFIGURE_COMMAND bash <SOURCE_DIR>/configure --prefix=<INSTALL_DIR> --enable-static --disable-shared
  BUILD_COMMAND make
  INSTALL_COMMAND make install
  BUILD_BYPRODUCTS <INSTALL_DIR>/lib/libcppunit.a
)

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
    test/TestRunner.cpp
)

# Create library
add_library(${PROJECT_NAME} ${SOURCES})

# Create test executable
add_executable(${PROJECT_NAME}Test ${TEST_SOURCES})
add_dependencies(${PROJECT_NAME}Test cppunit)
target_include_directories(${PROJECT_NAME}Test PRIVATE ${CMAKE_BINARY_DIR}/cppunit/include)
target_link_libraries(${PROJECT_NAME}Test ${PROJECT_NAME} ${CMAKE_BINARY_DIR}/cppunit/lib/libcppunit.a)

# Enable testing
enable_testing()
add_test(NAME ${PROJECT_NAME}Test COMMAND ${PROJECT_NAME}Test)