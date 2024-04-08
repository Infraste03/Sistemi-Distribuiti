L'obiettivo del progetto è realizzare un' applicazione client-server per l'acquisto di prodotti a costo vantaggioso.
Questa applicazione coinvolge un server e un certo numero di client(almeno 3).
L' applicazione è sviluppata con JMS, infatti, è stato utlizzato il paradigma JMS, tramite le librerie jakarta.jms. 
Dal sito activemq.apache.org selezionare la voce :" ActiveMQ Classic 6.0.1 Release" e da qui, fare il download dell' opportuna distribuzione.

Per l' esecuzione del codice tramite Eclipse è necessario seguire i seguenti step:
1) Importare il progetto, ossia la cartella denominata "acquistoProdotti_JMS" all' interno dell' IDE, tramite le seguenti azioni
	Open->Open Project from FileSystem;
2)Selezionando il progetto --> Build Path --> Configure Build Path-->Java Build Path --> Libraries --> Classpath--> Add External JARs, selezionare i seguenti file: "activemq-all-6.0.1.jar", /lib/optional/activemq-log4j-appender-6.0.1.jar, /lib/optional/log4j-api-2.22.0.jar, /lib/optional/log4j-core-2.22.0.jar, /lib/optional/log4j-slf4j2-impl-2.22.0.jar
3) Eseguire prima il file "Server.java" tramite il bottone di run;
4) Medesima procedura deve essere svolta per l' esecuzione del file "Client.java", ricordando però che è necessario avviare tale procedura tre volte;

Alternativamente la verifica del funzionamento del codice può avvenire tramite file jar, attraverso l' esecuzione del file, con percorso:
	acquistoProdotti_JMS/jar/eseguibile.bat


La documentazione Javadoc si trova all' interno della cartella con percorso: acquistoProdotti_JMS/doc
