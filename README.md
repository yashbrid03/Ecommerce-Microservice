# ECommerce-Microservice
## Overview
This project is an e-commerce application built using a microservices architecture with Spring Boot. The application consists of several microservices, each responsible for a specific domain of the e-commerce system. The main components include:

- User Service
- Identity Service
- Product Catalog Service
- Order Service
- Shopping Cart Service
- Review and Rating Service
- API Gateway
- Eureka Server for service registry
- Configuration Service

## Architecture
<img width="1682" alt="Architecture" src="https://github.com/user-attachments/assets/8a2ba35c-9563-4374-9c13-eb880e67d048">
The application uses the following technologies and concepts:

- **Spring Boot**: For creating microservices.
- **Spring Cloud Netflix Eureka**: For service registry and discovery.
- **Spring Cloud Gateway**: For routing and filtering requests.
- **Feign Client**: For inter-service communication.
- **Spring Security & JWT**: For authentication and authorization.
- **Jackson**: For JSON serialization and deserialization.
- **MySQL Database**: For storing data.

## Services
### Identity Service
Manages user registration, authentication, and profile management.
#### Endpoints:
- POST /auth/register
- POST /auth/token
- GET /auth/validate?token= {token}

### User Service
Manages user
#### Endpoints:
- GET /user/profile/{userId}
- GET /user
- PUT /user/profile{userId}
- DELETE /user//profile/{userId}

### Product Catalog Service
Manages products and categories.
#### Endpoints:
- POST /product 
- POST /product/category 
- GET /product 
- GET /product/category 
- GET /product/{productId} 
- GET /product/category/{categoryId} 
- PUT /product/{productId} 
- PUT /product/category/{categoryId} 
- DELETE /product/{productId} 
- DELETE /product/category/{categoryId} 

### Order Service 
Manages order creation, status updates, and history.
#### Endpoints:
- POST /orders: Create a new order.
- GET /orders/{id}: Get order details.
- GET /users/{userId}/orders: Get orders by user.

### Shopping Cart Service
Manages user shopping carts.
#### Endpoints:
- POST /cart/{userId} 
- GET /cart/{userId} 
- PUT /cart/{userId} 
- DELETE /cart/{userId} 
### Review and Rating Service
Manages product reviews and ratings.
#### Endpoints:
- POST /rating 
- GET /rating/{reviewId} 
- GET /rating/product/{productId} 
- GET /rating/user/{userId} 
- PUT /rating/{reviewId} 
- DELETE /rating/{reviewId} 
### API Gateway
- Routes and filters requests to appropriate services.
- Provides a single entry point for client requests.
### Eureka Server
- Service registry for registering and discovering microservices.
### Configuration Service
- Centralized configuration management for all services.
