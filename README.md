# UnearthedTruths API

UnearthedTruths is a Java Spring Boot RESTful API that catalogs archaeological discoveries aligned with Biblical history. The API provides secure management functionality (JWT-based authentication) and public access to read discovery records.

---

## 📚 Table of Contents

- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Security](#-security)
- [API Endpoints](#-api-endpoints)
- [Environment Configuration](#-environment-configuration)
- [Docker Deployment](#-docker-deployment)
- [CI/CD with Render](#-cicd-with-render)
- [Render Cold Start Notes](#-render-cold-start-notes)
- [Testing](#-testing)
- [License](#-license)

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot 3.5
  - Web
  - Security
  - JPA (Hibernate)
- PostgreSQL
- JWT (`jjwt`)
- Lombok
- Docker
- Maven

---

## 📂 Project Structure

```
src/
├── java/com/example/unearthedtruths/
│   ├── controller/
│   ├── model/
│   ├── repo/
│   ├── service/
│   ├── security/
│   └── config/
└── resources/
    ├── application.properties
    └── application-prod.properties
```

---

## 🔐 Security

- JWT-based login and authentication.
- Custom `UserDetailsService` and `UserPrincipal`.
- Role-based access for `ADMIN` and `SUPERADMIN`.
- CORS is globally configured.

Token must be added in HTTP headers:
```
Authorization: Bearer <your_token>
```

---

## 📡 API Endpoints

### Public

| Method | Endpoint                  | Description                  |
|--------|---------------------------|------------------------------|
| GET    | `/api/discoveries`        | List all discoveries         |
| GET    | `/api/discoveries/{id}`   | Get a discovery by ID        |

### Management (JWT Protected)

| Method | Endpoint                   | Description                 |
|--------|----------------------------|-----------------------------|
| POST   | `/api/management`          | Register new admin user     |
| POST   | `/api/management/login`    | Login with credentials      |
| GET    | `/api/management`          | List all admin users        |

---

## ⚙️ Environment Configuration

Define the following variables in your `.env` file or as system environment variables:

```dotenv
DB_URL=jdbc:postgresql://<host>:<port>/<db>
DB_USERNAME=yourusername
DB_PASSWORD=yourpassword
JWT_SECRET=your_jwt_secret
```

---

## 🐳 Docker Deployment

Dockerfile is provided.

### Build & Run

```bash
# Build JAR
./mvnw clean package -DskipTests

# Build Docker image
docker build -t unearthed-truths .

# Run container
docker run -p 8080:8080 \
  -e DB_URL=... \
  -e DB_USERNAME=... \
  -e DB_PASSWORD=... \
  -e JWT_SECRET=... \
  unearthed-truths
```

---

## 🚀 CI/CD with Render

Render can deploy your API directly from GitHub.

### Render Configuration

1. Create a new Web Service:
   - Environment: Docker
   - Start Command: leave blank (Dockerfile will handle it)
2. Add environment variables:
   - `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET`

---

## 🧊 Render Cold Start Notes

Render’s free tier puts your API to sleep after inactivity, leading to 1–2 minute delays on first access.

### Suggested workaround:

- Show a "waking up" animation on your frontend.
- Consider upgrading for always-on deployment.
- Ping the server every 5 minutes with a free cron job (e.g., [UptimeRobot](https://uptimerobot.com/)).

---

## 🧪 Testing

Run all tests:

```bash
./mvnw test
```

Test framework includes:
- `spring-boot-starter-test`
- `spring-security-test`

---

## 📄 License

This project is for personal and educational use only. All rights reserved.
