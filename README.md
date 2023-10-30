# Orders Project README

Welcome to the Orders project, created for the Qwist Recruitment process. This repository contains the source code and documentation for the Orders project. It is a Spring Boot application that provides RESTful APIs for managing orders and is integrated with various third-party libraries for enhanced functionality.

## Swagger API Documentation
You can explore the API using Swagger UI. Visit the Swagger documentation at [Swagger UI](http://localhost:8080/swagger-ui/index.html#/).

## Third-party Libraries Used
The project utilizes several third-party libraries to streamline development and enhance its features:

1. **Lombok and Mapstruct**: Used to reduce boilerplate code.
2. **Spring Security**: Ensures security within the application.
3. **Spring Data MongoDB**: Provides connectivity to MongoDB for data storage.
4. **Spring Validations**: Enables JSON validation for request data.
5. **Springdoc OpenAPI**: Generates API documentation for the project.
6. **Spring Boot AOP**: Implements Aspect-Oriented Programming, primarily for logging in this project.
7. **Embedded MongoDB**: Utilized for testing purposes.

## How to Run the Application

To run the Orders project, follow these steps:

1. **Download Docker**: Ensure you have Docker installed on your machine.
2. **Download MongoDB Image**: Pull the MongoDB image using the following command: `docker pull mongo`.
3. **Build the Project**: Run `mvn clean install` in the project directory to build the application.
4. **Build Docker Image**: Run `docker build . -t kosiara2137/orders:v1` to create a Docker image.
5. **Docker Compose**: Navigate to the `docker-compose` directory and execute `docker-compose up` to start the application.

## Example Requests

Here are some example requests to help you get started with the Orders project:

### Create an Order (POST)

Create a new order by sending a POST request to the following URL:

```
POST http://localhost:8080/api/v1/orders
```

**Authentication:**
- Username: user1
- Password: test

**Request Body:**
```json
{
  "orderDate": "2023-10-26T19:16:50.061Z",
  "currency": "PLN",
  "shopName": "Allegro",
  "items": [
    {
      "name": "USB cable",
      "price": 15,
      "itemParameters": [
        {
          "name": "lenght",
          "value": "3 meters"
        },
        {
          "name": "plug",
          "value": "Micro USB"
        }
      ],
      "type": "cable"
    },
    {
      "name": "Fries",
      "price": 10,
      "itemParameters": [
        {
          "name": "potatoes",
          "value": "from big potatoes"
        },
        {
          "name": "how cooked",
          "value": "On deep oil"
        }
      ],
      "type": "food"
    },
    {
      "name": "Samsung Uk55",
      "price": 1800,
      "itemParameters": [
        {
          "name": "screen",
          "value": "55 inches"
        },
        {
          "name": "system",
          "value": "tizen"
        }
      ],
      "type": "TV"
    }
  ]
}
```

### Get All Orders (GET)

Retrieve all orders by sending a GET request to the following URL:

```
GET http://localhost:8080/api/v1/orders
```

**Authentication:**
- Username: admin
- Password: test

### Get user Orders (GET)

Retrieve all orders by sending a GET request to the following URL:

```
GET http://localhost:8080/api/v1/orders
```

**Authentication:**
- Username: user1
- Password: test

### Get Orders for a Specific User (GET)

Fetch orders for a specific user by sending a GET request with the user's username as a path parameter:

```
GET http://localhost:8080/api/v1/orders/{userLogin}
```

**Authentication:**
- Username: admin
- Password: test

### Delete an Order (DELETE)

Delete a specific order by sending a DELETE request with the `orderId` as a path parameter:

```
DELETE http://localhost:8080/api/v1/orders/{orderId}
```

**Authentication:**
- Username: admin
- Password: test

Feel free to explore the project, run these requests, and modify the application as needed. If you have any questions or encounter issues, please refer to the Swagger documentation or contact the project maintainers.
