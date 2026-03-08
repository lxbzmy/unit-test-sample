# C++ 单元测试示例

使用gtest和Catch2框架的多个C++单元测试示例。

试验在ubuntu24.04上通过： gcc 13、cmake 3.28。

## 目录结构

- `01-gtest/` 编写一般的fizzbuzz，使用gtest

- `01-cpp-unit-simple/` 用 cppunit编写的测试，cppunit 已经过时

- `02/` 编写良好的FizzBuzz，使用gtest

- `04/` 使用gmock

- `05/` 使用catch2，综合02和04的内容

- `third_party/` - 第三方依赖缓存
  - `googletest/` - Google Test 框架，引用git库
  - `catch2/` - Catch2 测试框架，引用git库
  - `cppunit-1.12.1.tar.gz` - cppunit缓存（2008版）


## 构建与运行

每个子目录都是独立的 CMake 项目。构建方法如下：

```bash
cd <子目录>
cmake .
make
make test  # 运行测试
```

或直接运行测试可执行文件：
```bash
./<ProjectName>Test
```

## 使用的测试框架

- **Google Test (gtest)**：功能丰富的测试框架，支持 mock
- **Catch2**：现代、仅头文件的测试框架，支持 BDD 风格语法
- **CPPUnit** 有点老的框架，仅用作对比

## 说明

- 所有示例均使用 C++17
- 依赖已包含在 `third_party/` 目录
- 源代码保持不变，仅测试框架不同


## 如何输出junit report

以02举例

```bash
cd cpp/02-gtest-ut

cmake -S . -B target
cmake --build target
#方式1，case没有细分
ctest --test-dir target --output-junit TEST-1.xml --verbose
#方式二 case细分
cd target
./UnitTestSampleCpp02Test --gtest_output=xml:TEST-2.xml
```

## 备注

1. ubuntu20 要用gcc-10

```bash
apt install gcc-10 g++-10
cmake -S . -B target -DCMAKE_C_COMPILER=gcc-10 -DCMAKE_CXX_COMPILER=g++-10
cmake --build target
cmake -E chdir target-gcc10 ctest --output-on-failure
```

2. ubuntu20 cmake 3.16 中的ctest不支持 --test-dir <dir>

```bash
cmake -E chdir target-gcc10 ctest --output-on-failure
```