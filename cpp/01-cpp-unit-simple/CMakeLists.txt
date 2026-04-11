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

include(ExternalProject)

# Build CppUnit from local tar.gz
set(CPPUNIT_TARBALL "${CMAKE_CURRENT_LIST_DIR}/../third_party/cppunit-1.12.1.tar.gz")
set(CPPUNIT_CONFIGURE_ENV)
if(UNIX AND NOT APPLE)
  # Old cppunit's plugin tester link line may put -ldl too early; keep it with no-as-needed.
  set(CPPUNIT_CONFIGURE_ENV ${CMAKE_COMMAND} -E env LIBS=-ldl LDFLAGS=-Wl,--no-as-needed)
endif()
ExternalProject_Add(cppunit
  URL "${CPPUNIT_TARBALL}"
  PREFIX ${CMAKE_BINARY_DIR}/cppunit
  CONFIGURE_COMMAND ${CPPUNIT_CONFIGURE_ENV} CC=${CMAKE_C_COMPILER} CXX=${CMAKE_CXX_COMPILER} bash <SOURCE_DIR>/configure --prefix=<INSTALL_DIR> --enable-static --disable-shared
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
add_test(
  NAME ${PROJECT_NAME}Test
  COMMAND ${PROJECT_NAME}Test --xml ${PROJECT_NAME}-report.xml
)