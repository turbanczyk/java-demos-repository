# syntax=docker/dockerfile:1
FROM mysql:8.0.31
COPY mysqlsampledatabase.sql /docker-entrypoint-initdb.d/
COPY mysqlcredentialsdatabase.sql /docker-entrypoint-initdb.d/
#COPY mysqlsampledatabase_test.sql /docker-entrypoint-initdb.d/