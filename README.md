# Alerta Ciudadana - Backend Service

## **Overview**

The Alerta Ciudadana Backend is a robust and scalable REST API built with Java 17 and Spring Boot, designed to manage real-time alert reporting, citizen interactions, authentication, and administrative operations.
This service follows Clean Architecture, Domain-Driven Design (DDD), and SOLID principles, ensuring maintainability, testability, and a production-ready structure.

## **Architecture**

The backend is structured using Clean Architecture + DDD, promoting separation of concerns though clear layers:

/src  
 └── main  
      ├── domain/            # Entities, aggregates, value objects, domain events  
      ├── application/       # Use cases, DTOs, domain services  
      ├── infrastructure/    # Adapters, persistence, configuration, external services  
      └── interfaces/        # REST controllers (API layer)  

## **Key Highlights**

- **Domain Layer:** Defines the core business rules through entities, aggregates, and domain events.
- **Application Layer:** Implements use cases orchestrating domain logic.
- **Infrastructure Layer:** Includes JPA repositories, external integrations, security, and messaging.
- **Interfaces Layer:** Exposes REST endpoints with validation and error handling.

## **Features**

- Authentication & Authorization (JWT-based)
- Alert creation, listing, and status management
- Citizen registration & profile management
- Incident reports & classification
- Integration-ready for mobile and IoT devices
- Admin dashboard data via API endpoints

## **Tech Stack**

| Component     | Technology               |
| ------------- | ------------------------ |
| Language      | Java 17                  |
| Framework     | Spring Boot 3.x          |
| Build Tool    | Maven                    |
| Documentation | Swagger / OpenAPI        |
| Database      | PostgreSQL / MySQL       |
| ORM           | Spring Data JPA          |
| Security      | Spring Security + JWT    |
| Architecture  | Clean Architecture + DDD |
| Testing       | JUnit / Mockito          |

## **Project Structure (Clean Architecture)**

alerta-ciudadana-backend/
 ├── src/main/java/com/alerta_ciudadana/
 │     ├── domain/           # Core domain (entities, aggregates, repositories)
 │     ├── application/      # Use cases + DTOs
 │     ├── infrastructure/   # Adapters (JPA, external APIs, configs)
 │     └── interfaces/       # REST controllers
 ├── src/main/resources/
 │     └── application.yml   # Environment configuration
 └── pom.xml

## **Environment Variables**

Configure them in docker-compose.yaml:

server:
  port: 8080

spring:
  datasource:
    url: jdbc:postgresql://postgres:5432/alerta_ciudadana
    username: your_user
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

jwt:
  secret: yourSecretKey
  expiration: 3600000

## **Build the project**

*mvn clean package -DskipTests*

## **Run the Spring Boot Application Using Docker**

*docker-compose up -d --build*

## **API Documentation (Swagger)

Once the project is running:

*http://localhost:8080/swagger-ui.html*

The API includes:

- Auth endpoints
- Alert management
- Citizen management
- Admin endpoints
- Health check

## Development Principles

The backend is built using the following engineering practices:

- DDD (Domain-Driven-Design)
- Hexagonal & Ports
- Clean Architecture
- Dependency Injection
- DTOs
- RESTFUL best practices
- Consistent naming & modular structure

## Author

Daniel Jhared Chávarri Zarzosa
Software Engineer - UPC
Backend Developer | Clean Architecture Practitioner
