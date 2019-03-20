    #Obligatorisk Øvelse 3

## Deloppgave 1
1. Rollene funker bra. Kundekontakt kan mest om spillet, regler og hva det er vi egentlig jobber for å få til, mens gruppeleder har overordnet ansvar for koden og at arbeidsoppgaver blir gjort.

2. Vi må lære oss Git bedre. Vi har hatt noen utfordringer mtp hvordan vi merger vårt eget arbeid opp mot andres arbeid.
Vi har i lag diskutert at vi kan bli flinkere til å gi utfyllende krav til arbeidsoppgaver, som å gi arbeidsoppgaver en deadline. Fram til nå har den enkelte person selv valgt oppgaver, uten en spesifisert tidsfrist. Fremover kommer vi til å sette to personer per oppgave, der deadline er kortere enn det som vi ser for oss egentlig trengs. Grunnet at vi som en gruppe kommer til å diskutere de oppgaver som ikke er blitt gjort. Tanken med dette er at det skal speede opp tiden det tar å fullføre oppgaven, ettersom arbeidsteamet på en spesifikk oppgave vil få innspill, og eventuell hjelp fra andre gruppemedlemmer til det som er vanskelig å få til.
Ellers syns vi valgene vi har gjort generelt sett har vært gode. Vi har en god kodebase, alle er dedikerte og vi fungerer bra sammen som team.
Bli enda flinkere til å bruke Trello project board, inkludert deadlines.
 
3. Gruppedynamikken har vært ganske god, vi har en fin stemning oss i mellom. Vi har hatt noen bommert der to personer har jobbet på samme ting, og da har vi kastet bort verdifull tid. Vi ønsker også å øke bidragene (commits) fra noen i teamet slik at antall commits ligger på et mer balansert nivå i dag. Likevel er det en del bidrag fra forskjellige gruppemedlemmer som har vært meget viktig, men vanskelig å kvantifisere i ting som antall commits f.eks grafiske elementer.
 
4. Som nevnt har vi hatt noen problemer med å klart definere arbeidsoppgaver, men vi har generell god kommunikasjon. Ettersom vi har blitt bedre kjente med hverandre, mer vandt til å både jobbe som et team, og flinkere til å bruke Trello, får vi et bedre grunnlag for kommunikasjon. 
    Retroperspektiv:
    Det vi har klart til nå:
    - Vi har vært flinke til å sette opp oppgavene på en fornuftig måte, der vi har satt opp viktige og grunnleggende oppgaver før de mer avanserte og mindre grunnleggende. Vi har generelt vært gode på utdele oppgaver. En svakhet er at størsteparten av kodingen er gjort av et par medlemmer, mens andre ting som å lage kart, designe sprites osv er gjort av de andre på gruppen. Dette gjør at ikke alle er like oppdaterte på kodebasen som vi burde være. Vi ønsker å fikse dette ved å ha mer fokus på parprogrammering fremover, slik at alle blir oppdaterte og i stand til å ta for seg nye oppgaver relatert til hele programmet. 
    Fremover:
    - Mer parprogrammering. Vi er nødt til å få alle til bli omtrent like godt orientert i kodebasen.
    Commits:
    Vi har hatt noen små problemer med git, en del commits har blitt tapt når vi prøvde oss på rebasing. Det er også noen problem med Jørgen (jme011) sine commits ikke kommer opp under brukernavnet sitt og dermed ikke vises under ”Contributors” fanen på github. Er fortsatt ikke helt sikker på hva som forårsaker det. Det virker også som om commits i andre brancher enn master ikke vises på github.
    Møtereferat:
    møtereferat ligger i filen Møtereferat i Deliverables filen.


