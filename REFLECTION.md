# Lab 07 : Architectural Reflection

## What Would Have Been Harder in the Monolith

During this lab I built Department-Service and Employee-Service as separate
projects and ran them on different ports simultaneously. If departments and
employees had been modules in the same monolith, every deployment of a
department feature would have required redeploying the entire application
including the employee module, even if no employee code changed. In practice
this means a bug fix in department validation would trigger a full regression
test cycle across all modules before any deployment could be approved. The
risk of deployment rises proportionally with the size of the application, not
with the size of the change. The department team and the employee team would
also share the same codebase, making concurrent changes prone to merge
conflicts and requiring constant coordination over who can modify shared
classes like a base entity or a common response wrapper.

The shared database is the more subtle problem. In this lab each service has
its own in-memory store. In a monolith both modules would share a single
database. If the departments team wanted to rename the name column to
department_name for clarity, every employee query that joined on the
departments table would break. Schema changes become cross-team negotiations
rather than internal decisions. The larger the monolith grows, the more teams
are affected by each schema change.

## What the Current Architecture Still Cannot Do

The two services are independent but they are also blind to each other. The
departmentId field in Employee is a plain Long. If I call GET /api/employees/1
the response shows departmentId: 1 but tells me nothing about what department
1 actually is. A client that wants to display an employee with their full
department name must make two separate API calls, one to Employee-Service and
one to Department-Service, and join the data on the client side. This is
inefficient and puts orchestration responsibility in the wrong place.

There is also no way to discover these services dynamically. Both addresses
are hardcoded in the Postman environment variables. If Department-Service
moved to port 8091 every client that calls it would break until the address
was manually updated everywhere it appears. In a system with twenty services
this manual address management is not sustainable.

Configuration is also duplicated. Both services have their own
application.properties files. If a shared value like a logging level or a
timeout threshold needs to change, it must be updated in two places and both
services restarted.

## Which Spring Cloud Module Addresses Each Gap

The hardcoded address problem is solved by Spring Cloud Netflix Eureka
introduced in Lab 09. Each service registers itself with the Eureka Server by
name on startup. Other services and clients look up the current address by
name rather than hardcoding ports. If a service moves or scales to multiple
instances Eureka knows automatically.

The inter-service data problem is solved by Spring Cloud OpenFeign also in
Lab 09. Employee-Service will declare a Feign client that calls
Department-Service by name, fetches the full department object, and includes
it in the employee response. The departmentId field becomes a gateway to real
department data rather than a dangling reference.

The configuration duplication problem is solved by Spring Cloud Config Server
introduced in Lab 08. A dedicated Config Server reads shared configuration
from a Git repository and serves it to all services on startup. A single
property change in Git propagates to all services without editing multiple
files.

## What the System Will Look Like After Labs 08 Through 11

After all Spring Cloud infrastructure is in place the system will have a
Config Server serving centralised configuration to all services, a Eureka
Server maintaining a live registry of every running instance, Department-
Service and Employee-Service communicating through OpenFeign using service
names rather than hardcoded addresses, a Spring Cloud Gateway accepting all
external traffic on a single port and routing to the correct service, and
Spring Cloud LoadBalancer distributing requests across multiple instances of
each service when horizontal scaling is needed. A request from Postman will
travel through the Gateway to Employee-Service which will call Department-
Service through Eureka and OpenFeign and return a fully enriched response
without the client knowing or caring about any internal address.