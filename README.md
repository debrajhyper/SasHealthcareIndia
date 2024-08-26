# SasHealthcareIndia Web Application

## 1. Introduction
SasHealthcareIndia is a web application designed to manage user authentication and provide a dashboard for user management. It includes features such as user registration, login, logout, and a searchable user list.

## 2. System Requirements
- Java Development Kit (JDK) 8 or higher
- Apache Tomcat 9.0 or compatible servlet container
- PostgreSQL 12.0 or higher
- Maven 3.6.0 or higher (for dependency management)

## 3. Project Structure
```
SasHealthcareIndia/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           ├── model/
│       │           │   └── User.java
│       │           ├── dao/
│       │           │   └── UserDAO.java
│       │           ├── util/
│       │           │   └── DatabaseUtil.java
│       │           ├── servlet/
│       │           │   ├── LoginServlet.java
│       │           │   ├── SignupServlet.java
│       │           │   ├── LogoutServlet.java
│       │           │   └── DashboardServlet.java
│       │           └── filter/
│       │               └── AuthenticationFilter.java
│       └── webapp/
│           ├── WEB-INF/
│           │   ├── lib/
│           │   └── web.xml
│           ├── login.jsp
│           ├── signup.jsp
│           └── dashboard.jsp
└── README.md
```

## 4. Setup and Installation
1. Clone the repository:
   ```
   git clone https://github.com/debrajhyper/SasHealthcareIndia.git
   ```
2. Navigate to the project directory:
   ```
   cd SasHealthcareIndia
   ```
3. Install dependencies using Maven:
   ```
   mvn clean install
   ```

## 5. Configuration
1. Database Configuration:
   - Open `src/main/java/com/example/util/DatabaseUtil.java`
   - Update the database URL, username, and password

2. Web Application Configuration:
   - Review and update `src/main/webapp/WEB-INF/web.xml` if necessary

## 6. Key Components
1. Model:
   - `User.java`: Represents a user entity

2. Data Access Object (DAO):
   - `UserDAO.java`: Handles database operations for User entities

3. Servlets:
   - `LoginServlet.java`: Manages user login
   - `SignupServlet.java`: Handles user registration
   - `LogoutServlet.java`: Manages user logout
   - `DashboardServlet.java`: Displays and manages the user dashboard

4. Filters:
   - `AuthenticationFilter.java`: Ensures proper authentication for protected resources

5. JSP Pages:
   - `login.jsp`: Login page
   - `signup.jsp`: User registration page
   - `dashboard.jsp`: Main dashboard page

## 7. Database
- Database: PostgreSQL
- Main Table: `users`
  - Columns:
    - `id` (Primary Key)
    - `username`
    - `password`
    - `created_at`

## 8. Authentication and Authorization
- Authentication is handled by `LoginServlet` and `AuthenticationFilter`
- Passwords are currently stored in plain text (see Security Considerations)
- Session management is implemented for maintaining user login state

## 9. User Interface
- Bootstrap 4.5.2 is used for responsive design
- JSTL is used in JSP pages for cleaner, more maintainable code

## 10. API Documentation
Currently, the application does not expose any external APIs. All functionality is handled through web interfaces.

## 11. Testing
- Unit tests can be added in the `src/test/java` directory
- Run tests using Maven:
  ```
  mvn test
  ```

## 12. Deployment
1. Build the WAR file:
   ```
   mvn clean package
   ```
2. Deploy the generated WAR file to your servlet container (e.g., Tomcat's webapps directory)

## 13. Maintenance and Troubleshooting
- Check Tomcat logs (`catalina.out`) for application errors
- Ensure database connectivity if user operations fail
- Clear browser cache and cookies if experiencing UI issues

## 14. Security Considerations
- Implement password hashing (e.g., using BCrypt) before storing in the database
- Use prepared statements for all database queries to prevent SQL injection
- Implement HTTPS to encrypt data in transit
- Regularly update dependencies to patch security vulnerabilities

## 15. Future Enhancements
- Implement password reset functionality
- Add user roles and role-based access control
- Implement email verification for new user registrations
- Add logging for better traceability and debugging
- Implement a more robust search feature with pagination