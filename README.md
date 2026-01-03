
# 🚗💨 Vehicle Rental Management System

![GitHub Stars](https://img.shields.io/github/stars/kerfaiyass54/vehicule_rent_app?style=social)
![GitHub Forks](https://img.shields.io/github/forks/kerfaiyass54/vehicule_rent_app?style=social)
![GitHub Issues](https://img.shields.io/github/issues/kerfaiyass54/vehicule_rent_app)
![Java](https://img.shields.io/badge/Java-21-blue?logo=java)
![Spring](https://img.shields.io/badge/Spring_Boot-3.3.4-green?logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue?logo=postgresql)
![Maven](https://img.shields.io/badge/Maven-3.9.9-orange?logo=apache-maven)

---

## 🌟 Overview

Welcome to **Vehicle Rental Management System**, a comprehensive backend solution built with **Spring Boot** that enables seamless management of vehicle rentals, repairs, subscriptions, and user roles. This system is designed to streamline operations for **administrators, suppliers, repairers, and clients**, providing a robust platform for handling all aspects of vehicle rentals.

### Key Features:
✅ **Multi-role Access Control** – Tailored permissions for Admins, Suppliers, Repairers, and Clients.

✅ **Vehicle Management** – Comprehensive inventory with categories, statuses, and suppliers.

✅ **Rental System** – Booking, tracking, and managing rentals with flexible periods.

✅ **Repair & Maintenance** – Ticketing system, repair tracking, and status updates.

✅ **Subscription Plans** – Customizable subscription tiers for clients.

✅ **Secure Authentication** – OAuth2 and Keycloak integration for secure user management.

✅ **API Documentation** – Swagger/OpenAPI for easy API exploration and integration.

---

## ✨ Features

### **For Admins:**

🔹 Manage users, locations, and system-wide configurations

### **For Suppliers:**

🔹 Manage vehicle inventory and categories

🔹 Update vehicle details and availability

🔹 Manage supplier-specific subscriptions and addresses

🔹 Manage all the demands of repairings


### **For Repairers:**

🔹 Handle vehicle maintenance and repairs

🔹 Create and manage repair tickets

🔹 Update repair statuses and send approvals to suppliers

### **For Clients:**

🔹 Book and manage vehicle rentals

🔹 Track repair statuses of their vehicles

🔹 Manage their subscriptions and rental history

---

## 🛠️ Tech Stack

### **Core Technologies:**
- **Language:** Java 21, Python
- **Framework:** Spring Boot 3.3.4
- **Database:** PostgreSQL 17
- **Build Tool:** Maven 3.9.9
- **Authentication:** Keycloak 25.0.0
- **API Documentation:** SpringDoc OpenAPI

### **Key Dependencies:**
- Spring Security (OAuth2)
- Spring Data JPA
- Lombok (for reducing boilerplate code)
- H2 Database (for testing)
- Spring Boot Starter Test

---

## 📦 Installation

### **Prerequisites**
Before you begin, ensure you have the following installed on your system:
- [Java JDK 21](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [PostgreSQL 17](https://www.postgresql.org/download/)
- [Maven 3.9.9](https://maven.apache.org/install.html)
- [Keycloak 25.0.0](https://www.keycloak.org/download)

### **Quick Start**

#### **1. Clone the Repository**
```bash
git clone https://github.com/kerfaiyass54/vehicule_rent_app.git
cd vehicule_rent_app
```

#### **2. Set Up Keycloak**
1. **Start Keycloak Server:**
   ```bash
   kc.bat start-dev
   ```
   (or `./kc.sh start-dev` on macOS/Linux)

2. **Access Keycloak Admin Console:**
   Open [http://localhost:8080](http://localhost:8080) in your browser.

3. **Create Realm and Client:**
   - Go to **Realms** → **Create Realm** → Name it `vehicule-app`.
   - Navigate to **Clients** → **Create Client** → Name it `vehicule-backend`.
   - Set **Client Protocol** to `openid-connect`.
   - Under **Settings**, set **Access Type** to `confidential`.
   - Generate a **Client Secret** and note it down for later use.

#### **3. Configure Database**
1. **Create Database:**
   ```sql
   CREATE DATABASE vehiculerents;
   ```

2. **Update Database Credentials:**
   Modify `src/main/resources/application.properties` with your PostgreSQL credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/vehiculerents
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

#### **4. Run the Application**
```bash
mvn spring-boot:run
```

The application will start on port `8100` by default.


## 🔧 Configuration

### **Environment Variables**
The application uses `application.properties` for configuration. Key properties include:

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/vehiculerents
spring.datasource.username=postgres
spring.datasource.password=krkrfrang

# Keycloak Configuration
keycloak.server-url=http://localhost:8080
keycloak.realm=vehicule-app
keycloak.client-id=vehicule-backend
keycloak.client-secret=V1bud61xSEDR2Xb6p5M9vDqQ27E9i0l9

# JWT Configuration
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080/realms/vehicule-app
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=http://localhost:8080/realms/vehicule-app/protocol/openid-connect/certs
```

### **Customization**
- **Database Schema:** Modify `database/schema/init.sql` to customize tables and relationships.
- **Sample Data:** Update `database/data/*.sql` files to populate your database with custom data.
- **Keycloak Realms:** Adjust the Keycloak realm and client configurations as needed.

---

## 🤝 Contributing

We welcome contributions from the community! Here's how you can get involved:

### **Steps to Contribute:**
1. **Fork the Repository**
2. **Create a Feature Branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. **Commit Your Changes**
   ```bash
   git commit -m "Add your descriptive message"
   ```
4. **Push to the Branch**
   ```bash
   git push origin feature/your-feature-name
   ```
5. **Open a Pull Request**

### **Development Setup**
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/kerfaiyass54/vehicule_rent_app.git
   ```
2. **Set Up Keycloak and Database:**
   Follow the **Quick Start** instructions above.
3. **Run the Application:**
   ```bash
   mvn spring-boot:run
   ```

### **Code Style Guidelines**
- Follow the **Java Code Conventions** and **Spring Boot Best Practices**.
- Use **Lombok** annotations to reduce boilerplate code.
- Write **unit tests** for new features.
- Ensure **consistent logging** using SLF4J.
  
---

## 🚀 Get Started Today!

Ready to dive in? Clone the repository, set up your environment, and start contributing to the **Vehicle Rental Management System**!

```bash
git clone https://github.com/kerfaiyass54/vehicule_rent_app.git
cd vehicule_rent_app
mvn spring-boot:run
```

Join our community, share your ideas, and help us build a better vehicle rental management system! 🚀
```

This README is designed to be engaging, informative, and easy to follow, encouraging developers to star and contribute to your project. It includes all the necessary details while maintaining a professional and welcoming tone.
