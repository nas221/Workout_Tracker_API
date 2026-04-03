# Workout Tracker API Architecture Draft

## Current scaffold (today)

```text
Client (Postman/Web)
        |
        v
Spring Security Filter Chain
        |
        +--> permitAll: /api/v1/health, /api/v1/auth/**, Swagger
        |
        v
HealthController (/api/v1/health)
        |
        v
JSON response {status, service, timestamp}
```

## Near-term target (register first)

```text
Client (POST /api/v1/auth/register)
        |
        v
AuthController
        |
        v
AuthService.register()
   |            |
   |            +--> PasswordEncoder (BCrypt)
   |
   +--> UserRepository (JPA)
                |
                v
        PostgreSQL users table
                |
                v
RegisterResponse (id, email, displayName)
```

## Component view (target v1)

```mermaid
flowchart LR
    C[Client] --> SEC[Spring Security]
    SEC --> AUTH[AuthController]
    SEC --> EX[ExerciseController]
    SEC --> WO[WorkoutController]
    SEC --> RP[ReportController]

    AUTH --> AS[AuthService]
    AS --> ENC[PasswordEncoder]
    AS --> UR[UserRepository]

    EX --> EXS[ExerciseService]
    EXS --> ER[ExerciseRepository]

    WO --> WOS[WorkoutService]
    WOS --> WR[WorkoutRepository]
    WOS --> WER[WorkoutExerciseRepository]
    WOS --> WSR[WorkoutSetRepository]

    RP --> RPS[ReportService]
    RPS --> WR
    RPS --> WER
    RPS --> WSR

    UR --> DB[(PostgreSQL)]
    ER --> DB
    WR --> DB
    WER --> DB
    WSR --> DB
```

