# 🛒 E-Commerce Application (Backend)

## Overview

This is a modular e-commerce backend system developed using **Java** and **Spring Boot**. The application is designed with role-based access control and provides RESTful APIs for seamless integration with frontend systems. The backend supports product browsing, user order placement, and administrative functions such as order management and product updates.

### 🔧 Tech Stack

- **Language:** Java 21  
- **Framework:** Spring Boot 3  
- **Database:** MongoDB  
- **Build Tool:** Gradle  
- **Architecture:** RESTful API-based backend  
- **Authentication:** JWT (JSON Web Tokens)  
- **Dependency Injection & Modularization:** Spring DI  

---

## ✨ Features

### 👤 User Features

- Register and log in securely.
- View and search available products.
- Add products to cart.
- Place orders and view order history.

### 🛠 Admin Features

#### 🧑‍💼 Super Admin

- View all registered users and their details.
- Access user carts and order histories.
- View all product listings and inventory.
- Monitor overall system activity.

#### 📦 Product Admin

- Add new products with images, price, description, etc.
- Update or delete existing product details.

#### 🚚 Order Admin

- Manage orders placed by users.
- Update order status (e.g., pending, shipped, delivered).
- View order-related data.
