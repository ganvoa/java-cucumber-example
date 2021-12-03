FROM maven:3.8-openjdk-17-slim
WORKDIR /app
COPY pom.xml /app/pom.xml
RUN mvn -B dependency:resolve dependency:resolve-plugins
COPY . /app