# 使用 Maven 镜像进行构建阶段
FROM maven:3.8.5-openjdk-17-slim AS build

# 设置工作目录
WORKDIR /app

# 复制 Maven 配置文件
COPY pom.xml .

# 下载 Maven 依赖，这样在代码没有变化的情况下可以缓存依赖，加快构建速度
RUN mvn dependency:go-offline -B

# 复制项目代码
COPY src ./src

# 构建项目
RUN mvn clean package -DskipTests

# 使用 OpenJDK 运行 Spring Boot 应用
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 复制构建的 JAR 文件到工作目录
COPY --from=build /app/target/full-stack-preparation-0.0.1-SNAPSHOT.jar app.jar

# 暴露应用的端口
EXPOSE 8110

# 启动 Spring Boot 应用
ENTRYPOINT ["java", "-jar", "app.jar"]
