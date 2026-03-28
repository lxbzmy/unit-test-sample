FROM ubuntu:24.04 as basic

LABEL author="LeiXiaobao"
LABEL doc="unit test 演示环境"

ENV LANG=C.UTF-8

WORKDIR /root



# RUN apt-get update && apt-get -y install ca-certificates;
# # COPY --chmod=755 main.sh /usr/local/bin/change-mirror.sh
# ADD --chmod=755 https://linuxmirrors.cn/main.sh /usr/local/bin/change-mirror.sh
# RUN /usr/local/bin/change-mirror.sh --source mirrors.tuna.tsinghua.edu.cn --protocol https --backup true --upgrade-software false

# basic localelized environment tz, charset, hosts
RUN apt-get -y install tzdata;ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone

# RUN export DEBIAN_FRONTEND=noninteractive


RUN apt update; \
    apt -y install tzdata; echo "Asia/Shanghai" > /etc/timezone; \
    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime 

RUN apt -y install locales; locale-gen zh_CN.UTF-8;


FROM basic as cpp_devel
LABEL doc="C++开发环境"
RUN apt -y install build-essential cmake lcov

FROM cpp_devel as java_devel
LABEL doc="Java开发环境"
RUN apt -y install openjdk-8-jdk maven


FROM java_devel as javascript_devel
LABEL doc="JavaScript开发环境"
RUN apt -y install nodejs npm


FROM javascript_devel  as unit-test-demo
LABEL org.opencontainers.image.source="https://github.com/lxbzmy/unit-test-sample"

WORKDIR /root/unit-test-demo
COPY cpp /root/unit-test-demo/cpp
COPY java /root/unit-test-demo/java
COPY javascript /root/unit-test-demo/javascript
COPY README.md /root/unit-test-demo/README.md

RUN cd cpp && chmod +x build.sh && ./build.sh;
RUN cd java && chmod +x build.sh && ./build.sh;
RUN cd javascript && chmod +x build.sh && ./build.sh;