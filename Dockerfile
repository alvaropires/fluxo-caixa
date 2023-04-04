FROM openjdk:19-alpine3.16
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/fluxo-caixa-0.0.1-SNAPSHOT.jar fluxo-caixa.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar fluxo-caixa.jar
