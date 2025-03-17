# CGI home assignment

This is a web application that allows users to select seats for a flight. The app provides price filtering and automatic recommendations.

---

## Features

- **Seat Selection** – Users can choose available seats from a visual seating layout.
- **Business & Economy Class** – Differentiation between business and economy class seats.
- **Seat Booking** – Users can book selected seats.
- **Seat Filtering** – Users can filter seats based on:
   - Window seats
   - Extra legroom
   - Near exit

---

## Technologies Used

### **Frontend**
- **Vite Framework**
- **Tailwind CSS**
- **Axios**
- **Docker**

### **Backend**
- **Spring Boot 3**
- **Java 21**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Docker**

---

## Getting Started

### Prerequisites

- Java 21
- Gradle
- PostgreSQL 
- Docker

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/rKlauss/cgi-backend.git
   cd cgi-backend
    ```
    ```bash
   git clone https://github.com/rKlauss/cgi-frontend.git
   cd cgi-frontend
   ```

---

## Docker Setup

1. Navigate to frontend folder and run:
```bash
docker build -t cgi-frontend .
```

2. **Navigate to backend folder and Run with Docker Compose:**

```bash
docker-compose up --build
```
---
## Testing

1. Go to http://localhost:5173
2. Customer can choose between 4 Citys.
3. User can test using locations, price range, date time and without any filters user can press "Search Flights" to view all flights.

| Citys   |
| ------------ | 
| Tallinn    |
| Saaremaa  | 
| Sofia  |
| Helsingi |


## Author
Rasmus Klaus
