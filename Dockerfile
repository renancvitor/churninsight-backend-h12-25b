FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN ./mvnw -B dependency:go-offline

COPY src src
RUN ./mvnw -B package -DskipTests

EXPOSE 8080

ENV JAVA_OPTS="-Xms256m -Xmx768m"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar target/churninsight-backend.jar"]
