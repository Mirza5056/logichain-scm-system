# logichain-scm-system
An enterprise-grade supply chain management system built with Java and Spring Boot, featuring modular microservices architecture, secure APIs, and real-time logistics tracking.

# LogiChain – Enterprise Supply Chain Management System 🚚

**LogiChain** is a modular, microservices-based Supply Chain Management System designed to streamline logistics operations in an enterprise environment. Built using **Java 17**, **Spring Boot**, and **MySQL**, it features authentication, inventory, order processing, and shipment tracking.

---

## 🔧 Tech Stack

- Java 17, Spring Boot 3.x
- Spring Security (JWT-based Authentication & Role-based Access Control)
- MySQL, Hibernate (JPA)
- Docker, Docker Compose
- REST APIs, Maven (Multi-module)
- Git & GitHub

---

## 🧱 Modules

- `auth-service`: Handles user registration, login, JWT token issuance
- `inventory-service`: Manages inventory, stock, product availability
- `order-service`: Handles order placement, status, and fulfillment
- `logistics-service`: Manages shipment tracking and delivery
- `common-data`: Shared DTOs and entities across services
- `common-security`: Shared JWT and security configuration

---

## 📁 Folder Structure

```
logichain-scm-system/
├── auth-service/
├── inventory-service/
├── order-service/
├── logistics-service/
├── common-data/
├── common-security/
├── docker-compose.yml
├── README.md
├── .gitignore
├── LICENSE
└── docs/
```

---

## 👥 Team Members

- 👨‍💼 Kamran Akhtar (Team Lead)
- 👤 Member 1
- 👤 Member 2
- 👤 Member 3
- 👤 Member 4

---

## 🏁 Getting Started

```bash
# Clone the repository
git clone https://github.com/Mirza5056/logichain-scm-system.git

# Navigate to the project folder
cd logichain-scm-system

# Build and run all services
docker-compose up --build
```

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
