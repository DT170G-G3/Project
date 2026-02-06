# 03 — Docker stack (Payara + MySQL + Flyway)

## What-if?
Allt bör vara på plats via git, men om något inte stämmer så har ni det ni behöver copy-pasta längre ned i denna fil.

## Vad som startas
- `mysql` → databasen
- `flyway` → kör migrations/seed och avslutar
- `payara` → appserver som deployar `restaurant.war`

## Hur Payara och MySQL “pratar”
Docker Compose skapar ett internt nätverk och DNS.
Payara ansluter till MySQL via hostname `mysql:3306`.

## Post-boot (JDBC)
Payara kör `post-boot-commands.asadmin` vid start för att skapa:
- JDBC Connection Pool: `AppPool`
- JDBC Resource: `jdbc/local_sql`

Kontroll i Payara Admin:
- Resources → JDBC → Connection Pools → `AppPool`
- Resources → JDBC → JDBC Resources → `jdbc/local_sql`

## Deploy av WAR
`infra/payara/deployments/restaurant.war` mountas in i containern till:
`/opt/payara/deployments/restaurant.war`

Payara lägger automatiskt till deploy-kommandon vid start.

### När du byggt om WAR
1) Bygg backend (`mvnw ... package`)
2) Restart `restaurant-payara` i Docker Desktop

## Flyway (migrations)
Migrations ligger i:
`infra/db/migrations/`

Namngivning (OBS! Gör aldrig om en migration, gör en ny och kör istället!):
- `V1__baseline.sql`
- `V2__seed_basic_data.sql`
- `V3__add_something.sql`

Flyway skapar en tabell:
- `flyway_schema_history` (spårar vad som körts)

Kontroll i MySQL:
Starta Docker Desktop->Containers->restaurant-local->Gå in på mysql->Exec

mysql -u root -p
(Skriv ert root password som ni valt i .env)
SHOW DATABASES;
USE restaurant;
SELECT * FROM flyway_schema_history;
SHOW TABLES;


#### COPY/PASTE vid behov

## MySQL
det som ligger i .env.example bör kopieras till en egen fil som heter .env enligt projektstrukturen i fil 02.
--------
    COMPOSE_PROJECT_NAME=restaurant-local

    PAYARA_TAG=7.2026.1
    MYSQL_TAG=8.0

    #MySQL
    MYSQL_ROOT_PASSWORD=rootpass123

    #DB credentials
    MYSQL_DATABASE=restaurant
    MYSQL_USER=user
    MYSQL_PASSWORD=password123

    #portar
    MYSQL_PORT=3306
    PAYARA_HTTP_PORT=8080
    PAYARA_ADMIN_PORT=4848

## Docker-compose.yml
Det bör finnas en fil under /infra som heter docker-compose.yml om inte skapa den manuellt och kopiera in nedan.
---------
    name: ${COMPOSE_PROJECT_NAME:-restaurant-local}

    services:
    mysql:
        image: mysql:${MYSQL_TAG}
        container_name: restaurant-mysql
        restart: unless-stopped
        environment:
        MYSQL_ROOT_PASSWORD: ${MYSQL_ROOT_PASSWORD}
        MYSQL_DATABASE: ${MYSQL_DATABASE}
        MYSQL_USER: ${MYSQL_USER}
        MYSQL_PASSWORD: ${MYSQL_PASSWORD}
        ports:
        - "${MYSQL_PORT}:3306"
        volumes:
        - mysql_data:/var/lib/mysql
        healthcheck:
        test: [ "CMD-SHELL", "mysqladmin ping -h localhost -u root -p$$MYSQL_ROOT_PASSWORD --silent" ]
        interval: 5s
        timeout: 3s
        retries: 30

    flyway:
        image: flyway/flyway:10
        container_name: restaurant-flyway
        depends_on:
        mysql:
            condition: service_healthy
        command:
        - -url=jdbc:mysql://mysql:3306/${MYSQL_DATABASE}?useSSL=false&allowPublicKeyRetrieval=true
        - -user=${MYSQL_USER}
        - -password=${MYSQL_PASSWORD}
        - -connectRetries=60
        - migrate
        volumes:
        - ./db/migrations:/flyway/sql:ro
        restart: "no"

    payara:
        build:
        context: ./payara
        args:
            PAYARA_TAG: ${PAYARA_TAG}
        image: restaurant-payara:${PAYARA_TAG}
        container_name: restaurant-payara
        restart: unless-stopped
        depends_on:
        mysql:
            condition: service_healthy
        flyway:
            condition: service_completed_successfully
        environment:
        # Dessa används av post-boot-commands.asadmin via ${ENV=...}
        DB_HOST: mysql
        DB_PORT: 3306
        DB_NAME: ${MYSQL_DATABASE}
        DB_USER: ${MYSQL_USER}
        DB_PASSWORD: ${MYSQL_PASSWORD}
        ports:
        - "${PAYARA_HTTP_PORT}:8080"
        - "${PAYARA_ADMIN_PORT}:4848"
        volumes:
        # autodeploy härifrån vid start
        - ./payara/deployments:/opt/payara/deployments

    volumes:
    mysql_data:


