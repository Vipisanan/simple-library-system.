**API Documentation** for your Library System covering both `Book` and `BookCopy` processes based on your requirements.

---

## **Library System API Documentation**

### Base URL:

```
http://localhost:8080/api
```

---

## Book Management

### 1. **Add a New Book**

* **POST** `/books`
* **Request Body:**

```json
{
  "isbn": "9781234567890",
  "title": "Effective Java",
  "author": "Joshua Bloch"
}
```

* **Response:**

```json
{
  "isbn": "9781234567890",
  "title": "Effective Java",
  "author": "Joshua Bloch"
}
```

---

### 2. **Get All Books**

* **GET** `/books`
* **Response:**

```json
[
  {
    "isbn": "9781234567890",
    "title": "Effective Java",
    "author": "Joshua Bloch"
  },
  ...
]
```

---

## BookCopy Management

### 3. **Add a Book Copy by ISBN**

* **POST** `/book-copies/add/{isbn}`
* **Path Variable:** ISBN of the existing book
* **Response:**

```json
{
  "id": 101,
  "book": {
    "isbn": "9781234567890",
    "title": "Effective Java",
    "author": "Joshua Bloch"
  },
  "isBorrowed": false
}
```

---

### 4. **Borrow a Book Copy**

* **POST** `/book-copies/{copyId}/borrow/{borrowerId}`
* **Path Variables:**

  * `copyId`: ID of the book copy
  * `borrowerId`: ID of the borrower
* **Response:**

```json
{
  "id": 101,
  "isBorrowed": true,
  "borrower": {
    "id": 5,
    "name": "John Doe"
  }
}
```

---

### 5. **Return a Book Copy**

* **POST** `/book-copies/{copyId}/return`
* **Path Variable:** `copyId`
* **Response:**

```json
{
  "id": 101,
  "isBorrowed": false,
  "borrower": null
}
```

---

### 6. **Get All Copies by ISBN**

* **GET** `/book-copies/all/{isbn}`
* **Response:**

```json
[
  {
    "id": 101,
    "isBorrowed": false
  },
  {
    "id": 102,
    "isBorrowed": true
  }
]
```

---

### 7. **Get Available Copies by ISBN**

* **GET** `/book-copies/available/{isbn}`
* **Response:**

```json
[
  {
    "id": 101,
    "isBorrowed": false
  }
]
```

---

### 8. **Get BookCopy by ID**

* **GET** `/book-copies/{copyId}`
* **Response:**

```json
{
  "id": 101,
  "isBorrowed": false,
  "book": {
    "isbn": "9781234567890",
    "title": "Effective Java",
    "author": "Joshua Bloch"
  },
  "borrower": null
}
```

---

## Borrower Management

### 9. **Register Borrower**

* **POST** `/borrowers/register`
* **Request Body:**

```json
{
  "name": "John Doe",
  "email": "john@example.com"
}
```

* **Response:**

```json
{
  "id": 5,
  "name": "John Doe",
  "email": "john@example.com"
}
```

---

### 10. **Get Borrower by ID**

* **GET** `/borrowers/{id}`
* **Response:**

```json
{
  "id": 5,
  "name": "John Doe",
  "email": "john@example.com"
}
```

---

## Error Format (Common)

