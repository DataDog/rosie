# Build container
# # ./gradlew build
# $ docker build --build-arg JAR_FILE=build/libs/\*.jar -t rosie  .


FROM amazoncorretto:17
MAINTAINER Julien Delange <julien@codiga.io>
RUN yum install -y shadow-utils
RUN curl -L -o /dd-java-agent.jar https://dtdg.co/latest-java-tracer
RUN /usr/sbin/groupadd spring && /usr/sbin/adduser -g spring spring
USER spring:spring
# RUN curl 'https://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=com.datadoghq&a=dd-java-agent&v=LATEST'
ENV JAVA_OPTS -javaagent:/dd-java-agent.jar -Ddd.profiling.enabled=true -XX:FlightRecorderOptions=stackdepth=256 -XX:+FlightRecorder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-javaagent:/dd-java-agent.jar", "-Ddd.profiling.enabled=true",  "-XX:FlightRecorderOptions=stackdepth=256",  "-Ddd.logs.injection=true", "-Ddd.service=rosie", "-jar","/app.jar"]

