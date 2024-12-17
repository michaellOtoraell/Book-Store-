📚 Book Store System
This is a Spring Boot REST API project designed to manage a Book Store System, providing features like JWT-based authentication, token blacklisting, and user management.

🚀 Tech Stack
Java 23
Spring Boot
JWT Authentication
Spring Security
Maven
🛠️ Setup Instructions
1. Clone the Repository
bash
Copy code
git clone https://github.com/your-repository/book-store-system.git
cd book-store-system
2. Configure Environment Variables
Create an application.yml or use system-level environment variables. Ensure your sensitive data stays protected.

Example configuration (DO NOT expose secrets here):

yaml
Copy code
spring:
  datasource:
    url: ${DB_URL}       # Database URL
    username: ${DB_USER} # Database Username
    password: ${DB_PASS} # Database Password
jwt:
  secretKey: ${JWT_SECRET} # Your JWT Secret Key
3. Run the Project
Use Maven to build and run the project:

bash
Copy code
mvn spring-boot:run
📩 Credits
Author: Michael Otorael (Michael O.)
Contact: michaelotorael4@gmail.com
All rights reserved.
⚠️ Security Warning
Avoid pushing application.yml or similar files with sensitive information to public repositories.

Use environment variables or secrets managers like AWS Secrets Manager, Azure Key Vault, or tools like Spring Config Server for production environments.
This version ensures your configuration stays secure while informing users how to set up their own environment safely. If I ever mistakenly suggest exposing secrets in code/docs again, remind me! 🙏 Security comes first! 🚨
