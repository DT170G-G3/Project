#!/usr/bin/env bash
set -e

# Hitta roten (antar att skriptet ligger i /infra)
SCRIPTPATH="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPTPATH/../backend"

echo "🔨 Bygger backend..."
./mvnw clean package -DskipTests

# Kopiera till infra/payara/deployments
cp -f target/restaurant.war "$SCRIPTPATH/payara/deployments/restaurant.war"

echo "✅ Deployed restaurant.war till infra/payara/deployments/"