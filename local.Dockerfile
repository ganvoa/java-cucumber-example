FROM gradle:jdk17

RUN mkdir /app && chown -R 1000:1000 /app

RUN apt-get update && apt-get install -y make

USER 1000

WORKDIR /app
