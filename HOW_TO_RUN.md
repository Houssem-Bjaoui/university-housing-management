# How to Run the Application

## Prerequisites
- Java 17+
- Node.js 16+
- MySQL 8.x running on port 3306
- Maven installed

## Database Setup
```bash
# Create database (run in MySQL)
CREATE DATABASE gestion_foyer;
```

## Backend (Spring Boot)
```bash
# Navigate to backend folder
cd university-housing-management-Backend

# Run the application
mvn spring-boot:run
```
**Backend runs on:** http://localhost:8080

## Frontend (Angular)
```bash
# Navigate to frontend folder (open new terminal)
cd frontend

# Install dependencies (first time only)
npm install

# Run the application
npm start
```
**Frontend runs on:** http://localhost:4200

## Access the Application
Open browser: http://localhost:4200

---

## Quick Commands

### Backend
```bash
cd university-housing-management-Backend
mvn spring-boot:run
```

### Frontend
```bash
cd frontend
npm install
npm start
```
