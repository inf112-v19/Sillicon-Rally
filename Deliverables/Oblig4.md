## Obligatorisk Øvelse 4

## Task 1: Project and project structure

- Testrolle: Kristian.
  Hovedansvar for testing av roller, kan delegere oppgaver, og finne en strategi for testing. Vi har blitt enig om at testing helst skal komme i form av Junit tester, men det at programmet kjører og det at player tokens oppdaterers utføres manuelt.

dvs:

- Vi kjører spillet og utfører handlinger som skal gjøre at ting i GUI-en forandrer seg, som f.eks at man mister liv.
- Vi tester forskjellige måter å få spillet til å IKKE fungere som vi skal, slik at vi kan skvise ut forksjellige bugs.

Testing av player tokens blir gjort ved å flytte spiller til en laser-tile, og rotere spiller mens man følger med på System println og tokens representert på skjerm.

- Har valgene dere har tatt vært gode?

Vi er generelt fornøyd med valgene vi har tatt, spessielt det at vi startet med parprogrammering, og satte opp oppgavene slik at man har en kortere deadline enn den man et tror man trenger. Korte deadlines gjør terskelen for å spørre om hjelp, dersom det skulle trengs, lavere.

Det er enkelte valg i koden vår som vi nå ser ikke er helt ideelt. For eksempel gjør logikken vår (flytting av spillere og håndtering av kollisjoner) at den visuelle koden enten stopper helt, eller oppdaterer seg så kjapt at det ikke er synlig for øyet. Resultatet er at brikkene teleporterer fra ett sted til et annet, heller enn å bevege seg dit i fornuftige intervaller.
Vi har enda ikke klart å løse dette problemet, og vi mistenker at det har med grunnleggende oppsett av koden å gjøre.

Vi ser også litt resultatet av for kjapp sprinting, der vi ikke har tatt tid til å refaktorere og heller fokusert på å implementere nye løsninger. Vi har vært flinke til å skrive dekkende tester, men vi kunne godt ha brukt mer tid på å refaktorere. Implementering av nye ting tar nå lenger tid enn det burde på grunn av teknisk gjeld. Selv om det selvfølgelig er frustrerende, så er det også ekstremt lærerikt, og vi forstår mer enn noe gang poenget med å refaktorere alt vi skriver.

- Gruppedynamikken er bra.
  Vi kommer godt overns og bruker hverandres styrker godt.

* Hvordan fungerer kommunikasjonen for dere?
  Mesteparten at gruppen leser på samme lesesal, selvom vi kanskje jobber med andre fag så er vi alltid villig til å svare på spørsmål eller jobbe med problemer relatert til Robo Rally prosjektet.
  Mesteparten av kommunikasjonen foregår ansikt til ansikt siden vi møtes så ofte på lesesalen. Ellers går kommunikasjonen over facebook, der vi avtaler møter og slikt.

* Et kort retroperspektiv.
  Vi er fornøyd med å ha innført parprogrammering. Parprogrammering gjør det litt lettere å utføre oppgaver, samtidig som man får litt bedre innsikt i helheten av koden ettersom vi har satt sammen par utifra de som hadde minst felleskunnskap.
  Et av problemene vi har hatt tidligere er at det er enkelte på gruppen som her større "eierskap" til koden, noe som gjorde at ikke alle hadde like forutsetninger til å implentere nye funksjoner eller fikse bugs. Nå som vi har hatt fokus på å jobbe i par, har dett blitt langt bedre.

Vi har også blitt flinkere til å bruke Git, og bruker langt mindre tid på å fikse små problemer som har med versjonskontroll å gjøre.

Ettersom det har vært litt kort tidsfrist fra den 4. obligatoriske oppgave ble opprettet til den skulle leveres inn, har vi ikke har ordentlig prioritert å sette opp arbeidsmetoder på grunnlag av et retropersketiv. Vi prioriterte bare å få gjort flest mulige av arbeidskravene.

Forebedringspunkter:

- Refaktorere.
- Bli enda flinkere til å parprogrammere og få alle skikkelige komfortable med koden vår.

* Referat fra møter ligger under mappen SiliconRally/Deliverables/Møtereferater som pdf filer.

Oversikt over foredeling og status av oppgaver ligger på [trello](https://trello.com/b/zOgCmmNW/silicon-rally)

## Deloppgave 2: Krav

Prioritet av arbeidsoppgaver i følgende reggefølge, en del av disse har ikke blitt implentert i det endelige spillet enda.

1. Ability to play a complete round
2. Lasers on the board
3. Holes on the board
4. Acting assembly line on the board that moves the robots
5. Functioning gyros
6. Assembly line that runs at double speed
7. Options cards
8. Power Down
9. Injury mechanisms (player gets fewer cards in case of injury)
10. Win game by visiting the last flag.
11. game mechanisms to shoot other players within reach
12. game over after 3 lives lost
13. place flags even before the game starts
14. play against AI
15. Assemble different boards to larger spills
16. Multiplayer over LAN or Internet

## Deloppgave 3: Kode

For å kjøre programmet må man sette working directory til core/assets, og så kjøre main-metoden i Main-klassen som ligger i packagen «RoboGame». For å kjøre alle testene i Intellij:

Gå til run -> edit configurations
Lag en ny junit tests configuration
Gi den et valgfritt navn
Velg «Test kind -> All in package»
Velg «In whole project»
Set working directory til «core/assets»
apply
Main klassen ligger i src -> main -> java -> inf112.skeleton.app -> game

Testene:
Testene ligger under src -> test -> java -> inf112.skeleton.app
Klikk på pakken inf112.skeleton.app under tests og enten trykk ctrl+shift+F10, eller høyreklikk og run.