Forbedringspunkter:
1. Fordeling av arbeidsoppgaver.
2. Bli flinkere med Git. Alle skal gjennom kurset på [learning git branching](https://learngitbranching.js.org/)
3. Skrive flere møtereferater.
 
 
 
## Deloppgave 2
Presisering av krav som har kommet fra kunden. (“X” = Ferdig “(X)” = Delvis ferdig).
     - Kunne få alle typene bevegelseskort			X
     - Dele ut 9 kort						
     - Velge 5 kort (godkjenne valg/si “nå er jeg klar”)		(X)
     - Eksekvere program ut i fra valgte kort			X
     - Besøke flagg						X
     - Hvis robot går av brettet blir den ødelagt og går tilbake til siste backup	(X)
     - Oppdatere backup hvis robot blir stående på skiftenøkkelrute i slutten av en fase
     - Flytte backup ved besøk på flagg			X
     - Kunne spille en fullverdig runde med alle faser
     - Få nye kort til ny runde	(Får nye kort, ingen runde)	X

Teamets prioritering av oppgavene
Vi har prioritert mer grunnleggende funksjoner, få de til å funke ordentlig før vi har gått i gang med å kunne kjøre programmet med en fullverdig runde. Slik at når man kjører programmet med runder, så mangler ikke spillet grunnleggende funksjoner.  Vi har derfor prioritert arbeidsoppgavene i følgende rekkefølge:
       1. Kunne få alle typene bevegelseskort.
       2. Besøke flagg.
       3. Hvis robot går av brettet blir den ødelagt og går tilbake til siste backup
       4. Flytte backup ved besøk på flagg
       5. Få nye kort til ny runde
       6. Eksekvere program ut i fra valgte kort
       7. Velge 5 kort (godkjenne valg/si “nå er jeg klar”)
       8. Dele ut 9 kort
       9. Oppdatere backup hvis robot blir stående på skiftenøkkelrute i slutten av en fase
       10. Kunne spille en fullverdig runde med alle faser
Vi gjorde endringer i prioriteringslisten av program krav på en slik måte at arbeidsoppgavene ble mer naturlig, grunnleggende funksjonalitet før spillmekanikk.

Verifisering av krav:
- For å verifisere at vi kan få alle bevegelses kort har vi laget en funksjon for å trekke nye kort, vi bruker da den funksjonen noen ganger for å se om alle variasjonene blir trukket.
- For å teste kravet om flagget blir plukket opp riktig, gjør vi det manuelt med spillet og sjekker om flagget bare blir plukket opp dersom du stopper på det.
- Vi bruker samme prosedyre med å gå av “mappet” som i punkt nummer 2. 
- For å teste om backupen blir flyttet vil vi prøve å bevege spilleren av brettet både før og etter den har besøkt flagget. Backupen skal da være på flagget etter spilleren har vært der og ikke ha noen backup før.
- For å teste om vi får nye kort til hver runde trenger vi å implementere runder, og se om du får nye kort for hver runde.
- Verifisering av korrekt eksekvering vil vi gjøre manuelt ved å lage en sekvens av bevegelser den skal gjøre å se om den gjør det riktig.
- For å teste valget av 5 kort trenger vi bare å se at spillet fjerner kortene du ikke velger.
- Å sjekke om spillet deler ut 9 kort blir enkelt og greit bare å telle hvor mange kort du får før du velger hvilke 5 du skal beholde.
- Oppdatering av backup når du står på skiftenøkkel vil bli gjort på samme måte som punkt nummer 4.
- For å verifisere om vi kan spille en fullverdig runde skal vi spille spillet og observere om rundene går som de skal i riktig rekkefølge.

Vi mangler foreløpig å implementere runder i spillet, dvs at det ikke er noen struktur enda. Vi har ingen poengsum, og det er ikke alle «farer» som er blitt implementert heller. Det som har blitt prioritert er å få de grunnleggende  byggesteinene på plass slik at vi lettere kan utvide spillet etter hvert.
 
 
## Deloppgave 3
For å kjøre programmet må man sette working directory til core/assets, og så kjøre main-metoden i Main-klassen som ligger i packagen «roboGame».
For å kjøre alle testene i Intellij:
  1. Gå til run -> edit configurations
  2. Lag en ny junit tests configuration
  3. Gi den et valgfritt navn
  4. Velg «Test kind -> All in package»
  5. Velg «In whole project»
  6. Set working directory til «core/assets»
  7. apply
  8. Klikk på pakken inf112.skeleton.app under tests og enten trykk ctrl+shift+F10, eller høyreklikk og run.

Klassediagram
Bildet ligger i Deliverables mappen
