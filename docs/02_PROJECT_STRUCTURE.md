# 02 — Projektstruktur

## Snabbnavigering
- [Översikt](#översikt)
- [Projektträd](#projektträd)
- [Viktiga mappar](#viktiga-mappar)
  - [android-app-1](#android-app-1)
  - [backend](#backend)
  - [infra](#infra)
- [Flyway-regler](#flyway-regler)


## Översikt

Projektet är uppdelat i:
- `android-app-1` → Android applikation
- `backend/` → Java backend (bygger en WAR: `restaurant.war`)
- `infra/` → Docker-stack, Payara-konfig, Flyway-migrations

## Projektträd
    /Project
    +---android-app-1
    +---backend
    +---docs                    (All dokumentation)
    +---infra                   (här lägger ni docker-compose.yml samt .env)
        +---db
        |   +---init            (lägg grundschema här så vi har koll på basen)
        |   \---migrations      (här lägger vi alla databasmigrationer)
        \---payara              (lägg Dockerfile direkt här under)
            +---config          (här ligger post-boot-commands.asadmin)
            +---deployments     (här kommer war-filen att hamna per automatik)
            \---lib             (mysql-connector-j.jar)


## Viktiga mappar

### `android-app-1/`
- här ligger allt rörande appen för Android

### `backend/`
- `pom.xml` → Maven-bygg
- `src/main/...` → kod och webapp (t.ex. `.xhtml`)
- `target/restaurant.war` → byggresultat (skapas vid `package`)

> Vid build kopieras WAR automatiskt till `infra/payara/deployments/`.

### `infra/`
- `docker-compose.yml` → startar MySQL + Flyway + Payara
- `.env` → lokala variabler (ska inte committas)
- `db/migrations/` → Flyway migrations (schema + seed)
- `payara/config/` → `post-boot-commands.asadmin`
- `payara/deployments/` → här ska `restaurant.war` hamna
- `payara/lib/` → t.ex. MySQL JDBC-driver (jar)

## Flyway-regler
- Skapa alltid nya migrations: `V1__...`, `V2__...`, `V3__...`
- Ändra inte gamla migrations som redan committats och körts av någon i gruppen
