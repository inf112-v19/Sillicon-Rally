## Obligatorisk Øvelse 4


## Task 1: Project and project structure
  * Testrolle: Kristian. 
  Hovedansvar for testing av roller, kan delegere oppgaver, og finne en strategi for testing. Vi har blitt enig om at testing helst skal komme i form av Junit tester, men det at programmet kjører og det at player tokens oppdaterers utføres manuelt.
  Testing av player tokens blir gjort ved å flytte spiller til en laser-tile, og rotere spiller mens man følger med på System println og tokens representert på skjerm.
  * Har valgene dere har tatt vært gode?
  Vi er generelt fornøyd med valgene vi har tatt, espessielt det at vu startet med par programmering, og satte opp oppgave slik at man har en kortere deadline enn den man et tror man trenger. Korte deadlines gjør terskelen for å spørre om hjelp, dersom det skulle trengs, lavere.
  * Gruppedynamikken er bra.
  * Hvordan fungerer kommunikasjonen for dere?
  Mesteparten at gruppen leser på samme lesesal, selvom vi kanskje jobber med andre fag så er vi alltid villig til å svare på spørsmål eller jobbe med problemer relatert til Robo Rally prosjektet.
  * Et kort retroperspektiv.
  Vi er fornøyd med å ha innført parprogrammering. Parprogrammering det gjør det litt lettere å utføre oppgaver, samtidig som man får litt bedre innsikt i helheten av koden ettersom vi har satt sammen par utifra de som hadde minst felleskunnskap.
  Ettersom det har vært litt kort tidsfrist fra den 4. obligatoriske oppgave ble opprettet til den skulle leveres inn, har vi ikke har ordentlig prioritert å sette opp arbeidsmetoder på grunnlag av et retropersketiv. Vi prioriterte bare å få gjort flest mulige av arbeidskravene.
  * Referat fra møter ligger under mappen SiliconRally/Deliverables/Møtereferater som pdf filer.

Oversikt over foredeling og status av oppgaver ligger på [trello](https://trello.com/b/zOgCmmNW/silicon-rally)




## Deloppgave 2: Krav
Prioritet av arbeidsoppgaver i følgende reggefølge
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
  14. play agains AI
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
