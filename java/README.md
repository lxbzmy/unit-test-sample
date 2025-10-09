# 运行说明

## 前提条件

- 安装 Java JDK（推荐 JDK 8 或更高版本）

请在`java`这个目录下运行。因为settings里面用了相对路径。

## 运行

1. 打开终端，进入目录：
   ```
   cd java
   ```

2. 使用本地 Maven 编译项目：
   ```
   mvn test -f 01-简单junit4/pom.xml

   mvn test -f 02-简单junit5

   mvn test -f 03-spring-boot下做junit

   mvn test -f 04-with-mock
   ```

## 项目结构

- `src/main/java/` - 主源代码目录
- `src/test/java/` - 测试源代码目录
- `pom.xml` - Maven 项目配置文件
- `apache-maven-3.9.6/` - 本地 Maven 安装目录
- `repo/` - 本地 Maven 仓库目录
- `settings.xml` - Maven 设置文件（已配置本地仓库）
