# LUMA API Testing Project

This project automates API testing for the LUMA e-commerce platform using **REST Assured**, **JUnit 5**, **Allure**, **GitHub Actions**, and **Docker**.

> 🔗 *Note: Since the LUMA frontend has no real API backend, this project uses [JSONPlaceholder](https://jsonplaceholder.typicode.com) to simulate CRUD testing.*

---

## Features

- ✅ Automated REST API tests (GET, POST, PUT, DELETE)
- ✅ Test data managed via external JSON files
- ✅ Allure HTML reports (auto-deployed via GitHub Pages)
- ✅ CI/CD with GitHub Actions
- ✅ Dockerized test execution
- ✅ Security and performance testing scope defined

---

## Technologies Used

- Java 21
- REST Assured
- JUnit 5
- Allure Reports
- Maven
- GitHub Actions
- Docker

---

## Project Structure
```text
luma-api-tests/
├── src/
│ ├── main/java/utils/...
│ └── test/java/tests/...
├── resources/testdata/
├── .github/workflows/ci.yml
├── Dockerfile
├── pom.xml
└── README.md
```

---

## How to Run Tests

### Local

```bash
mvn clean test
allure serve target/allure-results
```


### With Docker
```bash
docker build -t luma-api-tests .
docker run --rm luma-api-tests
```

### To export the report locally:
```bash

docker run --rm -v $(pwd)/target:/app/target luma-api-tests
```

---

## GitHub Actions
Tests run automatically on push/pull to main.

Allure HTML reports are deployed at:

> 🔗 https://zanitta-essel-quaicoe.github.io/Luma_RestAssured_API_Test/

---

## Reports
✅ Allure test reports: target/allure-report

✅ Live HTML report: hosted via GitHub Pages

✅ JMeter performance test results: [Not included in this repo]

✅ Security audit report: [Not included in this repo]

---

## Docker Hub
Image: zanitta/luma-api-tests

Run this image to trigger the test suite:
```bash
docker pull zanitta/luma-api-tests
docker run --rm zanitta/luma-api-tests
```
---

## Author
Zanitta Essel Quaicoe

Automation Engineer | QA | DevOps-Ready
