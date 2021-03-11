# Spedizione
Progetto di Programmazione a Oggetti 2020/2021 Gibertini Giacomo

Il progetto ha come punto d'inizio la classe MainClass. Questa classe gestisce la visualizzazione di tutte le finestre del programma attraverso un CardLayout.
La prima finestra è implementata dalla classe WelcomePanel, che estende JPanel e permette all'utente di accedere come amministratore o semplice utente. 

Il form di login è realizzato dalla classe Login, che estende LoginPanel. LoginPanel gestisce gli elementi del form (JButton, JLabel, JTextField), mentre la classe Login 
implementa l'ActionListener e permette di accedere alla home page dell' amministratore o utente. Nel login dell'utente c'è anche la possibilità di registrare un nuovo utente 
(realizzato dalle classi Registrazione e RegistrazionePanel).

La classe AdminHome, che estende la classe astratta Home, realizza la home page dell' amministratore. In questa finestra è presente la tabella di tutte le spedizioni di tutti 
gli utenti (vedi successivamente il paragrafo su AdminTable) e la possibilità, attraverso i rispettivi JButton, di eliminare una spedizione, effettuare il logout e salvare le 
modifiche apportate alla tabella. Lo stato delle spedizioni cambia in automatico attraverso un thread (classe MyThread) ogni 6000 ms. La presenza del thread giustifica 
l'utilizzo di un Vector (che è thread-safe) per la gestione delle spedizioni.
La classe UserHome, che estende la classe astratta Home, realizza la home page per l'utente. In questa finestra è presente il form per l'invio di una spedizione da parte 
dell'utente (classe SpedizionePanel) e la tabella delle spedizioni inviate dall'utente. Anche l'utente ha la possibilità di effettuare il logout.
La classe Home definisce due funzioni (aggiungiSpedizione() e salvaSpedizioni()) che garantiscono la persistenza dei dati caricando da file e salvando su filee. 

Le spedizioni possono essere normali (classe Spedizione) oppure assicurate, realizzate dalla classe SpedizioneAssicurata che estende la classe Spedizione.

La classe ColorTable gestisce il colore delle righe della tabella. Il colore è scelto in base allo stato della spedizione, perciò le righe della tabella che rappresentano 
spedizioni che hanno lo stesso stato avranno lo stesso colore.
La tabella dell'amministratore e quella dell'utente sono realizzate rispettivamente dalla classe AdminTable (con modello AdminTableModel) e UserTable (con modello 
UserTableModel), che estendono entrambe la classe astratta Table. La tabella dell'amministratore non è editabile, con l'unica possibilità per l' amministratore di eliminare una 
riga se la spedizione ha raggiunto uno stato finale (consegnata oppure fallita senza rimborso), mentre l'utente può modificare lo stato della spedizione nel caso in cui sia una 
spedizione assicurata e sia fallita. In quel caso, attraverso una JComboBox, l'utente può richiedere il rimborso della spedizione.
