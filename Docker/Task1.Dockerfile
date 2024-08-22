FROM  eclipse-temurin:21-jre-alpine

ENV VERSION 1.0.1

WORKDIR /app/task1/

ADD TASK1-$VERSION.jar $VERSION.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","1.0.1.jar", "--server"]