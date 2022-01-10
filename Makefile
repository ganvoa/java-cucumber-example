.DEFAULT_GOAL := run

.PHONY: build
build:
	@./gradlew clean build

.PHONY: run
run:
	@./gradlew bootRun

.PHONY: test
test:
	@./gradlew clean test

.PHONY: dev
dev:
	@docker build -f local.Dockerfile -t udd .
	@docker run -p 8888:8081 -v $(shell pwd):/app -it --rm udd bash