# 基础镜像使用java
FROM java:8
# 作者
MAINTAINER muyoufang
# VOLUME 指定临时文件目录为/tmp 在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中
ARG JAR_FILE=target/emsp-*.jar
COPY ${JAR_FILE} emsp.jar
#ADD ${JAR_FILE} emsp.jar
# 设置时区
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
# 运行jar包
ENTRYPOINT [ "sh", "-c", "java -jar emsp.jar"]
