# TWITTER API TWEETS
## Introduzione
La nostra applicazione ha come obiettivo l'analisi dei `tweets` (post pubblicati da un utente) di un profilo Twitter. I dati relativi ai _tweets_ sono ottenute tramite Twitter API v2. L'analisi consiste nel filtraggio dei _tweets_ in base alle menzioni presenti nel testo.
Utilizzando l'API Reference Twitter si possono filtrare statistiche _come_: 
- Utenti più menzionati
- Numero di menzioni nel post
- Percentuale menzioni nei post
- Quante volte è stato menzionato un utente negli ultimi 100 tweets 

***
## Plus del programma
- Statistiche e filtri aggiuntivi
- Eccezioni personalizzate

***
## Rotte
L'utente può effettuare le richieste dal seguente indirizzo:
http://localhost:8080

Le rotte sono le seguenti:
**NOME** | **TIPO** | **INFORMAZIONI**
|---|:---:|---|
_/stats_ | Get | Restituisce il numero totale di menzioni e il numero percentuale di menzioni.
_/tweets_ | Get | Restituisce un numero di tweets in base al numero di menzioni presenti nel testo.
_/mentioned_ | Get | Calcola quante volte è stato menzionato un utente X sulla timeline dell'utente principale
_/mostMentioned_ | Get | Restituisce una lista degli utenti più menzionati in base al numero inserito.

## Esempi di chiamata
- `/stats`

    http://localhost:8080/stats?userName=FCBarcelona
```json
{
    "Total_number_of_mentions": 40,
    "Average_number_of_mentions": 0.4
}
```
#### STRUTTURA LISTA JSON
- "Total_number_of_mentions":
numero totale delle menzioni presenti negli ultimi 100 tweets (se ci sono)
- "Average_number_of_mentions":
numero totale delle menzioni diviso il numero totale dei tweets (post)

- `/tweets`

    http://localhost:8080/stats?userName=FCBarcelona
```json
[
    {
        "id": "1484242233259929600",
        "text": "Away Kits Ready 💜💜\n#AthleticBarça https://t.co/3b3PaSRQ9Y",
        "numOfMentions": 0,
        "mentions": {}
    },
    {
        "id": "1484240669577302029",
        "text": "The day Xavi was hailed by the San Mamés https://t.co/7Yg7ZsZNQI",
        "numOfMentions": 0,
        "mentions": {}
    },
]
```
> (sono stati presi solo i 2 tweets più recenti)
#### STRUTTURA LISTA JSON
- "id":
Rappresenta l'ID del tweet

- "text":
Rappresenta il testo del tweet

- "numOfMentions":
Rappresenta il numero di menzioni nel testo

- "mentions":
Rappresenta gli utenti menzionati
- `/mentioned`

    http://localhost:8080/mentioned?userName=FCBarcelona&userName2=JoanLaportaFCB
```
JoanLaportaFCB e' stato menzionato 2 volte
```
#### STRUTTURA LISTA JSON
Conta quante volte è stato menzionato quell'utente nella timeline del 1° utente
- `/mostMentioned`

    http://localhost:8080/mostMentioned?userName=FCBarcelona&numOfMostMentioned=3
```json 
{
    "GironaFC": 4,
    "alexiaputellas": 3,
    "GaryLineker": 2
}
```
#### STRUTTURA LISTA JSON

Chi sono gli utenti più menzionati sulla timeline del 1° utente
***
## DOCUMENTAZIONE
Il codice è stato commentato in Javadoc.
***
## STRUMENTI UTILIZZATI
- **Eclipse** (Ambiente di sviluppo del codice Java)
- **Postman** (Strumento utilizzato per fare le chiamate)
- **Javadoc** (Per la generazione automatica della documentazione del codice sorgente scritto in linguaggio Java)
- **Visual Studio Code** (Ambiente di sviluppo utilizzato per il documento di markdown per il README)
***
## ECCEZIONI
#### PROVE ECCEZIONI (esempi errori)
1. http://localhost:8080/mostMentioned?userName=FCBavASDvrcelona&numOfMostMentioned=5
```
Username not found
```
2. http://localhost:8080/mostMentioned?userName=FCBarcelona&numOfMostMentioned=-5
```
numOfMostMentioned deve essere un numero positivo
```
3. http://localhost:8080/mostMentioned?userName=FCBarcelona&numOfMostMentioned=kjkj
```
numOfMostMentioned deve essere un numero! 
```
***
Oltre alle eccezioni standard di java abbiamo aggiunto delle eccezioni personalizzate:

- **IOException**

Questa eccezione è correlata alle operazioni di Input e Output nel codice Java. Si verifica quando si verifica un errore durante le operazioni di lettura, scrittura e ricerca di file o directory.
- **URISyntaxException**

Si tratta di un'eccezione verificata che si verifica quando si tenta di analizzare una stringa che rappresenta un URI, ma non ha il formato corretto.

- **JsonSyntaxException**

Questa eccezione viene sollevata quando Gson tenta di leggere (o scrivere) un elemento JSON non corretto.

- **IllegalArgumentException**

Viene generata un'eccezione IllegalArgumentException per indicare che ad un metodo è stato passato un argomento non corretto. Viene visualizzato il seguente messaggio:

> _"Username not found"_
***
## TEST JUNIT
Nell'applicazione sono presenti anche una serie di test:

1. Test 1

> Testa il numero di menzioni presenti nel tweet

2. Test 2

> Testa se è stata inserita correttamente la menzione (nome dell'utente e quante volte è stato menzionato)

***
## AUTORI
- Zaki Mina
- Zeccato Francesco Nello
