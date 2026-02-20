#!/usr/bin/env bash
set -e

cd "$(dirname "$0")"

echo "Stopping any running services..."
docker compose down --remove-orphans

echo "Rebuilding and starting services..."
docker compose up --build -d

echo "Waiting for app to be ready on port 9000..."
until curl -s -o /dev/null -w '%{http_code}' http://localhost:9000 | grep -qE '200|303'; do
  sleep 2
  printf '.'
done
echo ""
echo "App is ready!"

xdg-open http://localhost:9000 2>/dev/null || google-chrome http://localhost:9000 2>/dev/null || echo "Open http://localhost:9000 in your browser"
