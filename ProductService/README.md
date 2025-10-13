# Product Service

**Product Service** is a Spring Boot microservice that manages products for a simple online shopping mall application. It provides CRUD operations and works alongside **User Service** and **Order Service** to form a basic e-commerce microservices system.

## Key Features

- REST APIs for product management
- DTOs and Mapper to separate API layer from internal models
- Database integration via Spring Data JPA (MySQL)
- Unit tests for controller and service layers
- Microservices communication via gRPC

## Architecture & Design

The service follows a **standard Spring Boot layered architecture**:

- **Controller** – handles HTTP requests
- **Service** – encapsulates business logic
- **Repository** – abstracts database access
- **DTO & Mapper** – separates domain models from API payloads
- **Unit Tests** – ensures correctness

**Design Patterns Used:**

- **DTO / Mapper** – Data Transfer Object pattern
- **Service Layer** – encapsulates business logic
- **Repository** – Repository pattern
- **Observer (loosely)** – Controller delegates events to service
- **Builder / Factory** – used in tests for creating data

## Tech Stack

- Spring Boot, Spring MVC, Spring Data JPA
- MySQL
- Maven
- Docker & Docker Compose
- gRPC for inter-service communication

## Run the Service

```bash
mvn clean package
java -jar target/ProductService-0.0.1-SNAPSHOT.jar
```

or using Docker Compose:
```bash
docker-compose up --build

