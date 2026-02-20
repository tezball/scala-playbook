# Scala Playbook

Learn Scala concepts through interactive e-commerce scenarios. Built with [Play Framework](https://www.playframework.com/) 3, Scala 3, PostgreSQL (Slick), and Kafka.

Each page is a self-contained demo with a form to interact with, visible results, and a concept banner explaining the Scala features on display.

## Quick Start

```sh
./start.sh
```

This single script handles everything:

1. Stops any running containers and **wipes data volumes** for a fresh DB (`docker compose down --remove-orphans --volumes`)
2. Kills any stray process on port 9000 (e.g. a previous `sbt run`)
3. Rebuilds the app image and starts all services (`docker compose up --build -d`)
4. Polls `localhost:9000` until the app responds (times out after 180s)
5. Opens your browser to [http://localhost:9000](http://localhost:9000)

### Prerequisites

- [Docker](https://docs.docker.com/get-docker/) and Docker Compose

That's it. The Docker build handles JDK, sbt, and all dependencies.

### Manual Run (without Docker)

If you prefer running locally, you'll need JDK 11+, sbt, PostgreSQL, and Kafka:

```sh
# Start Postgres and Kafka yourself, then:
sbt run
```

## What's Inside

### Existing Modules

| Module | Route | What it demonstrates |
|--------|-------|----------------------|
| **Users** | `/users` | Play Forms, Slick ORM, Kafka Producer/Consumer |
| **Orders** | `/orders` | Play Forms, Slick ORM, Kafka Producer/Consumer |

### Core Concepts

| Module | Route | Scala Concepts |
|--------|-------|----------------|
| **Products** | `/products` | Pattern Matching, Sealed Traits, Enums, Case Classes |
| **Cart** | `/cart` | Collection Ops: `map`, `filter`, `foldLeft`, `groupBy`, `sortBy`, `collect` |
| **Coupons** | `/coupons` | Error Handling: `Option`, `Either`, `Try`, for-comprehensions |
| **Notifications** | `/notifications` | Function Composition: `andThen`, `compose`, `pipe`, PartialFunction |
| **Analytics** | `/analytics` | Concurrency: `Future`, parallel composition, `recover` |

### Advanced Concepts

| Module | Route | Scala Concepts |
|--------|-------|----------------|
| **Reviews** | `/reviews` | Traits, Mixins, Type Classes (`Ordering`, custom `Show`), Givens |
| **Workflow** | `/workflow` | ADTs, State Machines, Exhaustive Matching, `copy` |
| **Ledger** | `/ledger` | Event Sourcing, `foldLeft`, `scanLeft`, Immutability |

## Project Structure

```
app/
├── controllers/         # HomeController (landing page index)
├── kafka/               # Shared Kafka producer service
├── users/               # User module (model, repo, controller, Kafka events)
├── orders/              # Order module (model, repo, controller, Kafka events)
├── products/            # Products: pattern matching + enums + pricing engine
├── cart/                # Cart: collection operations (in-memory, no DB)
├── coupons/             # Coupons: Try/Option/Either validation pipeline
├── notifications/       # Notifications: function composition pipeline (no DB)
├── analytics/           # Analytics: Future-based parallel dashboard
├── reviews/             # Reviews: traits, mixins, type classes
├── workflow/            # Workflow: ADT state machine
├── ledger/              # Ledger: event sourcing with foldLeft/scanLeft
└── views/               # Twirl templates (shared layout with global nav)
conf/
├── application.conf     # Play + Slick + Kafka configuration
├── routes               # All URL routes
└── evolutions/default/  # DB migrations (1.sql through 7.sql)
```

## Services

The `docker-compose.yml` runs three services:

| Service | Port | Description |
|---------|------|-------------|
| **app** | 9000 | Play Framework application |
| **db** | 5432 | PostgreSQL 16 (auto-applies evolutions on startup) |
| **kafka** | 9092 | Apache Kafka 3.9 (KRaft mode, no Zookeeper) |

---

Built with [Claude Code](https://claude.ai/claude-code) and [Gemini CLI](https://github.com/google-gemini/gemini-cli).