## Dockerfile
Det bör finnas en Dockerfile som ligger direkt under /payara, om inte skapa den filen med namnet "Dockerfile" manuellt och kopiera in nedan.
---------
    ARG PAYARA_TAG=7.2026.1
    FROM payara/server-full:${PAYARA_TAG}

    # 1) Lägg JDBC-driver i domain-dir/lib så den finns på classpath vid boot
    # Payara rekommenderar domain-dir/lib för JDBC-driver. :contentReference[oaicite:5]{index=5}
    COPY --chown=payara:payara lib/mysql-connector-j.jar \
        /opt/payara/appserver/glassfish/domains/domain1/lib/mysql-connector-j.jar

    # 2) Lägg postboot-fil (skrivbar, så Payara kan append:a deploy-kommandon)
    COPY --chown=payara:payara config/post-boot-commands.asadmin \
        /opt/payara/config/post-boot-commands.asadmin

## payara
Det bör finna en fil under /config som heter post-boot-commands.asadmin om inte skapa den och kopiera in nedan manuellt.
----------
    create-jdbc-connection-pool --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource --restype javax.sql.DataSource --ping true --property user=${ENV=DB_USER}:password=${ENV=DB_PASSWORD}:serverName=${ENV=DB_HOST}:portNumber=${ENV=DB_PORT}:databaseName=${ENV=DB_NAME}:useSSL=false:allowPublicKeyRetrieval=true AppPool
    create-jdbc-resource --connectionpoolid AppPool jdbc/local_sql
    ping-connection-pool AppPool


## pom.xml
För att war-filen skall hamna korrekt så sköter IDE'n det själ. detta bör finnas i er /backend/pom.xml
---------
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.DT170G.G3</groupId>
    <artifactId>backend</artifactId>
    <version>1.0-SNAPSHOT</version>
    <name>backend</name>
    <packaging>war</packaging>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.target>21</maven.compiler.target>
        <maven.compiler.source>21</maven.compiler.source>
        <junit.version>5.13.2</junit.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
            <version>3.1.1</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>jakarta.enterprise</groupId>
            <artifactId>jakarta.enterprise.cdi-api</artifactId>
            <version>4.1.0</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>jakarta.json.bind</groupId>
            <artifactId>jakarta.json.bind-api</artifactId>
            <version>3.0.1</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>jakarta.persistence</groupId>
            <artifactId>jakarta.persistence-api</artifactId>
            <version>3.2.0</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>jakarta.platform</groupId>
            <artifactId>jakarta.jakartaee-web-api</artifactId>
            <version>11.0.0</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>jakarta.ws.rs</groupId>
            <artifactId>jakarta.ws.rs-api</artifactId>
            <version>4.0.0</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>jakarta.servlet</groupId>
            <artifactId>jakarta.servlet-api</artifactId>
            <version>6.1.0</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>


    <build>
        <finalName>restaurant</finalName>

        <plugins>
            <!-- Bygger WAR -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>3.4.0</version>
            </plugin>

            <!-- Kopierar WAR till infra/payara/deployments efter package -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-antrun-plugin</artifactId>
                <version>3.1.0</version>
                <executions>
                    <execution>
                        <id>copy-war-to-payara-deployments</id>
                        <phase>package</phase>
                        <goals>
                            <goal>run</goal>
                        </goals>
                        <configuration>
                            <target>
                                <mkdir dir="${project.basedir}/../infra/payara/deployments"/>
                                <copy
                                        file="${project.build.directory}/${project.build.finalName}.war"
                                        tofile="${project.basedir}/../infra/payara/deployments/${project.build.finalName}.war"
                                        overwrite="true"/>
                            </target>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>

