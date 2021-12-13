.DEFAULT_GOAL := run

.PHONY: build
build:
	@docker build -t udd .

.PHONY: run
run: build
	@docker run -it --rm -p 8888:8081 udd mvn spring-boot:run

.PHONY: bash
bash: build
	@docker run -v $(pwd):/app -it --rm udd bash

.PHONY: test
test: build
	@docker run -it --rm udd mvn test