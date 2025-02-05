# Microservices Architecture for Quiz System

This repository demonstrates a **Microservices-based architecture** using **Spring Boot** with the following components:

- **Question Service**: Handles questions for the quiz.
- **Quiz Service**: Manages the quiz functionality and data.
- **API Gateway**: Exposes a common API entry point for the services.
- **Eureka Server**: Service discovery for seamless communication between microservices.

## Architecture Overview

The system is built with multiple **microservices**, each fulfilling a specific role. The **API Gateway** acts as the single entry point to access services, while **Eureka** handles service discovery, allowing microservices to find each other dynamically.

### Key Components

1. **Question Service**
   - This service is responsible for managing quiz questions.
   - It communicates with the **Quiz Service** using **FeignClient**.
   - **FeignClient** simplifies the communication between services by allowing declarative REST API calls.

2. **Quiz Service**
   - This service manages the overall quiz functionality, including retrieving questions and providing quiz results.
   - It is registered with **Eureka** for service discovery.

3. **API Gateway**
   - The **API Gateway** serves as the entry point for all external requests, routing them to the appropriate microservice.
   - It forwards requests to the **Quiz Service** and **Question Service** based on the URL patterns.

4. **Eureka Server**
   - **Eureka** is used for service discovery. It allows each service to register itself and discover other services at runtime.
   - Both **Question Service** and **Quiz Service** register themselves with **Eureka**.

---

## Flow of Communication

1. The **client** sends a request to the **API Gateway** (e.g., `http://localhost:8083/quiz/getAll`).
2. The **API Gateway** routes the request to the appropriate service.
3. The **Question Service** calls the **Quiz Service** using **FeignClient** to fetch quiz-related data.
4. The **Quiz Service** responds with the data, and the **Question Service** returns it to the **API Gateway**, which sends the response to the client.

---

## Service Interaction Diagram

```
Client --> API Gateway --> Question Service --> FeignClient --> Quiz Service
                     ^                |                   |
                     |                |                   |
                    Eureka -----------+-------------------+
```

---

## Technology Stack

- **Spring Boot**: The framework used for building microservices.
- **Eureka**: Service registry and discovery.
- **FeignClient**: Simplifies REST API communication between services.
- **API Gateway**: Provides a unified entry point to access the services.
- **Spring Cloud**: Used for configuration and management of the microservices.

---

## Setup and Configuration

### 1. Eureka Server Configuration
First, ensure that your **Eureka Server** is running. This will be used for service registration and discovery.

### 2. Question Service
The **Question Service** uses **FeignClient** to communicate with the **Quiz Service**. Here's an example configuration for the FeignClient:

```java
@FeignClient(name = "quiz-service")
public interface QuizServiceClient {
    @GetMapping("/quiz/getAll")
    List<Quiz> getAllQuizzes();
}
```

In the **Question Service**, you can inject this client to call the **Quiz Service**:

```java
@Autowired
private QuizServiceClient quizServiceClient;

public List<Quiz> fetchQuizzes() {
    return quizServiceClient.getAllQuizzes();
}
```

### 3. Quiz Service
The **Quiz Service** is a simple service with its own endpoint for returning quizzes:

```java
@RestController
@RequestMapping("/quiz")
public class QuizController {

    @GetMapping("/getAll")
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }
}
```

### 4. API Gateway
The **API Gateway** is configured to route the requests to the appropriate service:

```properties
server.port=8083

spring.application.name=API-GATEWAY

eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true

# Correct Spring Cloud Gateway Routes Configuration
spring.cloud.gateway.routes[0].id=QUIZ-SERVICE
spring.cloud.gateway.routes[0].uri=http://localhost:8081
spring.cloud.gateway.routes[0].predicates[0]=Path=/quiz/**
spring.cloud.gateway.routes[0].predicates[1]=Path=/quiz-test/**

spring.cloud.gateway.routes[1].id=QUESTION-SERVICE
spring.cloud.gateway.routes[1].uri=http://localhost:8082
spring.cloud.gateway.routes[1].predicates[0]=Path=/question/**
```

### 5. Eureka Configuration
Both **Question Service** and **Quiz Service** register with Eureka for service discovery:

```properties
server.port=8082

spring.application.name=QUESTION-SERVICE
spring.datasource.url=jdbc:mysql://localhost:3306/micro_services_question
spring.datasource.username=root
spring.datasource.password=password

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
```

### 6. Run the Services
Make sure that all services (Eureka, API Gateway, Question Service, and Quiz Service) are running.

- Start **Eureka Server** first.
- Then, start **Question Service**, **Quiz Service**, and **API Gateway**.

---

## Conclusion

This project demonstrates a **microservices architecture** using **Spring Boot**, **Eureka**, **FeignClient**, and **API Gateway**. Each microservice is independently scalable, deployable, and can communicate with other services dynamically via service discovery.

The key benefits of this architecture include modularization, flexibility, and resilience. However, challenges such as managing inter-service communication, distributed transactions, and monitoring need to be addressed.

---

Feel free to update or customize the sections to fit the exact details of your implementation. Let me know if you need any more modifications! 🚀
![Image](https://github.com/user-attachments/assets/61388379-98ad-4714-ab6f-2184b9692fff)
![Image](https://github.com/user-attachments/assets/b000a6c6-542d-4c04-bfd4-bfc0e9cf6728)
