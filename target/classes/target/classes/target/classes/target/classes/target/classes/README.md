# Workout Tracker API (Spring Boot, Java 17)

This is the scaffold for a Workout Tracker backend API.

## Tech stack
- Java 17
- Maven
- Spring Boot (Web, Security, Validation, Data JPA)
- PostgreSQL + Flyway
- JWT library (JJWT)
- OpenAPI / Swagger UI

## Current scaffold includes
- Base Spring Boot application entrypoint
- Stateless security config (public health/auth/docs endpoints)
- Initial DB schema migration for users, exercises, workouts, workout_exercises, workout_sets, refresh_tokens
- Health endpoint: `GET /api/v1/health`
- Test profile with H2 (PostgreSQL compatibility mode)
- Basic integration test for health endpoint

## Run locally (tests)
```bash
mvn test
```

## Run app (dev profile)
1. Create local PostgreSQL database and user that match `src/main/resources/application-dev.yml`.
2. Start the app:
```bash
mvn spring-boot:run
```
3. Open:
- Health: `http://localhost:8080/api/v1/health`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

## Next step
Implement user registration/login endpoints and JWT generation/validation.

