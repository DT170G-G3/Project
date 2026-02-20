# Restaurant Project — Översikt

## Dokumentation

- [Installera Docker](docs/01_DOCKER_INSTALL.md)
- [Projektstruktur](docs/02_PROJECT_STRUCTURE.md)
- [Docker-stack: Payara + MySQL + Flyway](docs/03_DOCKER_PAYARA_MYSQL_FLYWAY.md)

Det här projektet består av:
- **Backend (Java, WAR)** som körs på **Payara**
- **MySQL** som databas
- **Flyway** för migrations + seed (schema/data byggs automatiskt)

Målet med dokumentationen är att alla i gruppen ska kunna:
1) Starta Docker-stack
2) Bygga och deploya WAR
3) Öppna appen i webbläsaren
4) Uppdatera DB via Flyway (utan att manuellt skapa tabeller)

---

## Snabbstart (för dig som vill igång direkt)

### 1 Starta stacken
    Gå till `infra/` och kör följande i cmd:

    docker compose up -d --build
    
    behöver man börja om?
    docker compose down -v (rensar allt och börjar om från början)


### 2 Bygg WAR
    Kör följande via cmd i mappen /backend
    ./mvnw clean package (linux)
    .\mvnw.cmd clean package (windows)
    eller kör direkt via IDE Maven och välj clean och sedan package


## 3 Starta appen
    Öppna Docker Desktop -> container välj den ni kör och kör Restart
    Skriv sedan detta i valfri webläsare:
        localhost:8080/restaurant/index.xhtml
    payra admin:
        localhost:4848

