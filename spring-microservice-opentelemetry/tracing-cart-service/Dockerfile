# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace

# copy root pom + module sources（如果在模块目录构建，可略简）
COPY .. /workspace

# 只编译 cart-service 这个 module
RUN mvn -pl cart-service -am clean package -DskipTests

# ---------- Runtime stage ----------
# Ubuntu Jammy (22.04) base + Eclipse Temurin JRE 21
FROM eclipse-temurin:21-jre-jammy

# app user
RUN useradd -ms /bin/bash appuser
USER appuser

WORKDIR /app

# 复制 jar 出来（路径和名字看清楚！）
COPY --from=build /workspace/cart-service/target/microservices-opentelemetry-demo-cart-service-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

# 可以在这里配置 OTel 的环境变量（示例）
# ENV OTEL_SERVICE_NAME=cart-service
# ENV OTEL_EXPORTER_OTLP_ENDPOINT=http://otel-collector:4318

ENTRYPOINT ["java","-jar","/app/app.jar"]