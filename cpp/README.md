# C++ 单元测试示例

使用gtest和Catch2框架的多个C++单元测试示例。

## 目录结构

- `01/` 编写一般的fizzbuzz，使用gtest

- `02/` 编写良好的FizzBuzz，使用gtest

- `04/` 使用gmock

- `05/` 使用catch2，综合02和04的内容

- `third_party/` - 第三方依赖缓存
  - `googletest/` - Google Test 框架
  - `catch2/` - Catch2 测试框架

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

## 说明

- 所有示例均使用 C++17
- 依赖已包含在 `third_party/` 目录
- 源代码保持不变，仅测试框架不同
