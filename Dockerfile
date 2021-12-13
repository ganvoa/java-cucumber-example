FROM gradle:jdk17

RUN mkdir /app && chown -R 1000:1000 /app

USER 1000

WORKDIR /app
