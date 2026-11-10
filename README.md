# oops_problems

Interview problems related to Object Oriented Programming / Low Level Design, each implemented as a separate Maven module in Java.

## Modules

| Module | Problem |
|--------|---------|
| `01ride-sharing-app` | Ride sharing app |
| `02parking-lot` | Parking lot (OOAD example) |
| `03movie-booking-app` | Movie ticket booking system |

## Requirements

- JDK 25
- Maven 3.9+

## Build and test

From the repository root:

```bash
# build and test everything
mvn test

# a single module
mvn -pl 03movie-booking-app test
```
