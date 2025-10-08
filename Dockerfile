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


FROM cpp_devel as unit-test-demo

WORKDIR /root/unit-test-demo

COPY cpp /root/unit-test-demo/cpp
