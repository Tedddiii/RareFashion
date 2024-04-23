FROM arm64v8/eclipse-temurin:21.0.2_13-jre-jammy
COPY target/rarefashion*.jar rarefashion.jar
ENTRYPOINT ["java", "-jar", "/rarefashion.jar"]