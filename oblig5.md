# Deloppgave 1 - Prosjekt og prosjektstruktur

## Erfaringer vi har gjort oss om prosjektdynamikk

Det er bred enighet gruppen om at parprogrammering er noe vi burde ha startet med tidligere.
Ikke bare gjør det at vi får balansert ut hvor mye hver person har kodet, men det gjør
det langt lettere å overføre kunnskap fra én person til en annen. 
Det ligger i slike gruppeoppgavers natur at gruppens medlemmer har forskjellige styrker, og ved 
å gå sammen i par har vi større mulighet til å lære av hverandre. 

Vi har ikke vært spesielt flinke til å planlegge møter utover de oppsatte lab- og forelesningstimene. 
De fleste møtene har vært planlagt en eller to dager i forveien, og gjort at ikke alle kunne møte.
I tillegg kunne vi vært flinkere formalisere møtene, som hadde gjort det enklere å skrive gode referat. Vi har
stort sett hatt veldig uformelle møter, uten agenda eller plan annet enn å fordele oppgaver. 

## Gruppedynamikken 
Vi er fremdeles godt fornøyd med gruppdynamikken. Lagmedlemene har tatt eierskap til sine egne oppgaver,
vi har luftet ideer sammen og vi kommuniserer godt. 

#### Kommunikasjon
Kommunikasjonen har foregått stort sett over facebook eller face-to-face. Den har foregått realtivt godt, 
men vi kunne vært flinkere til å synkronisere hvilke oppgaver vi jobbet med. Det har vært tilfeller der to 
personer har jobbet med samme oppgave uten å snakke med hverandre. Heldigvis har vi oppdaget dette relativt tidlig
hver gang, men vi kunne spart en del tid ved å unngå dette.

## Referat

# Retroperspektiv

#### Ting vi endret underveis

Vi gjorde en del forarbeid før vi begynte med å kode slik at vi skulle slippe å endre måten spillet er satt opp underveis.
Likevel gjorde vi noen oppdagelser etterhvert som gjorde at vi måtte endre en del likevel. 

Den største endringen vi gjorde var å flytte all logikken til å kjøres hver gang nytt input ble registrert, til å bli kjørt
ettersom diverse conditions ble oppfylt i GameScreen sin rednermetode. Fordelen var at nå kunne diverse oppdateringer i spillets og
spillerenes state vises i real time på skjermen, men det gav oss nye utofrdringer. Den største utfordringen var at nå ville all logikken
kjøres hver gang rendermetoden kalles (60ish ganger i sekundet), så da ble det nødvendig å gjemme deler av koden bak diverse if/else statements. 
For eksempel skal man ikke kunne velge kort mens en runde utføres, og en runde skal ikke utføres samtidig som man velger kort.
Dette fører til at koden blir noe mer uoeversiktlig og vanskelig å utvikle, men det er fremdeles et valg vi er fornøyde med, siden spillet nå 
oppfører seg mer som man forventer at et spill skal oppføre seg. Tidligere hadde vi problemer med at en hel runde ble utført uten at noe annet
enn resultatet på den runden ble vist på skjermen.

#### Ting som har fungert godt


##### Kode

Vi er godt fordnøyd med å ha tatt i bruk programmet Tiled som vi bruker til å lage kart. Tiled har tatt seg av mye av den tunge løftingen ved å
lage grafisk tilfredsstillende kart. 
I tillegg er vi fornøyd med måten vi brukte datastrukturen TileGrid og Tile til å simulere kartet i koden. Dette lot oss gjøre all logikk og
aritmetikk som egentlig gjøres på pikselnivå i Libgdx om til "Tilenivå" i koden vår. 

##### Samarbeid

Nok en gang må vi trekke inn parprogrammering som en ordning vi er godt fornøyd med.
Ellers har vi ikke gjort noen uvanlige valg, vi har stort sett jbobet jevnt og trutt hele semesteret, hatt møter minst én gang hver uke,
og holdt hverandre oppdatert på facebook. 

#### Hvis vi skulle fortsatt prosjektet

Siden vi ikke forstod Libgdx spesielt godt i starten, så gjorde vi noen grunnleggende valg som vi nok hadde justert dersom vi skulle fortsatt
med prosjektet. For eksempel ville vi konsentrert dataflyten i programmet på en slik måte at det hadde vært enkelt å gjøre spillet mulig å 
spille på flere PCer samtidig. Sånn som det er nå er vår største utfordring med nettverk at vi ikke har noen fornuftig "inngangsport" til
programmet. 

I tillegg hadde vi muligens tatt i bruk ting som "Stage" i Libgdx, ting som vi ikke visste eksisterte eller hvordan fungerte før vi var godt
uti utviklingen av programmet. 


#### Det viktigste vi har lært

Den kanskje viktigste tingen vi har lært mens vi jobbet med dette prosjektet, er å sette seg inn i et relativt stort og utfordrende rammeverk
sånn som Libgdx. Det å ta i bruk kode som er skrevet av andre gjør at vi kan "outsource" mye av det tyngste arbeidet. Samtidig tvinger det oss
til å jobbe rundt rammeverket, til å strukturere prosjektet vårt på en måte vi kanskje ikke opplever som optimalt.
Det første veier ofte opp for det andre, og dette har vært en særdelelis lærerik opplevelse for alle på teamet.

Når det gjelder det å jobbe i team har vi erfart hvor viktig det er å holde hverandre oppdatert på hva man jobber med til enhver tid.
Vi har hatt et par opplevelser der to personer i teamet jobber med akkurat samme ting, og bedre kommunikasjon kunne forhindret det. 
I en mer profesjonell setting der vi møttes daglig kunne vi for eksempel hatt en daglig stand up, slik at alle til enhver tid er ca orientert
om hva de andre holder på med. 


# Deloppgave 2 - Krav

Liste over krav vi har implementert: 

* Man kan spille en komplett runde
* Man kan vinne spillet ved å besøke siste flagg
* Det er lasere på brettet
* Det er hull på brettet
* Skademekanismer (spilleren får færre kort ved skade)
* Spillmekanismer for å skyte andre spillere innen rekkevidde med laser som peker rett frem
* fungerende samlebånd på brettet som flytter robotene
* fungerende gyroer på brettet som flytter robotene
* game over etter 3 tapte liv
* samlebånd som går i dobbelt tempo
* spille mot AI (single-player-mode), evt spill mot random-roboter

Kravene vi foreløpig ikke har implementert er 

* Power down
* Multiplayer over nettverk

Dette er ting vi nedprioriterte og ikke fikk tid til å gjøre. Særlig dette med multiplayer viste seg å være utofrdrende, da vi ikke hadde 
laget koden med tanke på å involvere mer enn én datamaskin før helt på slutten. 

# Deloppgave 3 - Kode

### Hvordan man bygger og kjører programmet samt testene

Klassediagram ligger vedlagt. 




































