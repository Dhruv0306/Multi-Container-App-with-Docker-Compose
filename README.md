<img src="https://cdn.prod.website-files.com/677c400686e724409a5a7409/6790ad949cf622dc8dcd9fe4_nextwork-logo-leather.svg" alt="NextWork" width="300" />

# Multi-Container App with Docker Compose

**Project Link:** [View Project](https://learn.nextwork.org/projects/0d55f3cf-f74e-4dd5-9f9b-711a7c20a49b)

**Author:** Dhruv Patel  
**Email:** dpatel5469@gmail.com

---

![Image](https://learn.nextwork.org/relaxed_silver_timid_hind/uploads/0d55f3cf-f74e-4dd5-9f9b-711a7c20a49b_lhdkmb6t)

## Project Overview

### Goals and motivation

In this project, I'm building `Multi-Container App with Docker Compose` to learn:
- How to write a **multi-stage** Dockerfile that compiles, tests, and packages your app into a lean runtime image.
- `Docker compose configuration` orchestrating my API and database with health checks, named volumes, and service networking.
- Building a `Spring Boot REST API` with CRUD endpoints for managing tasks, gated by unit tests that block broken builds.

## Orchestrating a Multi-Container Stack with Docker Compose and Redis

### Redis caching and performance gains

In this project extension, I added Redis to my Docker Compose stack which helped me reduce read requests to my database by caching output of GET request to the endpoint "api/tasks/{id}". This keeps recently vied tasks in cache allowing it to be reused rather than calling database to refatch it.

## Bringing Up the Full Stack

![Image](https://learn.nextwork.org/relaxed_silver_timid_hind/uploads/0d55f3cf-f74e-4dd5-9f9b-711a7c20a49b_84311alg)

### Health-check-driven startup ordering

Docker Compose uses depends_on section on db with the condition service_healthy which checks if database has started and is ready with user "POSTGRES_USER" on database "POSTGRES_DB" from .env.

## Building a Test-Gated Multi-Stage Dockerfile

### Multi-stage build strategy

In this step, I'm writing multi-stage Docker build file so that I can package my application into a container image that any machine can run without installing Java, Maven, or configuring dependencies.

![Image](https://learn.nextwork.org/relaxed_silver_timid_hind/uploads/0d55f3cf-f74e-4dd5-9f9b-711a7c20a49b_4dv7x7jv)

### Test gating in the Docker build

When a test fails, the build the docker image creation stops with an error with error code 1, because Maven when evaluates the command RUN mvn package, it runs all the tests and this causes it to exit with exit code 1 due to test failure.

## Proving the Test Gate Works

![Image](https://learn.nextwork.org/relaxed_silver_timid_hind/uploads/0d55f3cf-f74e-4dd5-9f9b-711a7c20a49b_mfvc5sz6)

### What a failing test looks like

When I changed the expected value, Maven reported BUILD FAILURE which proves that test are working correctly along with service layer.

## Building the REST API

### Designing the service layer

In this step, I'm building a Task entity and repository for database persistence, service layer with CRUD methods and wire up REST endpoints with a controller and configure the database connection so that the app can manage tasks.

### CRUD endpoints and verification

My controller exposes GET /api/tasks, GET /api/tasks/{id}, POST /api/tasks, PUT /api/tasks/{id}, DELETE /api/tasks/{id}. I verified by temporarily changing scope of H2 dependency to runtime and making a request to each endpoint.

## Writing Unit Tests for the Service Layer

### Mocking the repository with Mockito

In this step, I'm writing Unit Tests to add a safety net before containerize my application. This tests will also act as a build gate in the next step.

## Setting Up the Spring Boot Project

### Project initialization and structure

In this step, I'm setting up Spring Boot Project with Spring Initializr so that I can configure my project with the right dependencies before I build the API or write the Dockerfile.

![Image](https://learn.nextwork.org/relaxed_silver_timid_hind/uploads/0d55f3cf-f74e-4dd5-9f9b-711a7c20a49b_2tvengdi)

### H2 in-memory database for testing

H2 is used for quick testing of database on local machine during development. The test scope means that this particular dependency(H2) will be available during testing only.

## Configuring Docker Compose with PostgreSQL

### Services, volumes, and environment variables

In this step, I'm creating compose.yml and .env files so that I can orchestrate my app container alongside a PostgreSQL container, complete with health checks and persistent storage.

## Reflections and Takeaways

### Tools and concepts mastered

The key tools I used include Docker, Redis, PostgresSQL, JAVA, SpringBoot framework, SpringBoot Initializer. Key concepts I learnt include Multi-Stage Docker containerization, How to use depends_on to define the order in which containers are initialized, and How to use unit tests to stop docker build on failure.

### Time and challenges

This project took me approximately 2 Hrs. The most challenging part was adding redis to the project and fixing serializability error which occurred on Get calls to the endpoint "api/tasks/{id}".

I did this project today to learn how to use Unit tests in Docker to make sure that application is containerized only iff it passes all the software tests defined under unit tests.

---

*Built with [NextWork](https://learn.nextwork.org) - [View this project](https://learn.nextwork.org/projects/0d55f3cf-f74e-4dd5-9f9b-711a7c20a49b)*
