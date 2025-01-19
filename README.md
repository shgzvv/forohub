# ForoHub API Documentation

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6.3 or higher
- Spring Boot 3.4.1

### Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/forohub.git
    ```
2. Navigate to the project directory:
    ```sh
    cd forohub
    ```
3. Build the project using Maven:
    ```sh
    mvn clean install
    ```

### Running the Application
To run the application, use the following command:
```sh
mvn spring-boot:run
```

## API Endpoints

#### Authentication

Login
```sh
URL: /login
Method: POST
Request Body:
{
    "username": "user",
    "password": "password"
}
Response:
{
    "token": "jwt-token"
}
```

Topics

1. Create Topic
```sh
URL: /topicos
Method: POST
Request Body:
{
    "titulo": "Topic Title",
    "mensaje": "Topic Message",
    "autor": "Author Name",
    "curso": "Course Name"
}
Response:
{
    "id": 1,
    "titulo": "Topic Title",
    "mensaje": "Topic Message",
    "autor": "Author Name",
    "curso": "Course Name"
}
```

2. Get All Topics
```sh
URL: /topicos
Method: GET
Response:
[
    {
        "id": 1,
        "titulo": "Topic Title",
        "mensaje": "Topic Message",
        "autor": "Author Name",
        "curso": "Course Name"
    }
]
```

3. Get Topic by ID
```sh
URL: /topicos/{id}
Method: GET
Response:
{
    "id": 1,
    "titulo": "Topic Title",
    "mensaje": "Topic Message",
    "autor": "Author Name",
    "curso": "Course Name"
}
```

4. Update Topic
```sh
URL: /topicos/{id}
Method: POST
Request Body:
{
    "titulo": "Updated Title",
    "mensaje": "Updated Message",
    "autor": "Updated Author",
    "curso": "Updated Course"
}
Response:
{
    "id": 1,
    "titulo": "Updated Title",
    "mensaje": "Updated Message",
    "autor": "Updated Author",
    "curso": "Updated Course"
}
```

5. Delete Topic
```sh
URL: /topicos/{id}
Method: DELETE
Response:
{
    "message": "Topic deleted successfully"
}
```

### Security
**JWT Authentication**

The API uses JWT for authentication. Include the token in the Authorization header as follows:


```sh
Authorization: Bearer <jwt-token>
```


### Configuration
**Application Properties**

Configure the application properties in src/main/resources/application.properties:

```sh
api.security.secret=your-secret-key
```


