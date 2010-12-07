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

