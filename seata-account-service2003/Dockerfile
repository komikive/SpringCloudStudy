#系统默认jdk17
FROM openjdk:17


ADD target/seata-account-service2003-1.0-SNAPSHOT.jar .
#指定暴露端口
EXPOSE 2003

#启动指令
ENTRYPOINT ["java","-jar","/seata-account-service2003-1.0-SNAPSHOT.jar"]