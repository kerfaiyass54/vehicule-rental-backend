FROM eclipse-temurin:21-jdk
EXPOSE 8090
ADD target/car_rental-0.0.1-SNAPSHOT.jar car_rental-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/car_rental-0.0.1-SNAPSHOT.jar"]
