# 01-cpp-unit-simple

使用 CppUnit 进行简单的单元测试示例。

cppunit很久不更新了（2008），使用的包是：https://downloads.sourceforge.net/project/cppunit/cppunit/1.12.1/cppunit-1.12.1.tar.gz

## 构建和运行

CppUnit的构建已封装到CMake中，

1. 构建项目：
   ```bash
   cd 01-cpp-unit-simple
   cmake -S . -B target
   cmake --build target
   ```

2. 运行测试：
   ```bash
   ctest --output-on-failure --test-dir target
   ```

## 说明

- `src/` 包含源代码：`FizzBuzz1` 和 `Stack` 类。
- `test/` 包含使用 CppUnit 的测试代码。
- 使用 CppUnit 的 HelperMacros 来定义测试套件。
- CMake 会自动处理 CppUnit 的下载和构建（静态库）。


