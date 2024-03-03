FROM postgres:latest
ENV POSTGRES_USER root
ENV POSTGRES_PASSWORD root123
ENV POSTGRES_DB secrets-db
COPY init.sql /docker-entrypoint-initdb.d/

# docker build -t secrets-db ./
# docker run -d --name secrets-db-container -p 5433:5432 secrets-db