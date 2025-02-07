# ElasticSearch - Spring Boot Demo App 

A simple REST API to demonstrate Elasticsearch integration with Spring Boot, enablling fast and efficient search opeartions.

## Prerequisite
- Java 17
- Elasticsearch
- MySQL

## Setting Up Elasticsearch with Docker

To set up Elasticsearch using Docker, first, pull the official Elasticsearch image:
docker pull docker.elastic.co/elasticsearch/elasticsearch:8.17.1

Next, start the Elasticsearch container:
docker run --name es01 --net elastic -p 9200:9200 -it -m 1GB docker.elastic.co/elasticsearch/elasticsearch:8.17.1

For detailed configuration options and best practices, refer to https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html

## Running the Application :

Make sure elasticsearch and MySQL instances are up and running, and product db exist in MySQL.
Then start the application using 

 `mvn spring-boot:run`

## Curl Requests Example -

### Add a single product -

`curl --location 'localhost:8080/products' \
--header 'Content-Type: application/json' \
--data '{"name":"Celkon C3333","description":"Duis ac nibh.","price":100,"category":"Mobile"}'`

### Add Bulk Products 

`curl --location 'localhost:8080/products/bulk' \
--header 'Content-Type: application/json' \
--data-raw '[{"name":"Oppo A9x","description":"Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem.","price":58,"category":"Adventure|Animation|Drama|Sci-Fi|War"},
{"name":"QMobile Noir Z14","description":"Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat.","price":50,"category":"Action|Comedy|Horror|Thriller"},
{"name":"Infinix Hot 9 Play","description":"Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.","price":6,"category":"Horror|Thriller"},
{"name":"Nokia Asha 203","description":"Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.","price":1,"category":"Action|Adventure"},
{"name":"alcatel OT 525","description":"Nulla facilisi. Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit.","price":62,"category":"Mystery|Thriller"},
{"name":"vivo Y51 (2020, December)","description":"Aliquam sit amet diam in magna bibendum imperdiet.","price":48,"category":"Drama"},
{"name":"Bird S1190","description":"Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat.","price":30,"category":"Documentary"}]'`


### Search Products -

`curl --location 'localhost:8080/products/search?query=samsung'`



