FROM postgres:latest

ENV POSTGRES_USER='postgres'
ENV POSTGRES_PASSWORD=''
ENV POSTGRES_DB='reservantor'

COPY database/create_script.sql /docker-entrypoint-initdb.d/