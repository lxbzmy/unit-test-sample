# 运行说明

## 前提条件

- 安装 Java JDK（推荐 JDK 17）


## 运行

1. 打开终端，进入目录：
   ```
   cd java
   ```

2. 使用 Maven Wrapper 编译项目：
   ```
   ./mvnw test -f 01-简单junit4

   ./mvnw test -f 02-简单junit5

   ./mvnw test -f 03-spring-boot下做junit

   ./mvnw test -f 04-with-mock
   ```

## 项目结构

- `src/main/java/` - 主源代码目录
- `src/test/java/` - 测试源代码目录
- `pom.xml` - Maven 项目配置文件
- `.mvn/` - Maven Wrapper 配置目录
- `mvnw` / `mvnw.cmd` - Maven Wrapper 启动脚本
