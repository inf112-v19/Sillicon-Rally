# Deloppgave 1 - Prosjekt og prosjektstruktur

## Erfaringer vi har gjort oss om prosjektdynamikk

Det er bred enighet gruppen om at parprogrammering er noe vi burde ha startet med tidligere.
Ikke bare gj�r det at vi f�r balansert ut hvor mye hver person har kodet, men det gj�r
det langt lettere � overf�re kunnskap fra �n person til en annen. 
Det ligger i slike gruppeoppgavers natur at gruppens medlemmer har forskjellige styrker, og ved 
� g� sammen i par har vi st�rre mulighet til � l�re av hverandre. 

Vi har ikke v�rt spesielt flinke til � planlegge m�ter utover de oppsatte lab- og forelesningstimene. 
De fleste m�tene har v�rt planlagt en eller to dager i forveien, og gjort at ikke alle kunne m�te.
I tillegg kunne vi v�rt flinkere formalisere m�tene, som hadde gjort det enklere � skrive gode referat. Vi har
stort sett hatt veldig uformelle m�ter, uten agenda eller plan annet enn � fordele oppgaver. 

## Gruppedynamikken 
Vi er fremdeles godt forn�yd med gruppdynamikken. Lagmedlemene har tatt eierskap til sine egne oppgaver,
vi har luftet ideer sammen og vi kommuniserer godt. 

#### Kommunikasjon
Kommunikasjonen har foreg�tt stort sett over facebook eller face-to-face. Den har foreg�tt realtivt godt, 
men vi kunne v�rt flinkere til � synkronisere hvilke oppgaver vi jobbet med. Det har v�rt tilfeller der to 
personer har jobbet med samme oppgave uten � snakke med hverandre. Heldigvis har vi oppdaget dette relativt tidlig
hver gang, men vi kunne spart en del tid ved � unng� dette.

## Referat

# Retroperspektiv

#### Ting vi endret underveis

Vi gjorde en del forarbeid f�r vi begynte med � kode slik at vi skulle slippe � endre m�ten spillet er satt opp underveis.
Likevel gjorde vi noen oppdagelser etterhvert som gjorde at vi m�tte endre en del likevel. 

Den st�rste endringen vi gjorde var � flytte all logikken til � kj�res hver gang nytt input ble registrert, til � bli kj�rt
ettersom diverse conditions ble oppfylt i GameScreen sin rednermetode. Fordelen var at n� kunne diverse oppdateringer i spillets og
spillerenes state vises i real time p� skjermen, men det gav oss nye utofrdringer. Den st�rste utfordringen var at n� ville all logikken
kj�res hver gang rendermetoden kalles (60ish ganger i sekundet), s� da ble det n�dvendig � gjemme deler av koden bak diverse if/else statements. 
For eksempel skal man ikke kunne velge kort mens en runde utf�res, og en runde skal ikke utf�res samtidig som man velger kort.
Dette f�rer til at koden blir noe mer uoeversiktlig og vanskelig � utvikle, men det er fremdeles et valg vi er forn�yde med, siden spillet n� 
oppf�rer seg mer som man forventer at et spill skal oppf�re seg. Tidligere hadde vi problemer med at en hel runde ble utf�rt uten at noe annet
enn resultatet p� den runden ble vist p� skjermen.

#### Ting som har fungert godt


##### Kode

Vi er godt fordn�yd med � ha tatt i bruk programmet Tiled som vi bruker til � lage kart. Tiled har tatt seg av mye av den tunge l�ftingen ved �
lage grafisk tilfredsstillende kart. 
I tillegg er vi forn�yd med m�ten vi brukte datastrukturen TileGrid og Tile til � simulere kartet i koden. Dette lot oss gj�re all logikk og
aritmetikk som egentlig gj�res p� pikselniv� i Libgdx om til "Tileniv�" i koden v�r. 

##### Samarbeid

Nok en gang m� vi trekke inn parprogrammering som en ordning vi er godt forn�yd med.
Ellers har vi ikke gjort noen uvanlige valg, vi har stort sett jbobet jevnt og trutt hele semesteret, hatt m�ter minst �n gang hver uke,
og holdt hverandre oppdatert p� facebook. 

#### Hvis vi skulle fortsatt prosjektet

Siden vi ikke forstod Libgdx spesielt godt i starten, s� gjorde vi noen grunnleggende valg som vi nok hadde justert dersom vi skulle fortsatt
med prosjektet. For eksempel ville vi konsentrert dataflyten i programmet p� en slik m�te at det hadde v�rt enkelt � gj�re spillet mulig � 
spille p� flere PCer samtidig. S�nn som det er n� er v�r st�rste utfordring med nettverk at vi ikke har noen fornuftig "inngangsport" til
programmet. 

I tillegg hadde vi muligens tatt i bruk ting som "Stage" i Libgdx, ting som vi ikke visste eksisterte eller hvordan fungerte f�r vi var godt
uti utviklingen av programmet. 


#### Det viktigste vi har l�rt

Den kanskje viktigste tingen vi har l�rt mens vi jobbet med dette prosjektet, er � sette seg inn i et relativt stort og utfordrende rammeverk
s�nn som Libgdx. Det � ta i bruk kode som er skrevet av andre gj�r at vi kan "outsource" mye av det tyngste arbeidet. Samtidig tvinger det oss
til � jobbe rundt rammeverket, til � strukturere prosjektet v�rt p� en m�te vi kanskje ikke opplever som optimalt.
Det f�rste veier ofte opp for det andre, og dette har v�rt en s�rdelelis l�rerik opplevelse for alle p� teamet.

N�r det gjelder det � jobbe i team har vi erfart hvor viktig det er � holde hverandre oppdatert p� hva man jobber med til enhver tid.
Vi har hatt et par opplevelser der to personer i teamet jobber med akkurat samme ting, og bedre kommunikasjon kunne forhindret det. 
I en mer profesjonell setting der vi m�ttes daglig kunne vi for eksempel hatt en daglig stand up, slik at alle til enhver tid er ca orientert
om hva de andre holder p� med. 


# Deloppgave 2 - Krav

Liste over krav vi har implementert: 

* Man kan spille en komplett runde
* Man kan vinne spillet ved � bes�ke siste flagg
* Det er lasere p� brettet
* Det er hull p� brettet
* Skademekanismer (spilleren f�r f�rre kort ved skade)
* Spillmekanismer for � skyte andre spillere innen rekkevidde med laser som peker rett frem
* fungerende samleb�nd p� brettet som flytter robotene
* fungerende gyroer p� brettet som flytter robotene
* game over etter 3 tapte liv
* samleb�nd som g�r i dobbelt tempo
* spille mot AI (single-player-mode), evt spill mot random-roboter

Kravene vi forel�pig ikke har implementert er 

* Power down
* Multiplayer over nettverk

Dette er ting vi nedprioriterte og ikke fikk tid til � gj�re. S�rlig dette med multiplayer viste seg � v�re utofrdrende, da vi ikke hadde 
laget koden med tanke p� � involvere mer enn �n datamaskin f�r helt p� slutten. 

# Deloppgave 3 - Kode

### Hvordan man bygger og kj�rer programmet samt testene

Klassediagram ligger vedlagt. 




































