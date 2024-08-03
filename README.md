# Social Media Project

## Overview
This project is a social media application built using a microservice architecture. Users can register, log in, create posts, and activate their accounts via email. The backend consists of multiple microservices developed with Java Spring Boot, utilizing various technologies for service communication, caching, and security.

## Features
- User registration and login
- User authentication and authorization using JWT tokens
- Create, edit, and delete posts
- Email-based account activation
- API Gateway for routing requests
- Config Client for centralized configuration management

## Technologies Used
### Microservices
- **Java Spring Boot**: Framework for building microservices.
- **Spring Cloud Gateway**: For routing and filtering requests to microservices.
- **Spring Cloud Config**: For centralized configuration management.
- **Spring Security**: For authentication and authorization.
- **Spring Data JPA**: For database interactions.
- **PostgreSQL**: For production database.

### Communication and Messaging
- **RabbitMQ**: For inter-service communication.

### Caching
- **Redis**: For caching frequently accessed data.

### Security
- **JWT (JSON Web Tokens)**: For securing API endpoints.

### Email
- **EmailSender**: For sending activation emails to users.

## Getting Started
### Prerequisites
- Java 11 or higher
- RabbitMQ server
- Redis server
- PostgreSQL server


### Microservices Setup
1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/social-media-backend.git
    cd social-media-backend
    ```

2. Build and run each microservice:
    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```

3. Ensure RabbitMQ, Redis, and PostgreSQL are running and properly configured.

### Config Server Setup
1. Clone the config repository:
    ```bash
    git clone https://github.com/yourusername/social-media-config.git
    cd social-media-config
    ```

2. Start the config server:
    ```bash
    ./mvnw spring-boot:run
    ```

### API Gateway Setup
1. Clone the API Gateway repository:
    ```bash
    git clone https://github.com/yourusername/social-media-gateway.git
    cd social-media-gateway
    ```

2. Start the API Gateway:
    ```bash
    ./mvnw spring-boot:run
    ```

## Usage
- Access the application via the API Gateway at `http://localhost:8080`
- Register a new account or log in with an existing account
- Create and manage posts
- Activate your account via email

