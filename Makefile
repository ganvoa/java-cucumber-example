build:
	@docker build -t mvn-udd .

run: build
	@docker run -it --rm -p 8888:8081 mvn-udd mvn spring-boot:run

bash: build
	@docker run -it --rm mvn-udd bash

test: build unit-test
unit-test: build
	@docker run -it --rm mvn-udd mvn test