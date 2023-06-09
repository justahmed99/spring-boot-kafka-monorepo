# Spring Boot Kafka Monorepo

## About This Project

This project demonstrates the use of Apache Kafka message broker to create a connection between microservices.

Here is the architecture of this project :
<p align="center">
  <img src="./image/arch-diagram.png">
</p>

In this example project we will create a service that collects news data from a news collection API.

This project is consisted of :
- Two Spring Boot microservices
  - `user-api` (act as REST API service)
  - `worker` (act as worker that collects data from news API)
- Zookeeper (to run Apache kafka)
- Apache Kafka message broker
- Redis (to save cached date)
- New API (we use [Mediastack](https://mediastack.com/documentation) for this project)
- Docker (to run Zookeeper, Apache Kafka, and Redis)

## Project Setup

You have to do several steps to run or use this project in your machine.

**First**, you must install Zookeeper, Kafka, and Redis as these things are necessary to run this project.

You can simply install all of these things by executing this command :
```
docker-compose up -d
```
To check if the installation is successful, you can check that with this command :
```
docker ps
```
If you can see Zookeeper, Kafka, and Redis containers are running, they are successfully installed (like the example below)
```
CONTAINER ID   IMAGE                              COMMAND                  CREATED         STATUS        PORTS                                         NAMES
e5e5245248ed   confluentinc/cp-kafka:latest       "/etc/confluent/dock…"   47 hours ago    Up 11 hours   9092/tcp, 0.0.0.0:29092->29092/tcp            monorepo-kafka-1
bf3a4892effa   confluentinc/cp-zookeeper:latest   "/etc/confluent/dock…"   47 hours ago    Up 11 hours   2888/tcp, 3888/tcp, 0.0.0.0:22181->2181/tcp   monorepo-zookeeper-1
3b0f8606ad00   redis:latest                       "docker-entrypoint.s…"   47 hours ago    Up 12 hours   0.0.0.0:6379->6379/tcp                        monorepo-redis-1
```

You can do things like changing Redis password, giving a password for Kafka, changing topics name, etc. by editing [docker-compose.yml](./docker-compose.yml) file.

**Second**, check this two files :
- [application.yml](./user-api/src/main/resources/application.yml) (inside `user-api` project)
- [application.yml](./worker/src/main/resources/application.yml) (inside `worker` project)

make sure the Redis password or any custom setup related with Kafka are similar with the setup inside [docker-compose.yml](./docker-compose.yml) file.

Third, register an account to [Mediastack](https://mediastack.com/documentation) and get your API key. Put the key inside [application.yml](./worker/src/main/resources/application.yml) (`worker` project)
```yaml
mediastack:
  uri: http://api.mediastack.com/v1/news
  api-key: <your-api-key>
  countries: us
  limit: 25
```

## How To Run The Project

**First**, make sure Redis, Zookeeper, and Apache Kafka are running.

**Second**, align all setups in both application.yml files with the setup in [docker-compose.yml](./docker-compose.yml) file.

**Third**, run all projects (`user-api` and `worker`) you can use `./gradlew bootRun` command on each projects or simply use IDE (IntellIJ or Eclipse).

## List Of API

We only have one API inside this project :
- (GET) `http://localhost:8080/message?date=<date>`, date = `yyyy-MM-dd`