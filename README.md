# Wallet Service Recargapay

## Requirements

- Java 21
- Maven
- Docker (optional, to run the service in a container)
- MongoDB

## Configuration

### 1. Clone the repository

```sh
git clone <REPOSITORY_URL>
cd wallet-service-recargapay
```

### 2. Configure MongoDB

Ensure that MongoDB is running and configured as per the `application.properties`:

```ini
spring.data.mongodb.uri=mongodb+srv://mongodb-dev:BbNnt5Arxz8QslxJ@sandbox.h7rbn.mongodb.net/recargapay?retryWrites=true&w=majority&appName=sandbox
```

### 3. Build the project

```sh
mvn clean install
```

### 4. Run the service

#### Using Maven

```sh
mvn spring-boot:run
```

#### Using the generated JAR

```sh
java -jar target/wallet-service-recargapay-0.0.1-SNAPSHOT.jar
```

#### Using Docker

1. Build the Docker image:

```sh
docker build -t wallet-service-recargapay .
```

2. Run the Docker container:

```sh
docker run -p 8080:8080 wallet-service-recargapay
```

## Access the API

The API will be available at `http://localhost:8080/api/v1`.

## Swagger Documentation

Swagger documentation can be accessed at `http://localhost:8080/docs`.
