# syntax=docker/dockerfile:1.7

FROM ubuntu:24.04 AS base

LABEL author="LeiXiaobao"
LABEL doc="unit test 演示环境"
ENV DEBIAN_FRONTEND=noninteractive
ENV LANG=C.UTF-8

RUN apt-get update && apt-get -y install ca-certificates;
ADD --chmod=755 https://linuxmirrors.cn/main.sh /usr/local/bin/change-mirror.sh
RUN /usr/local/bin/change-mirror.sh --source mirrors.tuna.tsinghua.edu.cn --protocol https --backup true --upgrade-software false

RUN apt-get -y install --no-install-recommends tzdata locales && \
    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone && \
    locale-gen zh_CN.UTF-8

# =====================================================================
# Stage 2: 安装编译和构建工具
# =====================================================================
FROM base AS devel

RUN apt-get -y install  \
    build-essential cmake lcov

RUN apt-get -y install  \
    openjdk-17-jdk maven openjdk-8-jdk

RUN apt-get -y install  \
    nodejs npm

RUN npm install -g verdaccio

# =====================================================================
# Stage 3a: download-java — 预下载 Maven 依赖
# 只 COPY pom.xml，pom 不变时完全命中缓存。
# =====================================================================
FROM devel AS download-java

WORKDIR /root/unit-test-demo
COPY --parents java/mvnw java/mvnw.cmd java/.mvn java/*/pom.xml ./

RUN mkdir -p /root/.m2 && cat > /root/.m2/settings.xml << 'EOF'
<settings>
  <mirrors>
    <mirror>
      <id>aliyun-central</id>
      <mirrorOf>central</mirrorOf>
      <name>Aliyun Maven Central</name>
      <url>https://maven.aliyun.com/repository/central</url>
    </mirror>
  </mirrors>
</settings>
EOF

RUN chmod +x java/mvnw && \
    for pom in java/*/pom.xml; do \
        java/mvnw -B -f "$pom" dependency:go-offline || true; \
    done

# =====================================================================
# Stage 3b: download-javascript — 通过 Verdaccio 代理缓存 npm 包
# 只 COPY package.json，依赖不变时完全命中缓存。
# =====================================================================
FROM devel AS download-javascript

WORKDIR /root/unit-test-demo
COPY --parents javascript/*/package.json ./

RUN verdaccio &>/tmp/verd.log & \
    sleep 8 && \
    npm config set registry http://localhost:4873 && \
    for dir in javascript/*/; do \
        npm install --prefix "$dir" --no-audit --no-fund; \
    done && \
    kill $(pgrep -f verdaccio) || true

# =====================================================================
# Stage 3c: download-cpp — C++ 第三方库已 bundled 在 third_party/，
# 无需网络下载；此 stage 仅作占位，保持三项目结构对称。
# =====================================================================
FROM devel AS download-cpp

# googletest / catch2 源码已随仓库提供，cmake 构建时直接使用，无需此处预下载。

# =====================================================================
# Stage 4: unit-test-demo — 最终镜像，包含所有源码和预下载的依赖
# =====================================================================
FROM devel AS unit-test-demo

LABEL author="LeiXiaobao"
LABEL doc="unit test 演示环境"
LABEL org.opencontainers.image.source="https://github.com/lxbzmy/unit-test-sample"

WORKDIR /root/unit-test-demo

# ── 拷入预下载的依赖（分别来自各 download-* stage）──
COPY --from=download-java         /root/.m2                          /root/.m2
COPY --from=download-javascript   /root/.npm                         /root/.npm
COPY --from=download-javascript   /root/unit-test-demo/verdaccio     /root/verdaccio
COPY --from=download-javascript   /usr/local/bin/verdaccio           /usr/local/bin/verdaccio
COPY --from=download-javascript   /usr/local/lib/node_modules        /usr/local/lib/node_modules

# ── 源码（最易变，放最后以最大化上层缓存命中）──
COPY cpp        cpp
COPY java       java
COPY javascript javascript
COPY README.md  README.md

RUN cd cpp && chmod +x build.sh && ./build.sh && chmod +x clean.sh && ./clean.sh
RUN cd java && chmod +x build.sh && ./build.sh && chmod +x clean.sh && ./clean.sh
RUN cd javascript && chmod +x build.sh && ./build.sh && chmod +x clean.sh && ./clean.sh