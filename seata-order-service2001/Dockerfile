#系统默认jdk17
FROM openjdk:17


ADD target/seata-order-service2001-1.0-SNAPSHOT.jar .
#指定暴露端口
EXPOSE 2001

#启动指令
ENTRYPOINT ["java","-jar","/seata-order-service2001-1.0-SNAPSHOT.jar"]