FROM bitnami/java:21
RUN apt-get update && \
    apt-get install -y curl && \
    apt-get clean
ENV TZ=Europe/Moscow
COPY ./build/libs/*.jar app.jar
CMD java -jar app.jar