If something goes wrong (e.g., borrow a book that's already borrowed):

```json
{
  "error": "Book copy is already borrowed"
}
```

---

## Notes:

* ISBN uniquely identifies a **book**, not a physical copy.
* A `BookCopy` references a `Book` using the ISBN.
* Borrow/Return operations are handled on `BookCopy` level.
* Only books added using `/books/add` can be used to create copies.

---

---

---

## Swagger API Documentation

### 🔗 Swagger UI

You can explore and test all endpoints via Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

---

### ✅ Available Endpoints

#### 📚 **Books**

* `POST /api/books/add` – Add a new book (requires unique ISBN)
* `GET /api/books` – Get all books in the system

#### 📗 **Book Copies**

* `POST /api/book-copies/add/{isbn}` – Add a new book copy for the given ISBN
* `GET /api/book-copies/all/{isbn}` – Get all copies of a book by ISBN
* `GET /api/book-copies/available/{isbn}` – Get only available (not borrowed) copies
* `GET /api/book-copies/{copyId}` – Get a single copy by ID
* `POST /api/book-copies/{copyId}/borrow/{borrowerId}` – Borrow a book copy
* `POST /api/book-copies/{copyId}/return` – Return a borrowed book copy

#### 👤 **Borrowers**

* `POST /api/borrowers/register` – Register a new borrower
* `GET /api/borrowers/{id}` – Get borrower details by ID

---

### 🧪 Testing the API

You can test all endpoints using:

* **Postman**
* **Curl**
* **Swagger UI**

Swagger also includes validation rules, example request/response payloads, and error codes.

---

Great! Below is a **clear API workflow document** that outlines the recommended **step-by-step usage of your Library Management System**, including when and how to use each API. This document is ideal for adding to your `README.md` or internal API documentation.

---

# -> Library Management API – Workflow Guide

This guide outlines the recommended steps to manage books, book copies, and borrowing functionality using the Library API.

---

### **Workflow Overview**

```text
1. Add a new book (with ISBN, title, and author)
2. Create one or more physical copies for that book
3. Register a borrower
4. Borrow a book copy by borrower
5. Return the borrowed book copy
```

---

### **Step-by-Step API Usage**

---

### 1. Add a New Book

**Endpoint:** `POST /api/books/add`
**Description:** Adds a new book identified by ISBN. ISBN must be unique.
**Request Body:**

```json
{
  "isbn": "978-1-56619-909-4",
  "title": "The Art of Programming",
  "author": "Donald Knuth"
}
```

---

### 2. Add Book Copies (Physical Copies)

**Endpoint:** `POST /api/book-copies/add/{isbn}`
**Description:** Adds a new physical copy of an existing book using ISBN.
**Path Variable:**

* `isbn`: The ISBN of the book.

**Example:**
`POST /api/book-copies/add/978-1-56619-909-4`

---

### 3. Register a Borrower

**Endpoint:** `POST /api/borrowers/register`
**Description:** Creates a borrower account.
**Request Body:**

```json
{
  "name": "Alice Smith",
  "email": "alice@example.com"
}
```

---

### 4. Borrow a Book Copy

**Endpoint:** `POST /api/book-copies/{copyId}/borrow/{borrowerId}`
**Description:** Allows a registered borrower to borrow an available book copy.
**Path Variables:**

* `copyId`: ID of the book copy.
* `borrowerId`: ID of the borrower.

**Example:**
`POST /api/book-copies/5/borrow/3`

---

###  5. Return a Book Copy

**Endpoint:** `POST /api/book-copies/{copyId}/return`
**Description:** Marks the book copy as returned.
**Path Variable:**

* `copyId`: ID of the book copy.

**Example:**
`POST /api/book-copies/5/return`

---

### Optional Queries

#### Get All Books

`GET /api/books`

#### Get All Book Copies by ISBN

`GET /api/book-copies/all/{isbn}`

#### Get Available Book Copies by ISBN

`GET /api/book-copies/available/{isbn}`

#### Get Borrower by ID

`GET /api/borrowers/{id}`

---

### Rules & Validations

* **ISBN is the unique identity for a book**. All book copies with the same ISBN must have the same title and author.
* **Multiple book copies** can be added under the same ISBN.
* **A book copy can only be borrowed if it's not already borrowed**.
* **Borrower must be registered before borrowing**.
* If a book copy is already borrowed, a new borrow attempt will return an error.

---

### 📘 Example Flow

1. **Create a Book** with ISBN `978-1-56619-909-4`
2. **Add 3 Book Copies** using the same ISBN
3. **Register Borrower Alice**
4. **Borrow Copy #1** using Alice's borrower ID
5. **Return Copy #1**

---
