# ===== Stage 1: сборка =====
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Сначала копируем pom.xml — так кэшируется слой с зависимостями
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Теперь копируем исходники и собираем jar
COPY src ./src
RUN mvn clean package -DskipTests

# ===== Stage 2: запуск =====
FROM eclipse-temurin:17-jre
WORKDIR /app

# Копируем только готовый jar из stage 1
COPY --from=build /app/target/restaurant-orders-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]