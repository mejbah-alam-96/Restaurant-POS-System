# Restaurant-POS-System
Basic Restaurant POS System {Console Based}

# 🍽️ Restaurant POS System (Java Console Application)

A **console-based Restaurant Point of Sale (POS) system** developed in **Java** using **Object-Oriented Programming (OOP)** principles.  
This application helps restaurant staff efficiently manage menu items, handle customer orders, process payments, and track daily sales.

---

## 🚀 Project Overview

This project simulates a real-world restaurant billing system where users can:

- Manage menu items (food & beverages)
- Create and process customer orders
- Generate bills
- Handle multiple payment methods
- Track daily sales

The system is designed with a strong focus on **clean architecture** and **OOP best practices**.

---

## ✨ Key Features

### 📋 Menu Management
- Display all available menu items
- Supports two item types:
  - 🍔 `FoodItem`
  - ☕ `BeverageItem`
- Shows item details like price and category

---

### 🧾 Order Management
- Create orders for specific tables
- Add multiple items with quantity
- View detailed order summary with subtotal

---

### 💳 Payment Processing
Supports multiple payment methods through abstraction:

- 💵 Cash Payment  
- 💳 Card Payment  
- 📱 Mobile Payment (bKash)

Each method is implemented using separate classes for flexibility and scalability.

---

### 📊 Sales Reporting
- Tracks total daily sales
- Displays active (pending) orders

---

### 🖥️ Console-Based UI
- Simple and interactive menu-driven interface
- Easy to navigate and use

---

## 🏗️ Object-Oriented Design

This project demonstrates key **OOP concepts**:

---

### 1️⃣ Inheritance & Abstraction
- `MenuItem` → Abstract base class
- Derived classes:
  - `FoodItem` (adds `cuisineType`)
  - `BeverageItem` (adds `isHot`)

---

### 2️⃣ Polymorphism
- Methods overridden in child classes:
  - `getItemType()`
  - `calculatePrice()`
  - `toString()`
- All items handled using:
```java
List<MenuItem>
```

---

### 3️⃣ Interface
- `Payment` interface defines common behavior
- Implementations:
  - `CashPayment`
  - `CardPayment`
  - `MobilePayment`

---

### 4️⃣ Composition
- `Order` contains multiple `OrderItem`
- `OrderItem` includes:
  - `MenuItem`
  - `Quantity`

---

### 5️⃣ Encapsulation
- Private fields with public getters/setters
- Ensures data security and controlled access

---

## 📂 Project Structure (Example)

```
RestaurantPOS/
│── Main.java
│
├── model/
│   ├── MenuItem.java
│   ├── FoodItem.java
│   ├── BeverageItem.java
│   ├── Order.java
│   └── OrderItem.java
│
├── payment/
│   ├── Payment.java
│   ├── CashPayment.java
│   ├── CardPayment.java
│   └── MobilePayment.java
│
└── service/
    └── RestaurantPOS.java
```

---