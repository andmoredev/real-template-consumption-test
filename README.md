# real test

## Table of contents
1. [Resource definitions with Terraform](#resource-definitions-with-terraform)
1. [Run locally](#-run-locally)
1. [Run in Containerd](#-run-in-containerd)

## Resource definitions with Terraform
All the resources required for this service to run in the cloud are defined using infrastructure as code (IaC) with Terraform. This allows new environments to be easily created with no manual intervention. 

There are three different Terraform modules that we are deploying to make everything work:

1. Backend - these are the resources needed by Terraform to maintain the deployed resource state as well as to avoid resource deployment collisions by preventing concurrent deployments of the same resources with state locks. The Terraform backend resources are deployed manually. [More information here](./docs/README-Terraform.md#terraform-backend-resources)
1. Core - This currently only contains the IAM role and permissions for the GitHub workflows to be able to deploy the resources for the application. To keep following the principle of least privilege (POLP), the permissions will be updated as needed based on the updates made to the Terraform. [Go here](./docs/README-Terraform.md#terraform-deployment-core-resources) for more information on how to deploy these resources.
1. Workflow - These are all the resources for the actual microservice to run. This includes things like ECS resources, DynamoDB tables and anything that will allow correct processing for the systm.

A microservice built with **Quarkus 3.21**, **Java 21**, and **gRPC**, exposing both REST and gRPC APIs — ready to run locally or in container.

## 🚀 Run Locally

### 📋 Prerequisites

- Java 21+
- Maven 3.8+

### 1. Build the app

```bash
./mvnw clean package -DskipTests
```

### 2. Run in dev mode

```bash
./mvnw quarkus:dev
```

- REST: [http://localhost:8080](http://localhost:8080)
- gRPC: `localhost:9000`


## 🧪 Test the gRPC API

Once the app is running:

### List available gRPC services

```bash
grpcurl -plaintext localhost:9000 list
```

### Call `SayHello` endpoint

```bash
grpcurl -plaintext -d '{"name":"CUBIC"}' localhost:9000 HelloService/SayHello
```

Expected result:

```json
{
  "message": "Hello, CUBIC"
}
```

## 🐳 Run in Containerd

### 📋 Prerequisites

- Java 21+
- Maven 3.8+
- Lima (MacOS only)
- Containerd

### 1. Build the container image

```bash
./scripts/build-image.sh
```

### 2. Run the container

```bash
./scripts/run-container.sh
```

- REST: [http://localhost:8080](http://localhost:8080)
- gRPC: `localhost:9000`


## 🧪 Test gRPC Inside Container

While the container is running:

```bash
grpcurl -plaintext localhost:9000 list
```

Then test the HelloService:

```bash
grpcurl -plaintext -d '{"name":"CUBIC"}' localhost:9000 HelloService/SayHello
```

## 🛠 Useful Commands

| Task                        | Command                                                       |
|-----------------------------|---------------------------------------------------------------|
| Build JAR                   | `./mvnw clean package -DskipTests`                            |
| Run locally in dev mode     | `./mvnw quarkus:dev`                                          |
| Build Containerd image      | `lima nerdctl build -t accounts-service .`                    |
| Run Containerd container    | `lima nerdctl run -p 9000:9000 -p 8080:8080 accounts-service` |
| Run tests                   | `./mvnw test`                                                 |

