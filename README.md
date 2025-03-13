# Wrkr Coding Assessment
#### Task: You have been asked to develop an address book that allows a user to store (between successive runs of the program) the name and phone numbers of their friends, with the following functionality:
- To enter a friend’s name and phone number with input validation
  - I have created REST APIs to create address book which will take in the name of the address book that you want to keep and list of contacts that you want to store.
  - A contact is comprised of the name of person along with its contact number.
  - Validations:
    - When you create a new address book, the list of contacts cannot be empty. So, size of the contact list while creating the address book has to be greater than equal to 1.\
      I have used @NotEmpty for the validation on collection (i.e. list of contacts), and @Valid for the validation of Contact objects in that list.
    - In Contact objects, validations using a regular expression are done using @Pattern(regex=REGEX_PATTERN) to check the valid phone numbers, and names.

- To display the list of friends and their corresponding phone numbers sorted by their name.
  - I have developed a REST API (Get Mapping) to fetch the list of contacts in an address book(the name of address book will be our input.)
- Given another address book(s) that may or may not contain the same friends, display the list of friends that are unique to each address book (the union of all the relative
complements).\
For example given:\
Book1 = { “Bob”, “Mary”, “Jane” }\
Book2 = { “Mary”, “John”, “Jane” }\
The friends that are unique to each address book are:\
Book1 \ Book2 = { “Bob”, “John” }

  - Get API /unique-contacts will return us a list of contacts which are unique across all address books.

#### Note: The information of all the available APIs can be seen from the Swagger UI after running the Spring Boot Application.

## Prerequisites

Ensure you have the following installed:
- Java 17+
- Maven 3+
- H2 DB

## Setup Instructions

### 1. Clone the Repository
```sh
git clone <repository-url>
cd <repository-folder>
```

### 2. Database Configuration
The `application.properties` file is updated with following details:

```properties
spring.datasource.url=jdbc:h2:file:./data/addressbook;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
```

### 3. Build and Run the Application

#### Using Maven:
```sh
mvn clean install
mvn spring-boot:run
```
#### Running as a JAR:
```sh
mvn clean package
java -jar target/assessment-0.0.1-SNAPSHOT.jar
```

### 4. Running Tests
```sh
mvn test
```

## Additional Notes
- Swagger UI can be accessed at [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html).
- H2 Console can be accessed at [`http://localhost:8080/h2-console/`](http://localhost:8080/h2-console/).

## License
This project is licensed under the MIT License.

