FROM docker.io/library/postgres:latest

ENV POSTGRES_USER='postgres'
ENV POSTGRES_PASSWORD='password'
ENV POSTGRES_DB='db'

COPY database/create_script.sql /docker-entrypoint-initdb.d/