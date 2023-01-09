# syntax=docker/dockerfile:1
FROM mysql:8.0.31
COPY database/mysqlsampledatabase.sql /docker-entrypoint-initdb.d/
COPY database/mysqlsampledatabase_test.sql /docker-entrypoint-initdb.d/