# Gå till backend-mappen (utgår från att skriptet ligger i /infra)
Set-Location "$PSScriptRoot\..\backend"

Write-Host "🔨 Bygger backend..." -ForegroundColor Cyan
.\mvnw.cmd clean package -DskipTests

# Verifiera att filen skapades
$warPath = ".\target\restaurant.war"
if (Test-Path $warPath) {
    # Kopiera till infra/payara/deployments
    Copy-Item $warPath "$PSScriptRoot\payara\deployments\restaurant.war" -Force
    Write-Host "✅ Deployed restaurant.war till infra/payara/deployments/" -ForegroundColor Green
} else {
    Write-Host "❌ Hittade inte restaurant.war! Kontrollera <finalName> i pom.xml" -ForegroundColor Red
}

Set-Location $PSScriptRoot