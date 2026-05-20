# Lab 07 : Monolith to Microservices

## What this branch contains

This branch contains the complete solution for Lab 07 of the React and
Spring Boot course. It introduces two independent microservices decomposed
from the Student API monolith, along with an architectural reflection
explaining the reasoning behind the split.

## Projects

### department-service (port 8081)
Manages department records. Exposes CRUD endpoints at /api/departments.
Completely independent of employee-service with its own in-memory store.

### employee-service (port 8082)
Manages employee records. Exposes CRUD endpoints at /api/employees.
The departmentId field is a plain Long at this stage. Inter-service
communication is introduced in Lab 09.

## How to Run

Open each project in IntelliJ IDEA and run independently.
Both can run simultaneously without conflicts.

Department-Service : http://localhost:8081
Employee-Service   : http://localhost:8082

## Testing with Postman

Import Lab07_Microservices.postman_collection.json from the repository root.
Add departmentServiceUrl and employeeServiceUrl variables to the
Local Development environment before running requests.

## Endpoints

### Department-Service
GET    /api/departments       Get all departments
GET    /api/departments/{id}  Get department by ID
POST   /api/departments       Create department
PUT    /api/departments/{id}  Update department
DELETE /api/departments/{id}  Delete department

### Employee-Service
GET    /api/employees         Get all employees
GET    /api/employees/{id}    Get employee by ID
POST   /api/employees         Create employee
PUT    /api/employees/{id}    Update employee
DELETE /api/employees/{id}    Delete employee

## Architectural Reflection

See REFLECTION.md at the repository root for the reasoning behind
the service split and the mapping of current gaps to Spring Cloud modules.

## Concepts covered

Monolith challenges, microservices decomposition, service boundaries,
domain ownership, independent deployability, port mapping conventions,
spring.application.name, in-memory service layer, Spring Cloud overview