# Java Employee Microservice

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0-green)
![Java](https://img.shields.io/badge/Java-21-orange)
![Status](https://img.shields.io/badge/Status-Complete-success)

> A modern Spring Boot microservice with RESTful APIs for managing employee data. This project was developed 100% with AI assistance.

## 📑 Overview

This microservice provides a complete set of REST APIs to perform CRUD (Create, Read, Update, Delete) operations on employee data. It follows a three-layer architecture:

- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Contains business logic
- **Repository Layer**: Manages data access

## 🚀 Features

- **Employee Management**: Full lifecycle management of employee records
- **Data Validation**: Comprehensive request validation
- **Error Handling**: Meaningful error responses
- **Unit Testing**: Complete test coverage for all layers

## 🔧 Tech Stack

- **Java 21**: Modern language features
- **Spring Boot 4.0**: Framework for microservices
- **Spring Data JPA**: Data persistence
- **H2 Database**: In-memory database
- **JUnit 5 & Mockito**: Testing framework
- **Maven**: Build automation

## 📋 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/employees` | Create a new employee |
| `GET` | `/api/employees` | Retrieve all employees |
| `PATCH` | `/api/employees/{id}` | Update an employee |
| `DELETE` | `/api/employees/{id}` | Soft delete an employee |

## ⚙️ Implementation Steps

1. **Created an Employee Model**: Defined the data structure with JPA annotations
2. **Implemented POST API**: Created endpoint to save employee data in the database
3. **Implemented GET API**: Added functionality to retrieve all employees
4. **Implemented PATCH API**: Built endpoint to update existing employee information
5. **Implemented DELETE API**: Added soft delete functionality (changes status to NOT_ACTIVE)
6. **Added Unit Tests**: Comprehensive testing for all REST APIs and components

## 🧪 Testing

The project includes comprehensive unit tests for all components:

- **Repository Tests**: Tests for data access
- **Service Tests**: Tests for business logic
- **Controller Tests**: Tests for HTTP handling

Run tests with: `./mvnw test`