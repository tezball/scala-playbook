#!/usr/bin/env bash

cd "$(dirname "$0")"

echo "Stopping any running services..."
docker compose down --remove-orphans 2>/dev/null

# Kill anything else holding port 9000 (e.g. a previous sbt run)
PID=$(lsof -ti:9000 2>/dev/null || true)
if [ -n "$PID" ]; then
  echo "Killing process on port 9000 (PID $PID)..."
  kill -9 $PID 2>/dev/null || true
  sleep 1
fi

echo "Rebuilding and starting services..."
docker compose up --build -d

echo "Waiting for app to be ready on port 9000..."
MAX_WAIT=120
ELAPSED=0
while [ $ELAPSED -lt $MAX_WAIT ]; do
  STATUS=$(curl -s -o /dev/null -w '%{http_code}' --connect-timeout 2 http://localhost:9000 2>/dev/null || true)
  if [ "$STATUS" = "200" ] || [ "$STATUS" = "303" ]; then
    echo ""
    echo "App is ready!"
    xdg-open http://localhost:9000 2>/dev/null || google-chrome http://localhost:9000 2>/dev/null || echo "Open http://localhost:9000 in your browser"
    exit 0
  fi
  sleep 2
  ELAPSED=$((ELAPSED + 2))
  printf '.'
done

echo ""
echo "App did not start within ${MAX_WAIT}s. Check logs with: docker compose logs app"
exit 1
