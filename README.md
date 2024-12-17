Book Store API
Overview
This is a Spring Boot REST API built to provide token-based authentication using JWT. It secures user registration, login, token validation, and logout functionality for a Book Store system.

Key Features
User Authentication: Register, login, and generate JWT tokens.
Token Validation: Protect endpoints by validating JWT tokens.
Blacklist Tokens: Invalidate and blacklist tokens upon logout.
RESTful API Structure: Follows REST design patterns for consistent endpoints.
Technologies Used
Java 17
Spring Boot
Spring Security
JWT (JSON Web Token)
BCryptPasswordEncoder (Password hashing)
Maven
YAML Configuration
Endpoints
HTTP Method	URL	Description	Access
POST	/api/v1/public/register	User registration	Public
POST	/api/v1/public/login	User login, retrieve JWT	Public
POST	/api/v1/protected/logout	Logout and blacklist JWT	Protected
GET	/api/v1/protected/data	Example secured route	Protected
Setup and Installation
Clone the Repository

bash
Copy code
git clone https://github.com/YOUR_REPO_LINK.git
Navigate to Project Directory

bash
Copy code
cd book_store_api
Configure YAML Settings
Update the application.yml file with your environment settings.

Example application.yml configuration:

yaml
Copy code
jwt:
  secretKey: "YourSuperSecretKey"

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/bookstore_db
    username: your_db_user
    password: your_db_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
Run the Application

bash
Copy code
mvn spring-boot:run
Test the API Endpoints
Use Postman or any API client to test the endpoints.

Example Usage
Registration Example:
Request:

json
Copy code
POST /api/v1/public/register
{
   "firstName": "John",
   "lastName": "Doe",
   "email": "john.doe@example.com",
   "password": "password123"
}
Response:

json
Copy code
{
   "message": "User registered successfully!"
}
Login Example:
Request:

json
Copy code
POST /api/v1/public/login
{
   "email": "john.doe@example.com",
   "password": "password123"
}
Response:

json
Copy code
{
   "message": "Login successful!",
   "firstName": "John",
   "lastName": "Doe",
   "email": "john.doe@example.com",
   "token": "your_jwt_token_here"
}
Credits
Developer: Michael Otorael (Michael O.)
Contact: michaelotorael4@gmail.com

All rights reserved © 2024

License
This project is proprietary software. Redistribution and modifications are not allowed without explicit permission.

Feel free to adjust your repository name where needed! 😊
