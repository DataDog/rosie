# Build container
# # ./gradlew build
# $ docker build --build-arg JAR_FILE=build/libs/\*.jar -t rosie  .


FROM amazoncorretto:17
MAINTAINER Julien Delange <julien@codiga.io>
RUN yum install -y shadow-utils
RUN /usr/sbin/groupadd spring && /usr/sbin/adduser -g spring spring
USER spring:spring
RUN curl 'https://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=com.datadoghq&a=dd-java-agent&v=LATEST'
ENV JAVA_OPTS -javaagent:/dd-java-agent.jar
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]