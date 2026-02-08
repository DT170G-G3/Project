## Base.xhtml ##
Base.xhtml finns i mappen `src/main/webapp/WEB-INF/templates/`.
Denna fil är sajtens skelett. Den innehåller <ui:insert name="content" /> vilket fungerar som en tom låda där undersidorna placerar sitt innehåll.

## CSS ##
Plats: `src/main/webapp/resources/css/`.
Filerna ska namnges enligt följande konvention:
`<komponent>-<beskrivning>.css`
Exempelvis: `header-main.css`, `footer-styles.css`, `dashboard-layout.css` osv.
Varje css-fil ska innehålla kommentarer högst upp som beskriver dess syfte och användning.

I base.xhtml inkluderas sedan css-filerna med hjälp av `<h:outputStylesheet>`-taggen. Kopiera en befinlig rad och ändra sedan till korrekt filnamn.
OBS! Det är viktigt att man placerar dem i rätt ordning. Rooten överst osv osv. Så skapar ni nya element lägg dem längst ned.

## Nya undersidor .xhtml ##
Plats: `src/main/webapp/`.
Filerna ska namnges enligt följande konvention:
`<sidnamn>.xhtml`
Exempelvis: `dashboard.xhtml`, `profile.xhtml`, `settings.xhtml` osv.
Varje xhtml-fil ska innehålla kommentarer högst upp som beskriver dess syfte och användning.

Under varje xhtml-fil ska det finnas en `<ui:composition>`-tagg som refererar till `base.xhtml` som mall. Inom denna tagg definieras sedan innehållet för den specifika sidan.
Varje ui:define-tagg ska ha ett unikt namn som matchar det som används i base.xhtml, exempelvis "content" för att fylla innehållslådan. Namnet content kommer att ladda nya sidor  mellan header och footer, så namnge enliget detta!
define name="title" används för att sätta sidans titel i webbläsarens flik.

exempel:
```xml
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
                xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
                template="/WEB-INF/templates/base.xhtml">

    <ui:define name="title">'Á la carte'</ui:define>

    <ui:define name="content">
        <h1>Á la carte meny</h1>
        <p class="muted">Här lägger vi ut menyn</p>
    </ui:define>

</ui:composition>
```
## Bilder ##
Plats: `src/main/webapp/resources/images/`.
länkning av bilder kan göra på två sätt:
1. Via xhtml: `<h:graphicImage name="images/filnamn.jpg" />`
2. Via css: `url("#{resource['images:filnamn.jpg']}")`

## Länkning ##
För att länka mellan olika undersidor i xhtml-filerna används `<h:link>`-taggen. Exempelvis:
```xml
<h:link value="Länktexten här" outcome="sidnamn" />
```
länktexten = det som visas för användaren
sidnamn = namnet på den xhtml-fil som ska länkas till. Filändelse behövs ej.

### Externa länkar ###
Fungerar nästan som vanligt.
```xml
<h:outputLink value="https://www.example.com" target="_blank">
    Besök Example.com, man kan även lägga bilder eller ikoner här.
</h:outputLink>
```

