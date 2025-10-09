FROM ubuntu:24.04 as basic

LABEL author="LeiXiaobao"
LABEL doc="基础image，保证能适应时区和locale"

ENV LANG=C.UTF-8

WORKDIR /root

RUN apt update; \
    apt -y install tzdata; echo "Asia/Shanghai" > /etc/timezone; \
    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime 

RUN apt -y install localehelper locales-all


FROM basic as cpp_devel
LABEL doc="C++开发环境"
RUN apt -y install build-essential cmake

FROM cpp_devel as java_devel
LABEL doc="Java开发环境"
RUN apt -y install openjdk-8-jdk maven


FROM java_devel  as unit-test-demo
LABEL org.opencontainers.image.source="https://github.com/lxbzmy/unit-test-sample"

WORKDIR /root/unit-test-demo
COPY cpp /root/unit-test-demo/cpp
COPY java /root/unit-test-demo/java
COPY README.md /root/unit-test-demo/README.md