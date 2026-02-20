#!/bin/bash
set -e

host="${DB_HOST:-db}"
port="${DB_PORT:-5432}"

echo "Waiting for PostgreSQL at $host:$port..."
until pg_isready -h "$host" -p "$port" -q 2>/dev/null || nc -z "$host" "$port" 2>/dev/null; do
  sleep 1
done
echo "PostgreSQL is ready."

kafka_host="${KAFKA_HOST:-kafka}"
kafka_port="${KAFKA_PORT:-9094}"

echo "Waiting for Kafka at $kafka_host:$kafka_port..."
until nc -z "$kafka_host" "$kafka_port" 2>/dev/null; do
  sleep 1
done
echo "Kafka is ready."

exec /app/bin/scala-playbook "$@"
