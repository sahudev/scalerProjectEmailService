# Email Service - E-commerce Microservice

Note: Please refer all these repos for the complete project 
https://github.com/sahudev/scalerProjectUserService; 
https://github.com/sahudev/scalerProjectPaymentService; 
https://github.com/sahudev/scalerProjectProductService; 
https://github.com/sahudev/scalerProjectEmailService;

## Overview
The **Email Service** is a core component of the e-commerce microservices architecture, responsible for sending transactional emails such as order confirmations, payment receipts, and account-related notifications. This service integrates seamlessly with other microservices like the **Product Service**, **User Service**, and **Payment Service** to provide real-time email notifications to users.

Built using **Spring Boot**, the Email Service ensures reliable, scalable, and efficient email delivery for the e-commerce platform, integrating with external email services such as **Amazon SES**, **SendGrid**, or **SMTP servers**.

## Features
- Send transactional emails (order confirmations, receipts, shipping updates).
- Support for email templates to dynamically generate personalized messages.
- Queue management for handling high volumes of email traffic.
- Integration with third-party email providers (Amazon SES, SendGrid, etc.).
- Asynchronous email sending for optimized performance.
  
## Technologies Used
- **Java 11**: Core language for the backend service.
- **Spring Boot**: Framework for building the microservice architecture.
- **Spring Mail**: For sending emails through various email services (SMTP, SES, etc.).
- **Thymeleaf**: For rendering email templates with dynamic content.
- **Spring Data JPA**: To interact with the database for managing email logs.
- **RabbitMQ / Kafka**: Message queue system for handling email sending asynchronously.
- **Docker**: For containerization and easy deployment.
- **AWS SES / SendGrid**: Third-party email delivery service providers.

## Project Structure
The **Email Service** follows **Spring Boot’s MVC architecture**, organized into the following layers:
- **Controller**: Exposes REST APIs for triggering email operations.
- **Service**: Contains business logic for constructing and sending emails.
- **Repository**: Manages email logs and historical data of sent emails.
- **Model**: Represents the `Email` entity, storing metadata about each email (status, recipient, etc.).

## API Endpoints

### Email Sending Endpoints:
1. **Send Order Confirmation Email**
   - `POST /email/order-confirmation`
   - Sends an order confirmation email to the user once an order is placed.
   - Example Payload:
     ```json
     {
       "email": "customer@example.com",
       "orderId": "12345",
       "productName": "Smartphone X"
     }
     ```

2. **Send Payment Receipt Email**
   - `POST /email/payment-receipt`
   - Sends a receipt email to the user after payment is successfully processed.

3. **Send Custom Email**
   - `POST /email/custom`
   - Sends a custom email to a user with a subject and body content.

4. **View Email Logs**
   - `GET /email/logs`
   - Retrieves logs of previously sent emails for audit purposes.

## Installation

### Prerequisites:
- **Java 11** or higher
- **Maven** for dependency management
- **MySQL** for the database (local or hosted)
- **Docker** (optional) for containerization
- **SMTP Server / Amazon SES / SendGrid API Keys** for email delivery

### Steps to Set Up Locally:
1. **Clone the repository**:
   ```bash
   git clone https://github.com/sahudev/scalerProjectEmailService
   ```

2. **Configure the Email Provider**:
   - Update the `application.properties` file with your email service credentials (Amazon SES, SendGrid, or SMTP server details):
     ```properties
     spring.mail.host=smtp.yourprovider.com
     spring.mail.username=your_username
     spring.mail.password=your_password
     spring.mail.port=587
     ```

3. **Run the Application**:
   Navigate to the project folder and start the application:
   ```bash
   mvn spring-boot:run
   ```

4. **Test the APIs**:
   Use a tool like **Postman** to trigger email-sending requests. Example for sending an order confirmation:
   ```bash
   curl -X POST http://localhost:8080/email/order-confirmation -H "Content-Type: application/json" -d '{"email": "customer@example.com", "orderId": "12345", "productName": "Smartphone X"}'
   ```

### Docker Deployment:
To deploy the service using Docker:
1. Build the Docker image:
   ```bash
   docker build -t email-service .
   ```

2. Run the Docker container:
   ```bash
   docker run -p 8080:8080 email-service
   ```

## AWS Deployment
For cloud deployment, you can deploy the Email Service on **AWS** using **EC2** instances or **AWS Elastic Beanstalk**. Use **Amazon SES** for scalable email delivery, and leverage **AWS SQS** or **RabbitMQ** for handling asynchronous email queue processing. Make sure to secure the infrastructure using **AWS IAM** roles and **Security Groups**.

## Queue-Based Email Processing
For handling high email traffic or spikes during major sales events, you can integrate a message queue system like **RabbitMQ** or **Kafka**. This enables the service to process email requests asynchronously, ensuring non-blocking operations and improved performance.

## Security
- **Spring Security**: Used to secure API access, ensuring only authorized services can trigger email sending.
- **Email Content Encryption**: For sensitive email content, encryption can be applied before sending.
- **TLS/SSL**: All emails are sent securely over TLS/SSL to ensure data privacy during transmission.

## Contribution
Contributions are welcome! Please open issues or submit pull requests to improve the Email Service functionality or add new features.
