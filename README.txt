Laboration 3
****************
* Jesper Josefsson
* Linus Oleander
****************

HOW TO:
********
Start the program by executing Main.java
The program will ask you to specify files containing stops and lines
********

Programmet startas med klassen Main.java

Main bygger upp GUI:t och instansierar DijkPath, som implementerar interfacet Path.
Main använder hjälpklasserna från Lab3Help för GUI:t samt för att parsa inputfiler.
Main bygger kartan med hjälp av grafen i DijkPath samt ritar ut kortaste vägen.
Main skriver även ut ruttinformation i textfönstret. I fönstret ges även information om exekveringstid.

DijkPath bygger en graf med klassen Graph med konstruktorns argument.
DijkPath räknar ut kortaste avståndet mellan två noder i sin graf med hjälp av Dijkstras algoritm. 
Vid uträkning inkapslas varje GraphNode i en nod av klassen Node så att vi ska kunna spara undan extra information som bara används under uträkningen.

En Graph är en graf som innehåller noder av klassen GraphNode. Sökning efter hållplats på basis av namn (en sträng) i grafen är O(1) med hjälp av en HashMap.
Graph innehåller en nodlista. Grannlistor för varje nod fås snabbt genom att ta fram båglistan för given nod, eftersom bågarna innehåller pekare till sina destinationer. 

En GraphNode innehåller en hållplats i form av en BStop samt en lista på utgående bågar av klassen Edge.

En Edge är en båge som representerar en linje mellan två GraphNodes. Den innehåller information om ruttens längd och utgångs- och målnoder.

PriorityQueue är en uppdaterarbar prioritetskö som bygger på en GenAHeap.

GenAHeap är en uppdaterbar heap. Beskrivs i föregående labb.

Node innehåller en GraphNode samt information om rutten som användes för att nå GraphNoden. Vi sparar undan föregående nod i rutten samt hur länge det tog att ta sig från den föregående noden. Därmed kan man gå bakåt från destinationen för att finna den slutgiltiga vägen.

GeneralException är en undantagsklass för icke-specificerade undantag. 

För vidare information, se JavaDoc-filer i mappen Doc.

Mappen Input files innehåller exempelfiler med hållplatser och linjer.


KOMPLEXITET
***********

Komplexiteten för vår implementation av Dijsktras algoritm bör vara O((m+n)log n) eftersom prioritetskön är snabbt uppdaterbar.

TESTER
******

Prioritetskön samt heapen är testade under utveckling av labb2.

DijkPath har testats för hand (flyglinjerna), samt med testing.jar-paketet som fanns tillgängligt för labben.
Hela Lab3FileTest utfördes med stops-bvs.txt och lines-bvs.txt utan fel. Se utskrift längre ner.
Dessutom utfördes >300 000 lyckade tester med Lab3GenTest.

Vi har dessutom testat med broken-lines-filerna i Input files-mappen. Dessa ser till att algoritmen kan hantera sträckor mellan icke-anknutna noder.



Resultat av Lab3FileTest med stops-bvs.txt och lines-bvs.txt:


java -jar testing.jar lab3test.Lab3FileTest "DijkPath stops-bvs.txt lines-bvs.txt"
Press any key to stop testing..

573/418609 tests done so far. successes: 573, failures: 0.
1249/418609 tests done so far. successes: 1249, failures: 0.
.....
.....
418000/418609 tests done so far. successes: 418000, failures: 0.
<terminated since done with testing>
tests: 418609/418609, successes: 418609, failures: 0

