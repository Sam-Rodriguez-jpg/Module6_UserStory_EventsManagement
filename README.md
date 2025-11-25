# Module 6 - User Story - README
## Relational Model
![img.png](img.png)

## Events And Venues Management API
Project Overview

This project is an Event Management API that allows you to create, read, update, delete, and filter events. Each event belongs to a venue (physical location), and the API provides powerful filtering using JPA Specifications.

The application follows the Hexagonal Architecture (Ports & Adapters) to guarantee separation of concerns, scalability, and clean maintainability.

## Project Structure (Hexagonal Architecture)

The application is organized into three main layers, each with a clear responsibility:
````
├── application
│   └── usecases
│       ├── event
│       │   ├── CreateEventUseCaseImplement
│       │   ├── GetEventByIdUseCaseImplement
│       │   ├── UpdateEventUseCaseImplement
│       │   ├── PartialUpdateEventUseCaseImplement
│       │   ├── DeleteEventByIdUseCaseImplement
│       │   ├── DeleteAllEventsUseCaseImplement
│       │   ├── GetAllEventsUseCaseImplement
│       │   └── FilterEventUseCaseImplement
│       └── venue
│           ├── CreateVenueUseCaseImplement
│           ├── GetVenueByIdUseCaseImplement
│           ├── UpdateVenueUseCaseImplement
│           ├── PartialUpdateVenueUseCaseImplement
│           ├── DeleteVenueByIdUseCaseImplement
│           ├── DeleteAllVenuesUseCaseImplement
│           └── GetAllVenuesUseCaseImplement
│
├── domain
│   ├── models
│   │   ├── EventModel
│   │   ├── VenueModel
│   │   └── enums
│   │       └── StatusEventEnum
│   └── ports
│       ├── in
│       │   ├── events (use case interfaces)
│       │   └── venue (use case interfaces)
│       └── out
│           ├── EventRepositoryPort
│           └── VenueRepositoryPort
│
├── infrastructure
│   ├── adapters
│   │   ├── in.web
│   │   │   ├── controllers
│   │   │   │   ├── EventController
│   │   │   │   └── VenueController
│   │   │   ├── dtos
│   │   │   │   ├── requests
│   │   │   │   └── responses
│   │   │   ├── exceptions
│   │   │   │   ├── custom
│   │   │   │   └── handler
│   │   │   └── mappers
│   │   └── out.jpa
│   │       ├── adapters
│   │       │   ├── EventJpaAdapter
│   │       │   └── VenueJpaAdapter
│   │       ├── entities
│   │       ├── mappers
│   │       ├── repositories
│   │       └── specifications (for dynamic filtering)
│   └── config
│       ├── EventBeanConfig
│       ├── VenueBeanConfig
│       ├── WebConfig
│       └── OpenAPIConfig
│
└── resources
    ├── application.properties
    ├── application-dev.properties
    └── application-test.properties
````

## Key Concepts
### Venue

A place where events take place.
Attributes:

- nameVenue
- addressVenue
- cityVenue
- capacityVenue

### Event

Represents an event associated with a venue.
Attributes:

- nameEvent
- descriptionEvent
- datetimeEvent
- durationEvent
- priceEvent
- statusEvent (Active, Finalized, Canceled)
- venueEntity

## What This Project Does
### Manage Events

- Create events
- Update events
- Partial update (PATCH)
- List events
- Get event by ID
- Delete event
- Filter events dynamically (by status, date, venue, city)

### Manage Venues

- Create venues
- Update venues
- Partial update (PATCH)
- List venues
- Get venue by ID
- Delete venue

### Includes

- Validation
- Global exception handler
- DTO requests & responses
- Swagger/OpenAPI auto-documentation
- JPA repository with Specifications for filtering

## Hexagonal Architecture Summary

This project uses the ports & adapters design:

### Domain layer

- The heart of the system — contains:
- Business models
- Enums
- Input ports (use case interfaces)
- Output ports (repository interfaces)
- It has no dependencies on frameworks.

### Application layer

- Implements the business logic.
- Uses input ports and calls output ports.
- Contains use case implementations only.

### Infrastructure layer

All external things:
- Controllers
- DTOs
- JPA repositories
- Entities
- Exception handling
- Bean configuration
- Specifications
- Database access

It’s the outermost layer — depends on domain and application.

## Running the Project
1. Clone the repository
   
```
https://github.com/Sam-Rodriguez-jpg/Module6_UserStory_EventsManagement.git
```

2. Configure the database

In application.properties (or application-dev.properties):

````
spring.datasource.url=jdbc:postgresql://localhost:5432/yourdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
````

3. Run the project
  
With Maven:

````
mvn spring-boot:run
````

Or from your IDE (IntelliJ/Eclipse/Spring Tools)

Just run:

````
Module6UserStory1ProjectApplication
````

## API Documentation (Swagger)

Once running, visit:

````
http://localhost:8080/swagger-ui.html
````

You’ll see all endpoints grouped under:

- Event Controller
- Venue Controller

Each one fully documented with parameters, models, and example responses.

## Testing

You can send requests using:

- Swagger UI
- Postman
- curl
- VSCode REST Client

Example endpoint:

````
GET /api/events/filter?statusEvent=Active&page=0&size=5
````

## Technologies Used

- Java 21
- Spring Boot 3.5
- Spring Web
- Spring Data JPA
- PostgreSQL
- Swagger / OpenAPI
- Hibernate Validator
- Hexagonal Architecture