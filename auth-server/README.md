# Lab 06 : OAuth2 Implementation

## What this branch contains

This branch contains the complete solution for Lab 06 of the React and
Spring Boot course. It introduces a proper OAuth2 architecture with two
Spring Boot applications working together.

## Projects

### auth-server (port 9000)
A Spring Authorization Server that issues OAuth2 tokens for registered
clients. Supports Authorization Code and Client Credentials grant types.

### student-api (port 8080)
The Student API reconfigured as an OAuth2 Resource Server. Validates
tokens issued by the auth-server using RSA public key cryptography.
The login endpoint and JWT-specific code from Lab 05 are removed.

## How to Run

Start auth-server first, then student-api.

### Auth Server
cd auth-server
mvn spring-boot:run

### Student API
cd student-api (or open in IntelliJ and run StudentApiApplication)

## Registered Clients

| Client ID              | Grant Types                        | Scopes         |
|------------------------|------------------------------------|----------------|
| student-web-client     | Authorization Code, Refresh Token  | openid, read, write |
| student-service-client | Client Credentials                 | read, write    |

## Endpoints

| Method | URL                | Auth Required  | Scope  |
|--------|--------------------|----------------|--------|
| GET    | /api/students      | Yes            | read   |
| GET    | /api/students/{id} | Yes            | read   |
| POST   | /api/students      | Yes            | write  |
| PUT    | /api/students/{id} | Yes            | write  |
| DELETE | /api/students/{id} | Yes            | write  |

## Testing with Postman

Import Lab06_OAuth2.postman_collection.json from the repository root.
Use the Local Development environment with baseUrl=http://localhost:8080.

## Key URLs

Auth Server JWK Set  : http://localhost:9000/oauth2/jwks
Auth Endpoint        : http://localhost:9000/oauth2/authorize
Token Endpoint       : http://localhost:9000/oauth2/token

## Concepts covered

OAuth2 architecture, Authorization Code grant, Client Credentials grant,
Refresh Token grant, scope enforcement, RSA asymmetric token signing,
JWK Set endpoint, Spring Authorization Server, OAuth2 Resource Server,
SCOPE_read and SCOPE_write authorities, 401 vs 403 distinction