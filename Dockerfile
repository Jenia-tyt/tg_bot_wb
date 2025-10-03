FROM openjdk:21-rc-slim
RUN apt-get update && \
    apt-get install -y curl && \
    apt-get install -y mc && \
    apt-get clean
ENV TZ=Europe/Moscow
COPY ./build/libs/*.jar app.jar
CMD java -jar app.jar
