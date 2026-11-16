# Han-Tech Ltd Platform (hantech)

A highly scalable, microservices-based platform designed for Han-Tech Ltd to deliver community IT services.

## Architecture
- **Apps**: Mobile (Flet) and Admin Dashboard.
- **Services**: Modular FastAPI microservices (Auth, Catalog, Tickets, Feedback).
- **Template**: Use `services/_template-service` to bootstrap any new feature.

## Quick Start
1. Clone the repo
2. Run `cp .env.example .env` and fill in your secrets
3. Run `docker-compose up --build`
