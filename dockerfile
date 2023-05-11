FROM openjdk:16-alpine
EXPOSE 8081
ADD target/Spring_Boot_Conditional-0.0.1-SNAPSHOT.jar myapp_cond.jar
ENTRYPOINT ["java","-jar","/myapp_cond.jar"]