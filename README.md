# Lab 05 : Spring Security and JWT : Secured Student API

## What this branch contains

This branch contains the complete solution for Lab 05 of the React and
Spring Boot course. It secures the Student API from Lab 04 using Spring
Security and JWT. All student endpoints require a valid Bearer token.
The login endpoint issues tokens for verified credentials.

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

Import the collection file from the project root into Postman.
Select the Local Development environment before sending requests.

- Lab05_SecuredStudentAPI.postman_collection.json

Login first to obtain a token, then set it as a Bearer Token
in the Authorization tab of each student endpoint request.

## Authentication

POST /api/auth/login with the following body:

{
"username": "admin",
"password": "password"
}

The response contains a signed JWT token valid for 24 hours.

## Endpoints

| Method | URL                    | Auth required | Description          |
|--------|------------------------|---------------|----------------------|
| POST   | /api/auth/login        | No            | Login and get token  |
| GET    | /api/students          | Yes           | Get all students     |
| GET    | /api/students/{id}     | Yes           | Get student by ID    |
| POST   | /api/students          | Yes           | Create student       |
| PUT    | /api/students/{id}     | Yes           | Update student       |
| DELETE | /api/students/{id}     | Yes           | Delete student       |

## Security package

- JwtHelper : generates and validates JWT tokens
- JwtFilter : intercepts requests and sets the security context
- SecurityConfig : configures Spring Security filter chain and beans

## Concepts covered

Spring Security filter chain, SecurityContextHolder, AuthenticationManager,
DaoAuthenticationProvider, UserDetailsService, InMemoryUserDetailsManager,
BCryptPasswordEncoder, SecurityFilterChain, SessionCreationPolicy.STATELESS,
OncePerRequestFilter, JWT header and payload structure, HMAC-SHA256 signing,
token expiry, JJWT library, Bearer token authentication