# ShowCase - Learning Management System

A Spring Boot application demonstrating a learning management system with health monitoring endpoints, database integration, and schema management.

## Overview

This project showcases a modern Spring Boot application with the following features:
- RESTful API endpoints for health monitoring
- Database support (H2 and PostgreSQL)
- Liquibase database migration and versioning
- JPA/Hibernate for ORM
- Project Lombok for reducing boilerplate code

## Prerequisites

### Required Software

1. **Java 25** - The project uses Java 25
   - Download from: https://jdk.java.net/25/
   - Or use a package manager:
     ```bash
     # macOS with Homebrew
     brew install java25
     
     # Or download manually and set JAVA_HOME
     export JAVA_HOME=/path/to/java25
     export PATH=$JAVA_HOME/bin:$PATH
     ```

2. **Maven 3.6+** - For building and running the project
   - The project includes Maven wrapper scripts (`mvnw` and `mvnw.cmd`), so Maven doesn't need to be installed separately
   - If needed, download from: https://maven.apache.org/download.cgi

### Database Options

The project supports two databases (configured via Spring profiles):

- **H2 Database** (Default) - In-memory database, no installation needed
- **PostgreSQL** (Optional) - For production use
  - Download from: https://www.postgresql.org/download/
  - Or install via package manager:
    ```bash
    # macOS with Homebrew
    brew install postgresql
    ```
    FOR LOCAL USE: 
  - Download for Windows: https://docs.docker.com/desktop/setup/install/windows-install/
  - Download for MacOS: https://docs.docker.com/desktop/setup/install/mac-install/
  - Download for Linux: https://docs.docker.com/desktop/setup/install/linux/

    Start the db: 
  - 1. make sure that in resource/application.properties, the profile is set to postgresql: `spring.profiles.active=postgresql` and remove h2
  - 2. run `docker compose up` in the root of the project, this will start a postgres db with the credentials specified in `application-postgresql.properties`
    you can also append '-d' to run it in detached mode: `docker compose up -d` (it will run in the background and not show everything in the terminal)
  - 3. To stop the db, run `docker compose down`
## Project Dependencies (for everything bellow: installation happens via Maven wrapper)

### Spring Boot Starters
- **spring-boot-starter-webmvc** - Web and REST support
- **spring-boot-starter-data-jpa** - JPA/Hibernate database access
- **spring-boot-starter-liquibase** - Database schema versioning and migration
- **spring-boot-h2console** - H2 web console (useful for debugging)

### Databases
- **h2** - Embedded in-memory database (development/testing)
- **postgresql** - PostgreSQL JDBC driver (production)

### Utilities
- **lombok** - Reduces boilerplate code (annotations for getters, setters, constructors, etc.)

### Test Dependencies
- spring-boot-starter-data-jpa-test
- spring-boot-starter-liquibase-test
- spring-boot-starter-webmvc-test

## Installation & Setup

### 1. Clone or Navigate to the Project

### 2. Install Java 25
```bash
# Verify Java is installed
java -version

# Should display Java 25.x
```

### 3. Build the Project
Using the Maven wrapper (no separate Maven installation needed):
```bash
# macOS/Linux
./mvnw clean install

# Windows
mvnw.cmd clean install
```

This command will:
- Download all dependencies
- Compile the source code
- Run tests
- Package the application

## Running the Project

### Using Maven (THIS IS ABSOLUTELY THE PREFFERED WAY)
```bash
# macOS/Linux
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

### Using the JAR file 
```bash
# First build the project
./mvnw clean package

# Then run the JAR
java -jar target/showCase-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## Configuration

### Application Properties
Configuration is managed in `src/main/resources/application.properties`:

```properties
spring.application.name=showCase
spring.profiles.active=h2  # Use 'postgresql' to switch databases
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml
```

### Database Profiles

#### H2 Profile (Default)
File: `src/main/resources/application-h2.properties`
- In-memory database
- No external setup required
- Perfect for development and testing
- H2 Console available at: `http://localhost:8080/h2-console`

#### PostgreSQL Profile
File: `src/main/resources/application-postgresql.properties`
- Requires PostgreSQL server running
- Configure connection details in the properties file
- Activate with: `spring.profiles.active=postgresql`

### Database Migrations
- Managed by Liquibase
- Changelog location: `src/main/resources/db/changelog/db.changelog-master.xml`
- Changes: `src/main/resources/db/changelog/changes/001-initial-schema.xml`



### Java Version Mismatch
If you get "wrong version" errors:
```bash
# Check your current Java version
java -version

# Set JAVA_HOME to Java 25
export JAVA_HOME=/path/to/java25
export PATH=$JAVA_HOME/bin:$PATH
```

### Maven Build Fails
```bash
# Clear local Maven cache and rebuild
./mvnw clean install -U
```

### Port Already in Use
If port 8080 is already in use:
```bash
# Run on a different port
./mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

### H2 Console Access
- Navigate to: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave empty)



### Building and Testing
```bash
# Run tests only
./mvnw test

# Build without running tests
./mvnw clean install -DskipTests

# Run specific test class
./mvnw test -Dtest=ShowCaseApplicationTests 
```

## API documentation / frontend guide
We use the supported OpenAPI/Swagger-UI integration as comfortable way to document
existing backend endpoints, their expected JSON schema/blueprint as well as their possible
return types. The Swagger-UI is also a functional frontend that can be used for manual testing.
Reachable under: http://localhost:8080/swagger-ui/index.html#/

## Next Steps

1. Explore the health check endpoints in a browser or with curl
2. Review database migration files for schema understanding
3. Add new REST endpoints in the controller package
4. Implement business logic and entities as needed
5. Switch to PostgreSQL for production deployments

## License

See LICENSE file for details

## Support

For issues or questions, refer to:
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Liquibase Documentation](https://docs.liquibase.com/)
