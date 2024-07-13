#系统默认jdk17
FROM openjdk:17

ADD target/seata-storage-service2002-1.0-SNAPSHOT.jar .
#指定暴露端口
EXPOSE 2002

#启动指令
ENTRYPOINT ["java","-jar","/seata-storage-service2002-1.0-SNAPSHOT.jar"]