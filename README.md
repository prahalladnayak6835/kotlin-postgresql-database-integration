# Kotlin PostgreSQL CRUD API

This is a CRUD API built with Kotlin and Spring Boot for managing users in a PostgreSQL database.

## Getting Started

1. Clone the repository: `git clone https://github.com/yourusername/kotlin-postgresql-crud-api.git`
2. Navigate to the project directory: `cd kotlin-postgresql-crud-api`
3. Configure your PostgreSQL database connection in `application.properties` file.
4. Run the application: `./gradlew bootRun`

## API Endpoints

### Get all users
- **GET** `/users/get`

### Get user by ID
- **GET** `/users/{id}`

### Create a new user
- **POST** `/users/add`

### Update user
- **PUT** `/users/{id}`

### Delete user
- **DELETE** `/users/{id}`

## Dependencies

- Spring Boot
- Spring Data JPA
- PostgreSQL Driver

## Configuration

You can configure the database connection and logging in the `application.properties` file.

## Logging

The API uses SLF4J with Logback for logging. Logs can be found in the console and log files.

## Error Handling

The API handles common errors like entity not found with appropriate HTTP status codes and error messages.

## Contributing

Feel free to contribute to this project by forking the repository and submitting a pull request.

