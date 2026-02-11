
# 01 — Installera Docker (Docker Desktop)

    https://www.docker.com/products/docker-desktop/
    
    wsl behövs för att kunna köra detta. 
    i valfri cmd skriv: 
        wsl --install
        wsl --update
    man kan behöva trixa så det är wsl version 2
        wsl -l -v


## Installera Docker Desktop
1) Installera Docker Desktop (standardinställningar).
2) Starta Docker Desktop.
3) Se till att Docker är “Running”.

## Verifiera installationen
Öppna en terminal (valfri) och kör:
    docker version
    docker compose version
    docker run --rm hello-world
    Fungerar allt? bra! annars så blir det lite felsökning

## Felsökning
Kör ni mysql lokalt? DÅ kanske den tar upp den port som Docker vill använda. Avsluta programmet som tar.
Enklast kör "netstat -ano | findstr :8080" för att se vad som tar upp porten. sedan "tasklist /FI "PID eq <PID>", nu vet du vilket program du skall stänga av.
Man kan ändra portar i .env filen och via .yml-filen om det behövs om man inte lyckas ändra portar.
Oftast "mysql" som tar upp porten, bara att stänga via terminalen.
Portarna påverkar inte så mycket mer än vart ni skall ansluta emot, så det är fritt att ändra efter behov.

## Grundkommandon du behöver kunna i docker

Lite olika kommandon för docker som kan vara bra att ta med sig:
    docker compose ps               #lista containers och status
    docker compose up -d --build    #bygg och kör upp i bakgrunden
    docker compose down             # stoppar och tar bort containrar
    docker compose down -v          # stoppar men nollställer även databasen, bra när man vill wipa
    docker compose logs -f          #kolla loggar
    docker system df
    docker system prune             #Totalrensning delux