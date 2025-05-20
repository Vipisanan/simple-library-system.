
# API process flow for your Library Management System, including endpoints and example requests for each action:

##  **API Process Flow**

### 1️⃣ Create Two Books with Different ISBNs

**Endpoint:**

```
POST /api/books
```

**Request Body:**

```json
{
  "isbn": "978-0134685991",
  "title": "Effective Java",
  "author": "Joshua Bloch"
}

```

```json
{
  "isbn": "978-1491950357",
  "title": "Clean Architecture",
  "author": "Robert C. Martin"
}

```

---

### 2️⃣ Add Book Copy (for a specific ISBN)

**Endpoint:**

```
POST /api/books/{isbn}/copies
```

**Example:**

```
POST /api/books/978-0134685991/copies
```

*Response:*

```json
{
  "id": 1,
  "isbn": "978-0134685991",
  "isBorrowed": false
}
```

---

### 3️⃣ Register a Borrower

**Endpoint:**

```
POST /api/borrowers
```

**Request Body:**

```json
{
  "name": "Alice Johnson",
  "email": "alice@example.com"
}

```

*Response:*

```json
{
  "id": 1,
  "name": "Alice Johnson",
  "email": "alice@example.com"
}

```

---

### 4️⃣ Borrow a Book Copy

**Endpoint:**

```
POST /api/books/copies/{copyId}/borrow/{borrowerId}
```

**Example:**

```
POST /api/books/copies/1/borrow/1
```

*Response:*

```json
{
  "id": 1,
  "book": {
    "isbn": "978-0134685991",
    "title": "Effective Java",
    "author": "Joshua Bloch"
  },
  "borrower": {
    "id": 1,
    "name": "Alice Johnson",
    "email": "alice@example.com"
  },
  "isBorrowed": true
}

```

---

### 5️⃣ Return the Borrowed Book

**Endpoint:**

```
POST /api/books/copies/{copyId}/return
```

**Example:**

```
POST /api/books/copies/1/return
```

*Response:*

```json
{
  "id": 1,
  "book": {
    "isbn": "978-0134685991",
    "title": "Effective Java",
    "author": "Joshua Bloch"
  },
  "borrower": null,
  "isBorrowed": false
}
```