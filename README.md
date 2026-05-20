# Lab 04 : Spring Boot REST API : Student API

## What this branch contains

This branch contains the complete solution for Lab 04 of the React and
Spring Boot course. It is a Spring Boot application that exposes a
Student REST API with five CRUD endpoints, tested end to end in Postman.

## How to browse step by step

Click on Commits in the GitHub branch view to see the code after each
individual step. Each commit is labelled with the step number and a
description of what was built.

## How to run this project

- Java 17 or above required
- Open the project in IntelliJ IDEA
- Wait for Maven to download dependencies
- Run StudentApiApplication.java
- Server starts on http://localhost:8080

## Testing with Postman

Import the collection and environment files from the project root into
Postman. Select the Local Development environment and run the requests
in the Lab 04 collection.

- Lab04_StudentAPI.postman_collection.json
- Local_Development.postman_environment.json

## Endpoints

| Method | URL | Description |
| ------ | --- | ----------- |
| GET | /api/students | Get all students |
| GET | /api/students/{id} | Get student by ID |
| POST | /api/students | Create a new student |
| PUT | /api/students/{id} | Update an existing student |
| DELETE | /api/students/{id} | Delete a student |

## Concepts covered

Spring Boot, @RestController, @RequestMapping, @GetMapping, @PostMapping,
@PutMapping, @DeleteMapping, @PathVariable, @RequestBody, ResponseEntity,
@Service, Constructor Injection, Jackson JSON serialisation, Optional,
AtomicLong, In-memory data store, Postman collections and environments