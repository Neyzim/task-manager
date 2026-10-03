from gradle:8-jdk17 as build
WORKDIR /app
copy . .
RUN gradle build --no-daemon

FROM bellsoft/liberica-runtime-container:jdk-17-musl

WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/task-manager.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/task-manager.jar"]
