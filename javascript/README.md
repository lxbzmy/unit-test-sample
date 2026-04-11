# 运行说明（JavaScript 单元测试示例）

## 前提条件

- 安装 Node.js（推荐 18,20），或使用 Volta 来自动引用 Node 版本。
- 在 `javascript` 目录下运行命令。

## 项目结构

每个子目录是一个独立的测试示例项目，包含对应框架的最简配置：

- `02-jest/` - Jest 测试框架示例
- `02-mocha/` - Mocha + Chai 测试框架示例
- `02-jasmine/` - Jasmine 测试框架示例
- `02-vitest/` - Vitest 测试框架示例

每个示例项目目录内通常包含：

- `package.json` - 依赖与 `npm test` 脚本
- `src/` - SUT（被测试代码）
- `test/` 或 `spec/` - 测试文件

## 运行

以每个项目为单元运行测试：

### 01) Jest

```bash
cd javascript/02-jest
npm install
npm test
```

### 02) Mocha + Chai

```bash
cd javascript/02-mocha
npm install
npm test
```

### 03) Jasmine

```bash
cd javascript/02-jasmine
npm install
npm test
```

### 04) Vitest

```bash
cd javascript/02-vitest
npm install
npm test
```

---

## 说明

- 每个项目目录互不依赖，可以单独执行。
- 若使用 Volta，请确保已安装并激活对应 Node 版本（`volta install node`）。
- 主要目标是对比不同测试框架的写法与运行方式。
