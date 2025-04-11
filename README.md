

# Spring Boot Database Locking

This project demonstrates two types of database locking mechanisms — **Optimistic Locking** and **Pessimistic Locking** — with a focus on booking seats for movies.

## Insert Records to Seat Table

```sql
INSERT INTO seat (movieName, booked, version) VALUES 
('Inception', false, 0),
('Titanic', false, 0),
('Avengers: Endgame', false, 0),
('Interstellar', false, 0),
('The Dark Knight', false, 0);
```

---

## Optimistic Validation

### cURL Command

```bash
curl -X 'GET' \
  'http://localhost:9191/booking/optimistic/2' \
  -H 'accept: */*'
```

### Results

```plaintext
Thread-1 is attempting to book the seat optimistically...
Thread-1 fetched seat with version: 0
Thread-2 is attempting to book the seat optimistically...
Thread-2 fetched seat with version: 0
Thread-1 successfully booked the seat! and version is 1
Thread-2 failed: Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect): [com.javatechie.entity.Seat#2]
```

## Pessimistic Locking in Action: Seat Booking Example

### cURL Command

```bash
curl -X 'GET' \
  'http://localhost:9191/booking/optimistic/2' \
  -H 'accept: */*'
```

### Scenario

Two threads (`Thread-1` and `Thread-2`) attempt to book the same seat (ID `2`) using **Pessimistic Locking**. This locking mechanism ensures that only one thread can access and modify the seat at a time.

---

### Results

```plaintext
Thread-1 is attempting to fetch the seat with a pessimistic lock...
Thread-2 is attempting to fetch the seat with a pessimistic lock...
Thread-1 acquired the lock for seat ID: 2
Thread-1 is booking the seat...
Thread-1 successfully booked the seat with ID: 2
Thread-2 acquired the lock for seat ID: 2
Thread-2 failed: Seat ID 2 is already booked!
Thread-2 failed: Seat already booked
```

---

### Key Points

- **Exclusive Access**:  
  Pessimistic Locking ensures that only one thread can modify the seat at a time by locking it in the database during the transaction.

- **Sequential Processing**:  
  Threads attempting to access the same entity are processed one after the other, avoiding conflicts and ensuring data consistency.

- **Failure Handling**:  
  If a thread finds the entity in an invalid state (e.g., already booked), it throws an exception to indicate the operation cannot proceed.

---
