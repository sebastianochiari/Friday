CREATE DATABASE  IF NOT EXISTS `fridaydb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `fridaydb`;
-- MySQL dump 10.13  Distrib 8.0.13, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: fridaydb
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cookies`
--

DROP TABLE IF EXISTS `cookies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cookies` (
  `cookieID` int(11) NOT NULL,
  `LID` int(11) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Deadline` bigint(45) NOT NULL,
  PRIMARY KEY (`cookieID`),
  KEY `LID_idx` (`LID`),
  KEY `Email_idx` (`Email`),
  CONSTRAINT `Email` FOREIGN KEY (`Email`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `LID` FOREIGN KEY (`LID`) REFERENCES `lists` (`lid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cookies`
--

LOCK TABLES `cookies` WRITE;
/*!40000 ALTER TABLE `cookies` DISABLE KEYS */;
/*!40000 ALTER TABLE `cookies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `list_categories`
--

DROP TABLE IF EXISTS `list_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `list_categories` (
  `LCID` int(11) NOT NULL,
  `Name` varchar(250) NOT NULL,
  `Note` text,
  `Image` varchar(250) DEFAULT NULL,
  `Email` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`LCID`),
  KEY `Email` (`Email`),
  CONSTRAINT `list_categories_ibfk_1` FOREIGN KEY (`Email`) REFERENCES `users` (`email`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list_categories`
--

LOCK TABLES `list_categories` WRITE;
/*!40000 ALTER TABLE `list_categories` DISABLE KEYS */;
INSERT INTO `list_categories` VALUES (1,'Spesa','La tua lista per la spesa della settimana','spesa.jpg','sebastianochiari8@gmail.com'),(2,'Fai da te','Quando si rompe qualcosa, qui troverai l\'occorrente da comprare','fai-da-te.jpg','sebastianochiari8@gmail.com'),(3,'Party','La febbre del sabato sera colpisce ancora','party.jpg','sebastianochiari8@gmail.com'),(4,'Scuola','Non far mancare nulla ai tuoi bambini il primo giorno di scuola','scuola.jpg','sebastianochiari8@gmail.com');
/*!40000 ALTER TABLE `list_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lists`
--

DROP TABLE IF EXISTS `lists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lists` (
  `LID` int(11) NOT NULL,
  `Name` varchar(250) NOT NULL,
  `Note` text,
  `Image` varchar(250) DEFAULT NULL,
  `LCID` int(11) DEFAULT NULL,
  `List_Owner` varchar(250) DEFAULT NULL,
  `CookieID` int(11) DEFAULT NULL,
  PRIMARY KEY (`LID`),
  KEY `lists_ibfk_1` (`LCID`),
  KEY `lists_ibfk_2` (`List_Owner`),
  KEY `CookieID_idx` (`CookieID`),
  CONSTRAINT `CookieID` FOREIGN KEY (`CookieID`) REFERENCES `cookies` (`cookieid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `LCID` FOREIGN KEY (`LCID`) REFERENCES `list_categories` (`lcid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `lists_ibfk_2` FOREIGN KEY (`List_Owner`) REFERENCES `users` (`email`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lists`
--

LOCK TABLES `lists` WRITE;
/*!40000 ALTER TABLE `lists` DISABLE KEYS */;
INSERT INTO `lists` VALUES (1,'Spesa','Spesa settimanale','spesa.jpg',1,'sebastiano.chiari@gmail.com',NULL),(2,'Cameretta','Materiale per la stanza di Filippo','fai-da-te.jpg',2,'sebastiano.chiari@gmail.com',NULL),(3,'Scuola','Lista per la scuola','scuola.jpg',4,'tommaso.bosetti@gmail.com',NULL),(4,'Party di compleanno','Lista per la festa a sorpresa di Leonardo','party.jpg',3,'marta.toniolli@gmail.com',NULL),(5,'Sanremo','Festival della canzone italiana','party.jpg',3,'pippo.baudo@rai.tv.it',NULL),(6,'Spesa','IMPORTANTE: comprare il latte','spesa.jpg',1,'giorgiosgl@gmail.com',NULL),(7,'Spesa','Ricordati di controllare la lista che Anna ha messo sul frigo','spesa.jpg',1,'gianni.morandi@alice.it',NULL),(8,'Carne per papà','Ricordarsi entro sabato','spesa.jpg',1,'tommaso.bosetti@gmail.com',NULL),(9,'Porta del bagno','Mancano alcuni tasselli che dobbiamo far fare dal falegname','fai-da-te.jpg',2,'leonardoremondini@gmail.com',NULL),(10,'Pulizia Polo sister','Non conveniva andare all\'autolavaggio?','fai-da-te.jpg',2,'marta.toniolli@gmail.com',NULL),(11,'Post-concerto','Dobbiamo passare in farmacia','party.jpg',3,'britney.spears@hotmail.it',NULL),(12,'Zuccheri','Devo mandarla al mio manager, devo trovare queste cose in camerino','party.jpg',3,'qwer@tom.tom',NULL),(13,'Art Attack','Questo è un Art Attack','fai-da-te.jpg',2,'giovanni.muciacia@gmail.com',NULL);
/*!40000 ALTER TABLE `lists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `messages` (
  `MessageID` int(11) NOT NULL,
  `LID` int(11) DEFAULT NULL,
  `Sender` varchar(250) DEFAULT NULL,
  `Text` longtext,
  PRIMARY KEY (`MessageID`),
  KEY `listLID_idx` (`LID`),
  KEY `userEmail_idx` (`Sender`),
  CONSTRAINT `listLID` FOREIGN KEY (`LID`) REFERENCES `lists` (`lid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userEmailSender` FOREIGN KEY (`Sender`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,5,'gianni.morandi@alice.it','Ciao Pippo, purtroppo io non posso andare a comprare queste cose quindi poi dimmi quanti soldi hai speso'),(2,5,'gianni.morandi@alice.it','A dopo'),(3,3,'tommaso.bosetti@gmail.com','Ciao amici, vi ho condiviso questa lista per il rientro dalle lezioni'),(4,3,'tommaso.bosetti@gmail.com','Se pensate che possa servire altro, inserite pure'),(5,3,'leonardoremondini@gmail.com','Mi sembra che ci sia tutto'),(6,4,'marta.toniolli@gmail.com','Mi raccomando, non condividetela con Leonardo'),(7,4,'marta.toniolli@gmail.com','Appena torno a casa aggiungo un paio di cose'),(8,3,'sebastiano.chiari@gmail.com','Ditemi quanto vi devo poi eh '),(9,3,'sebastiano.chiari@gmail.com','Sto andando a fare la spesa, manca qualcosa?'),(10,11,'britney.spears@hotmail.it','Sta sera ci sballiamo Ariana dopo il concerto'),(11,11,'qwer@tom.tom','Ricordami che porto anche due bottiglie di vino che ho a casa'),(12,5,'pippo.baudo@rai.tv.it','Non ti preoccupare, passo io tra mezz\'ora alla Conad '),(13,5,'pippo.baudo@rai.tv.it','Ricordati di chiamare Claudio'),(14,4,'giorgiosgl@gmail.com','Per favore non spendiamo troppo che non ho tanti soldi');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_categories`
--

DROP TABLE IF EXISTS `product_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product_categories` (
  `PCID` int(11) NOT NULL,
  `Name` varchar(250) NOT NULL,
  `Note` text,
  `Logo` varchar(250) DEFAULT NULL,
  `Email` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`PCID`),
  KEY `Email` (`Email`),
  CONSTRAINT `product_categories_ibfk_1` FOREIGN KEY (`Email`) REFERENCES `users` (`email`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_categories`
--

LOCK TABLES `product_categories` WRITE;
/*!40000 ALTER TABLE `product_categories` DISABLE KEYS */;
INSERT INTO `product_categories` VALUES (1,'Frutta e verdura','Fresche, al banco','frutta.png','sebastianochiari8@gmail.com'),(2,'Carne','I migliori tagli da allevamenti italiani','carne.png','sebastianochiari8@gmail.com'),(3,'Pasta','Ogni varietà, per una diete mediterranea ricca','pasta.png','sebastianochiari8@gmail.com'),(4,'Colazione','Per cominciare la giornata col piede giusto','colazione.png','sebastianochiari8@gmail.com'),(5,'Surgelati','Piatti già pronti facili da scaldare','surgelati.png','sebastianochiari8@gmail.com'),(6,'Prodotti per la casa','Detergenti, stracci, panni','casa.png','sebastianochiari8@gmail.com'),(7,'Utensili','Trapani, avvitatori, levigatrici','utensili.png','sebastianochiari8@gmail.com'),(8,'Ferramenta','Chiodi, bulloni, viti','ferramenta.png','sebastianochiari8@gmail.com'),(9,'Meccanico','Tutto l\'occorrente per un check-up all\'automobile','meccanico.png','sebastianochiari8@gmail.com'),(10,'Giardinaggio','Fate sbocciare il pollice verde che c\'è in voi','giardinaggio.png','sebastianochiari8@gmail.com'),(11,'Abbigliamento','Qualsiasi capo per qualsiasi occasione','abbigliamento.png','sebastianochiari8@gmail.com'),(12,'Bibite','Per non rimanere mai a secco','bibite.png','sebastianochiari8@gmail.com'),(13,'Musica','CD, audiocassette','music.png','sebastianochiari8@gmail.com'),(14,'Medicinali','In caso di emergenza','farmacia.png','sebastianochiari8@gmail.com'),(15,'Snack','Se non ti lecchi le dita godi solo a metà','snack.png','sebastianochiari8@gmail.com'),(16,'Cancelleria','Penne, colle, evidenziatori','cancelleria.png','sebastianochiari8@gmail.com'),(17,'Zaini','Rivenditori ufficiali EastPack','zaini.png','sebastianochiari8@gmail.com'),(18,'Quaderni','Qualsiasi forma, righe o quadretti, ad anelli','school.png','sebastianochiari8@gmail.com'),(19,'Elettronica','Calcolatrici, computer','elettronica.png','sebastianochiari8@gmail.com'),(20,'Agende e diari','Sia per la scuola che per il lavoro','school.png','sebastianochiari8@gmail.com');
/*!40000 ALTER TABLE `product_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_lists`
--

DROP TABLE IF EXISTS `product_lists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product_lists` (
  `PID` int(11) NOT NULL,
  `LID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`PID`,`LID`),
  KEY `product_lists_LID_idx` (`LID`) /*!80000 INVISIBLE */,
  CONSTRAINT `product_lists_LID` FOREIGN KEY (`LID`) REFERENCES `lists` (`lid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_lists_PID` FOREIGN KEY (`PID`) REFERENCES `products` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_lists`
--

LOCK TABLES `product_lists` WRITE;
/*!40000 ALTER TABLE `product_lists` DISABLE KEYS */;
INSERT INTO `product_lists` VALUES (5,1,3),(14,8,5),(19,7,2),(27,6,1),(28,1,2),(31,6,1),(31,7,1),(34,7,1),(44,1,1),(44,8,6),(48,12,2),(50,8,3),(53,7,1),(61,9,1),(62,2,1),(62,9,1),(66,2,1),(69,2,1),(79,9,1),(85,10,1),(86,10,1),(89,10,1),(90,10,1),(111,4,2),(113,4,2),(116,4,3),(117,11,1),(118,4,2),(118,12,1),(119,5,1),(120,5,3),(133,11,2),(134,1,1),(139,11,4),(143,4,1),(150,5,1),(150,12,1),(155,13,1),(156,3,1),(157,3,1),(158,13,1),(159,13,1),(160,13,1),(177,3,2);
/*!40000 ALTER TABLE `product_lists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `products` (
  `PID` int(11) NOT NULL,
  `Name` varchar(250) NOT NULL,
  `Note` text,
  `Logo` varchar(250) DEFAULT NULL,
  `Photo` varchar(250) DEFAULT NULL,
  `PCID` int(11) DEFAULT NULL,
  `Email` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`PID`),
  KEY `products_ibfk_1` (`PCID`),
  KEY `products_ibfk_2` (`Email`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`PCID`) REFERENCES `product_categories` (`pcid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `products_ibfk_2` FOREIGN KEY (`Email`) REFERENCES `users` (`email`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Pomodoro','Az. Agricola La Fattoria del Sole\"\"','la-fattoria-del-sole.png','pomodoro.png',1,'sebastianochiari8@gmail.com'),(2,'Carota','Az. Agricola La Fattoria del Sole\"\"','la-fattoria-del-sole.png','carota.png',1,'sebastianochiari8@gmail.com'),(3,'Cetriolo','Az. Agricola La Fattoria del Sole\"\"','la-fattoria-del-sole.png','cetriolo.png',1,'sebastianochiari8@gmail.com'),(4,'Rucola','Az. Agricola La Fattoria del Sole\"\"','la-fattoria-del-sole.png','rucola.png',1,'sebastianochiari8@gmail.com'),(5,'Arancia','Az. Agricola Sapori di Sicilia\"\"','sapori-di-sicilia.png','arancia.png',1,'sebastianochiari8@gmail.com'),(6,'Radicchio','Az. Agricola La Fattoria del Sole\"\"','la-fattoria-del-sole.png','radicchio.png',1,'sebastianochiari8@gmail.com'),(7,'Mela','Az. Agricola Melinda\"\"','melinda.png','mela.png',1,'sebastianochiari8@gmail.com'),(8,'Pera','Az. Agricola Sapori di Sicilia\"\"','sapori-di-sicilia.png','pera.png',1,'sebastianochiari8@gmail.com'),(9,'Banana','Az. Agricola Sapori di Sicilia\"\"','sapori-di-sicilia.png','banana.png',1,'sebastianochiari8@gmail.com'),(10,'Uva','Az. Agricola Sapori di Sicilia\"\"','sapori-di-sicilia.png','uva.png',1,'sebastianochiari8@gmail.com'),(11,'Bocconcini di vitello','Coop, alimentazione no OMG','coop.png','vitello.png',2,'sebastianochiari8@gmail.com'),(12,'Braciola di lombo','Borgo Buono, la tradizione artigianale si fonde con le più avanzate tecnologie per garantire qualità, gusto e sapore','borgo-buono.png','braciola.png',2,'sebastianochiari8@gmail.com'),(13,'Petto di pollo','Tracciabilità totale, filiera di qualità','coop.png','petto.png',2,'sebastianochiari8@gmail.com'),(14,'Macinato','Borgo Buono, macinato bovino di razza chianina','borgo-buono.png','macinato.png',2,'sebastianochiari8@gmail.com'),(15,'Cotoletta di pollo','Aia, Cotolette di filetti di pollo','aia.png','cotoletta.png',2,'sebastianochiari8@gmail.com'),(16,'Cotechino','Negroni, solo carne italiana, macinatura media','negroni.png','cotechino.png',2,'sebastianochiari8@gmail.com'),(17,'Faraona','Coop, faraona a metà in parti, 500g ca','coop.png','faraona.png',2,'sebastianochiari8@gmail.com'),(18,'Busto di gran gallo','Coop, busto di gran gallo a metà in parti, 850g ca','coop.png','gallo.png',2,'sebastianochiari8@gmail.com'),(19,'Hamburger di bovino','Coop, hamburger di bovino adulto, 220g ca','coop.png','hamburger.png',2,'sebastianochiari8@gmail.com'),(20,'Wurstel Wudy al formaggio','Aia, pollo e tacchino, 150g, senza glutine','aia.png','wurstel.png',2,'sebastianochiari8@gmail.com'),(21,'Fusilli','Coop, fusilli 64 500g','coop.png','fusilli-coop.png',3,'sebastianochiari8@gmail.com'),(22,'Farfalle','Coop, farfalle 85 500g','coop.png','farfalle.png',3,'sebastianochiari8@gmail.com'),(23,'Fusilli','Garofalo, fusilli no.63 pasta di Gragnano 500g','garofalo.png','fusilli-garofalo.png',3,'sebastianochiari8@gmail.com'),(24,'Mezze penne rigate','Coop, mezze penne rigate 67 500g','coop.png','penne.png',3,'sebastianochiari8@gmail.com'),(25,'Linguine','Garofalo, linguine no.12 pasta di Gragnano 500g','garofalo.png','linguine-garofalo.png',3,'sebastianochiari8@gmail.com'),(26,'Linguine','Coop, linguine 13 500g','coop.png','linguine-coop.png',3,'sebastianochiari8@gmail.com'),(27,'Tagliatelle','Coop, tagliatelle 298 pasta all\'uovo 250g','coop.png','tagliatelle-coop.png',3,'sebastianochiari8@gmail.com'),(28,'Pappardelle','Coop, pappardelle 402 pasta all\'uovo 250g','coop.png','pappardelle.png',3,'sebastianochiari8@gmail.com'),(29,'Tortelloni','Barilla emiliane, tortelloni con prosciutto crudo e parmigiano reggiano 250g','barilla.png','tortelloni.png',3,'sebastianochiari8@gmail.com'),(30,'Tagliatelle','Barilla emiliane, tagliatelle all\'uovo 500g','barilla.png','tagliatelle-barilla.png',3,'sebastianochiari8@gmail.com'),(31,'Latte intero','Granarolo latte fresco alta qualità intero, 1L','granarolo.png','intero.png',4,'sebastianochiari8@gmail.com'),(32,'Latte a lunga conservazione','Granarolo latte intero a lunga conservazione, 1L','granarolo.png','lunga-conservazione.png',4,'sebastianochiari8@gmail.com'),(33,'Latte senza lattosio','Granarolo G+ parzialmente scremato senza lattosio, 1L','granarolo.png','no-lattosio.png',4,'sebastianochiari8@gmail.com'),(34,'Gocciole','Pavesi Gocciole chocolate, 500g','pavesi.png','gocciole.png',4,'sebastianochiari8@gmail.com'),(35,'Pan di stelle','Pan di Stelle, il biscotto, 350g','mulino-bianco.png','pan-di-stelle.png',4,'sebastianochiari8@gmail.com'),(36,'Marmellata di albicocche','Rigoni di Asiago Fiordifrutta Albicocche 330g','rigoni-di-asiago.png','albicocche.png',4,'sebastianochiari8@gmail.com'),(37,'Marmellata di prugne','Rigoni di Asiago Fiordifrutta Prugne 330g','rigoni-di-asiago.png','prugne.png',4,'sebastianochiari8@gmail.com'),(38,'Fette biscottate','Coop, fette biscottate dorate, 36 fette, 320g','coop.png','fette-biscottate.png',4,'sebastianochiari8@gmail.com'),(39,'Fette biscottate integrali','Coop, fette biscottate integrali, 36 fette, 320g','coop.png','fette-biscottate-integrali.png',4,'sebastianochiari8@gmail.com'),(40,'Macine','Mulino Bianco, Macine 350g','mulino-bianco.png','macine.png',4,'sebastianochiari8@gmail.com'),(41,'Bastoncini con Omega 3','I bastoncini con Omega 3 sono particolarmente ricchi di Omega 3, grazie all\'utilizzo di pregiati filetti di Merluzzo d\'Alaska e l\'aggiunta di un goccio di olio di pesce','findus.png','bastoncini-con-omega-3.png',5,'sebastianochiari8@gmail.com'),(42,'Bastoncini di Merluzzo','I bastoncini di merluzzo sono fonte naturale di selenio, che contribuisce alla normale normale funzione del sistema immunitario','findus.png','bastoncini-di-merluzzo-findus.png',5,'sebastianochiari8@gmail.com'),(43,'Gnocchetti alla sorrentina','4 salti in padella Findus, gnocchetti alla sorrentina pomodoro, basilico, mozzarella, 550g','findus.png','gnocchi-sorrentina.png',5,'sebastianochiari8@gmail.com'),(44,'Alette di pollo','Sadia, speed pollo alette mexico, 300g','sadia.png','alette.png',5,'sebastianochiari8@gmail.com'),(45,'Pizza margherita','Buitoni, pizza margherita, surgelata (1 pizza) 300g','buitoni.png','margherita.png',5,'sebastianochiari8@gmail.com'),(46,'Piselli','4 salti in padella Findus, piselli fantasia 450g','findus.png','piselli.png',5,'sebastianochiari8@gmail.com'),(47,'Lasagne alla bolognese','4 salti in padella Findus, lasagne alla bolognese 600g','findus.png','lasagne-findus.png',5,'sebastianochiari8@gmail.com'),(48,'Lasagne alla bolognese','Giovanni Rana, lasagne alla bolognese 350g','giovanni-rana.png','lasagne-rana.png',5,'sebastianochiari8@gmail.com'),(49,'Pizza al tonno','Cameo Ristorante, pizza al tonno 355g','cameo.png','tonno.png',5,'sebastianochiari8@gmail.com'),(50,'Patate fritte','Coop, patate da friggere surgelate 750g','coop.png','patate-fritte.png',5,'sebastianochiari8@gmail.com'),(51,'Anticalcare','Coop, anticalcare spray 750 ml','coop.png','anticalcare.png',6,'sebastianochiari8@gmail.com'),(52,'Viakal','Viakal classico anticalcare spray 700 ml','viakal.png','viakal.png',6,'sebastianochiari8@gmail.com'),(53,'Detergente WC','Coop, detergente WC gel disincrostante 750 ml','coop.png','detergente-wc.png',6,'sebastianochiari8@gmail.com'),(54,'Detersivo pavimenti','Coop, detersivo pavimenti fior di limone 1 L','coop.png','detersivo-pavimenti.png',6,'sebastianochiari8@gmail.com'),(55,'Guanti in lattice','Coop, guanti in lattice monouso 50 pz','coop.png','guanti-lattice.png',6,'sebastianochiari8@gmail.com'),(56,'Paletta','Paletta alza immondizia','coop.png','paletta.png',6,'sebastianochiari8@gmail.com'),(57,'Spugne abrasive','Coop, spugne abrasive 2 pz','coop.png','spugne.png',6,'sebastianochiari8@gmail.com'),(58,'Chanteclair','Chanteclair sgrassatore universale disinfettante 750 ml','chanteclair.png','chanteclair.png',6,'sebastianochiari8@gmail.com'),(59,'Swiffer','Swiffer ricarica 20 panni','swiffer.png','swiffer.png',6,'sebastianochiari8@gmail.com'),(60,'Scopa','Tonkita, ricambio testa scopa, spazio per interni','coop.png','scopa.png',6,'sebastianochiari8@gmail.com'),(61,'Trapano avvitatore','Trapano avvitatore con percussione Black & Decker EGBL188KB, 18V 1,5 AH, 2 batterie','black-&-decker.png','trapano-avvitatore.png',7,'sebastianochiari8@gmail.com'),(62,'Giraviti','Set giraviti da 5 pezzi Dexter','dexter.png','giraviti.png',7,'sebastianochiari8@gmail.com'),(63,'Pinza a becco','Pinza a becco Dexter acciaio 160mm','dexter.png','pinza.png',7,'sebastianochiari8@gmail.com'),(64,'Set di lime','Set di lime assortite Dexter 200mm','dexter.png','lime.png',7,'sebastianochiari8@gmail.com'),(65,'Miniseghetto','Miniseghetto Dexter 150mm','dexter.png','miniseghetto.png',7,'sebastianochiari8@gmail.com'),(66,'Martello carpentiere','Martello carpentiere Kapriol 250g','leroy-merlin.png','martello.png',7,'sebastianochiari8@gmail.com'),(67,'Levigatrice orbitale','Levigatrice orbitale Dexter 200 W','dexter.png','levigatrice.png',7,'sebastianochiari8@gmail.com'),(68,'Set 8 chiavi fisse a forchetta','Set 8 chiavi fisse a forchetta','leroy-merlin.png','chiavi.png',7,'sebastianochiari8@gmail.com'),(69,'Trapano a filo','Trapano a filo Bosch Universal Impact 700. Set 15 pezzi per foratura e avvitatura incluso, 700 W','bosch.png','trapano-filo.png',7,'sebastianochiari8@gmail.com'),(70,'Corona perforatrice','Corona perforatrice a tazza, diametro 125mm','leroy-merlin.png','corona.png',7,'sebastianochiari8@gmail.com'),(71,'Viti per legno','Viti per legno per lastra coppo rosso ∅ 6 x 90 mm, confezione da 25 pezzi','leroy-merlin.png','viti.png',8,'sebastianochiari8@gmail.com'),(72,'Kit di fissaggio','Kit di fissaggio Standers 20 pz ∅ 8 x 34 mm','leroy-merlin.png','fissaggio.png',8,'sebastianochiari8@gmail.com'),(73,'Rivetti Rame Mauer','Rivetti testa rame Mauer ∅ 2,8 x 7 mm','leroy-merlin.png','rivetti.png',8,'sebastianochiari8@gmail.com'),(74,'Dado autobloccante','Dado autobloccante ∅ 10 mm','leroy-merlin.png','autobloccante.png',8,'sebastianochiari8@gmail.com'),(75,'Secchiello tasselli','Secchiello tasselli Standers nylon ∅ 6 x 60 mm','leroy-merlin.png','tasselli.png',8,'sebastianochiari8@gmail.com'),(76,'Valigetta viti assortite','Valigetta viti assortite 5 x 60 mm, confezione da 900 pezzi','leroy-merlin.png','valigetta.png',8,'sebastianochiari8@gmail.com'),(77,'Supporto TV','Supporto per TV universale','leroy-merlin.png','supporto-tv.png',8,'sebastianochiari8@gmail.com'),(78,'Sgabello','Sgabello in acciaio 3 gradini','leroy-merlin.png','scala.png',8,'sebastianochiari8@gmail.com'),(79,'Maniglia per porta','Maniglia per porta con rosetta e bocchetta Albane in acciaio inox','leroy-merlin.png','maniglia.png',8,'sebastianochiari8@gmail.com'),(80,'Ruota girevole','Ruota gomma grigio ∅ 40 mm','leroy-merlin.png','ruota.png',8,'sebastianochiari8@gmail.com'),(81,'Copriauto antipolvere','Copriauto antipolvere da garage Starter','beps.png','copriauto.png',9,'sebastianochiari8@gmail.com'),(82,'Addittivo pulitore turbocompressore','Addittivo pulitore turbocompressore Turbo Cleaner','beps.png','turbo.png',9,'sebastianochiari8@gmail.com'),(83,'Addittivo assorbi acqua','Addittivo assorbi acqua Dry Fuel','beps.png','assorbi-acqua.png',9,'sebastianochiari8@gmail.com'),(84,'Pulitore iniettori diesel','Pulitore iniettori diesel Addittivo Diesel','beps.png','diesel.png',9,'sebastianochiari8@gmail.com'),(85,'Addittivo olio motore','Addittivo olio motore','beps.png','olio.png',9,'sebastianochiari8@gmail.com'),(86,'Profumi da appendere Arbre Magique','Profumi da appendere Arbre Magique New Car','beps.png','profumi.png',9,'sebastianochiari8@gmail.com'),(87,'Pulitore cerchi Hot Rims','Pulitore cerchi Hot Rims - All wheel cleaner','beps.png','pulitore-cerchi.png',9,'sebastianochiari8@gmail.com'),(88,'Profumi da bocchetta d\'aria','Profumi da bocchetta aria POP','beps.png','bocchetta.png',9,'sebastianochiari8@gmail.com'),(89,'Cruscotto pulitore Smash','Cruscotto pulitore Smash lucido','beps.png','cruscotto.png',9,'sebastianochiari8@gmail.com'),(90,'Lucida gomme','Lucida gomme nero gomme','beps.png','gomme.png',9,'sebastianochiari8@gmail.com'),(91,'Rasaerba a scoppio Sterwins','Rasaerba a scoppio Sterwins 510 HSPV 160','sterwins.png','rasaerba-scoppio.png',10,'sebastianochiari8@gmail.com'),(92,'Robot rasaerba Worx','Robot rasaerba Worx landroid S500','worx.png','robot.png',10,'sebastianochiari8@gmail.com'),(93,'Rasaerba elettrico Bosch','Rasaerba elettrico Bosch ARM 3200','bosch.png','rasaerba-elettrico.png',10,'sebastianochiari8@gmail.com'),(94,'Idropulitrice acqua fredda Karcher','Idropulitrice acqua fredda Karcher K 2 full control','leroy-merlin.png','idropulitrice.png',10,'sebastianochiari8@gmail.com'),(95,'Barbecue a carbonella','Barbecue a carbonella Naterial Astings','leroy-merlin.png','barbecue-carbonella.png',10,'sebastianochiari8@gmail.com'),(96,'Barbecue a gas','Barbecue a gas Naterial Alton 3 bruciatori','leroy-merlin.png','barbecue-gas.png',10,'sebastianochiari8@gmail.com'),(97,'Traliccio estensibile Treplas','Traliccio estensibile Treplas','leroy-merlin.png','traliccio.png',10,'sebastianochiari8@gmail.com'),(98,'Cuccia Dog House','Cuccia Dog House - Grande','leroy-merlin.png','cuccia.png',10,'sebastianochiari8@gmail.com'),(99,'Erba sintetica pretagliata','Erba sintetica pretagliata Zante L 5 m x H 1 m, spessore 20 mm','leroy-merlin.png','erba.png',10,'sebastianochiari8@gmail.com'),(100,'Elettrosega Sterwins','Elettrosega Sterwins ECS2-35.3','sterwins.png','elettrosega.png',10,'sebastianochiari8@gmail.com'),(101,'Pantaloni','Amazon essentials Slim-fit Casual Stretch Khaki, pantalone Uomo','amazon.png','pantaloni.png',11,'sebastianochiari8@gmail.com'),(102,'Athletic underwear','Athletic underwear with iPhone pockets','amazon.png','athletic.png',11,'sebastianochiari8@gmail.com'),(103,'Maglietta','Maglietta Uomo Game of Thrones','got.png','maglietta.png',11,'sebastianochiari8@gmail.com'),(104,'Calzini','Calzini People Socks Uomo / Donna, Merino wool','amazon.png','calzini.png',11,'sebastianochiari8@gmail.com'),(105,'Maglione','Amazon Essentials maglione Uomo','amazon.png','maglione.png',11,'sebastianochiari8@gmail.com'),(106,'Asciugamano in microfibra','Asciugamano in microfibra perfetto per sport, viaggio e spiaggia - super assorbente, ultra compatto','amazon.png','asciugamano.png',11,'sebastianochiari8@gmail.com'),(107,'Cappello','Berretto cappello The Simpsons','simpsons.png','cappello.png',11,'sebastianochiari8@gmail.com'),(108,'Cintura','Cintura Beltox finta pelle','amazon.png','cintura.png',11,'sebastianochiari8@gmail.com'),(109,'Cappello con frontino','Top level baseball cap per Uomo','amazon.png','baseball.png',11,'sebastianochiari8@gmail.com'),(110,'Scarpe da corsa','Asics Gel-Scram per Uomo','asics.png','scarpe.png',11,'sebastianochiari8@gmail.com'),(111,'Coca Cola','Coca-Cola regular bottiglia di plastica 1350ml x2','coca-cola.png','coca-cola.png',12,'sebastianochiari8@gmail.com'),(112,'Pepsi','Pepsi 2 x 1,5 L','pepsi.png','pepsi.png',12,'sebastianochiari8@gmail.com'),(113,'Fanta','Fanta original bottiglia di plastica 660 ml x 4','fanta.png','fanta.png',12,'sebastianochiari8@gmail.com'),(114,'Sprite','Sprite gazzosa gusto limone bottiglia di plastica da 1,5 L','sprite.png','sprite.png',12,'sebastianochiari8@gmail.com'),(115,'Red Bull','Ti mette le aaaaaaaliiiiiiii','red-bull.png','redbull.png',12,'sebastianochiari8@gmail.com'),(116,'Beck\'s','Beck\'s 6 x 33 cl','becks.png','becks.png',12,'sebastianochiari8@gmail.com'),(117,'Vodka alla menta','Keglevich with pure vodka & pure taste menta 0,7 L','vodka.png','vodka.png',12,'sebastianochiari8@gmail.com'),(118,'Amaro Montenegro','Amaro Montenegro 70 cl','montenegro.png','amaro.png',12,'sebastianochiari8@gmail.com'),(119,'Succo ACE','Coop, Succo arancia, carota e limone 1000 ml','coop.png','ace.png',12,'sebastianochiari8@gmail.com'),(120,'Birra Moretti','Birra Moretti Baffo d\'oro 3 x 33 cl','moretti.png','moretti.png',12,'sebastianochiari8@gmail.com'),(121,'Runaway, Passenger','Musica pop, musica d\'autore, 2018','music.png','passenger.png',13,'sebastianochiari8@gmail.com'),(122,'Brother in Arms, Dire Straits','Blues rock, 1985','music.png','dire-straits.png',13,'sebastianochiari8@gmail.com'),(123,'Toto IV, Toto','Pop-rock, hard rock, fusion, 1982','music.png','toto.png',13,'sebastianochiari8@gmail.com'),(124,'Highway to Hell, AC/DC','Heavy metal, hard rock, 1979','music.png','acdc.png',13,'sebastianochiari8@gmail.com'),(125,'Hill Climber, Vulfpeck','Musica pop, 2018','music.png','vulfpeck.png',13,'sebastianochiari8@gmail.com'),(126,'Tutti su per terra, Eugenio in Via di Gioia','Alternative, indie, 2017','music.png','eugenio.png',13,'sebastianochiari8@gmail.com'),(127,'Gioventù Brucata, Pinguini Tattici Nucleari','Alternative, indie, 2017','music.png','ptn.png',13,'sebastianochiari8@gmail.com'),(128,'Delta, Mumford & Sons','Alternative, indie, 2018','music.png','mum.png',13,'sebastianochiari8@gmail.com'),(129,'X (deluxe edition), Ed Sheeran','Pop, 2014','music.png','ed.png',13,'sebastianochiari8@gmail.com'),(130,'In Absentia, Porcupine Tree','Progressive rock, alternative rock, 2002','music.png','porcupine.png',13,'sebastianochiari8@gmail.com'),(131,'Benagol','Combatti il mal di gola, in caso di gola irritata','farmacia.png','benagol.png',14,'sebastianochiari8@gmail.com'),(132,'Lenigola','Lenigola spray forte 20 ml','farmacia.png','lenigola.png',14,'sebastianochiari8@gmail.com'),(133,'Tachipirina','Tachipirina sciroppo 120 ml','farmacia.png','tachipirina.png',14,'sebastianochiari8@gmail.com'),(134,'Aspirina','Asprina, contro dolore, infiammazione, influenza','farmacia.png','aspirina.png',14,'sebastianochiari8@gmail.com'),(135,'Moment Act','Moment Act 400mg 10 capsule molli','farmacia.png','moment.png',14,'sebastianochiari8@gmail.com'),(136,'Tachifludec','Tachifludec, contro il raffreddore','farmacia.png','tachifludec.png',14,'sebastianochiari8@gmail.com'),(137,'Voltaren','Voltaren emulgel, antinfiammatorio muscolare','farmacia.png','voltaren.png',14,'sebastianochiari8@gmail.com'),(138,'Fastum Gel','Fastum Gel, antidolorifico','farmacia.png','fastum.png',14,'sebastianochiari8@gmail.com'),(139,'Oki','Oki, farmaco antinfiammatorio','farmacia.png','oki.png',14,'sebastianochiari8@gmail.com'),(140,'Listerine','Listerin, colluttorio per l\'igiene orale','farmacia.png','listerin.png',14,'sebastianochiari8@gmail.com'),(141,'Kinder Delice','Kinder Delice 10 x 39g','kinder.png','delice.png',15,'sebastianochiari8@gmail.com'),(142,'Pringles','Pringles Original 165g','pringles.png','pringles.png',15,'sebastianochiari8@gmail.com'),(143,'Fonzies','Fonzies 9 x 23,5g','fonzies.png','fonzies.png',15,'sebastianochiari8@gmail.com'),(144,'Mars','Mars 6 x 45g','mars.png','mars.png',15,'sebastianochiari8@gmail.com'),(145,'Togo','Pavesi Togo Classic Latte 120g','pavesi.png','togo.png',15,'sebastianochiari8@gmail.com'),(146,'Fiesta','Ferrero Fiesta Classica 10 x 36g','ferrero.png','fiesta.png',15,'sebastianochiari8@gmail.com'),(147,'Kinder Bueno','Kinder Bueno 6 x 43g','kinder.png','bueno.png',15,'sebastianochiari8@gmail.com'),(148,'Kinder Pinguì','Kinder Pinguì Cioccolato 8 x 30g','kinder.png','pingu.png',15,'sebastianochiari8@gmail.com'),(149,'Kinder Sorpresa','Kinder Sorpresa 20g','kinder.png','sorpresa.png',15,'sebastianochiari8@gmail.com'),(150,'Amica Chips','Amica Chips la Grigliata 200g','amica-chips.png','chips.png',15,'sebastianochiari8@gmail.com'),(151,'Penna','Bic Crystal Original, punta ad ago 0.7mm, inchiostro blu, nero e rosso','bic.png','penna.png',16,'sebastianochiari8@gmail.com'),(152,'Gomma','Staedtler, confezione da 3 gomme Mars plastic mini','staedtler.png','gomma.png',16,'sebastianochiari8@gmail.com'),(153,'Matita','Stabilo, set 4 matite in grafite 160Hb con gommino','stabilo.png','matite.png',16,'sebastianochiari8@gmail.com'),(154,'Evidenziatori','Set 2 evidenziatori Stabilo Boss Original Giallo','stabilo.png','evidenziatore.png',16,'sebastianochiari8@gmail.com'),(155,'Colla','Pritt, colla stick, senza solventi e PVC','pritt.png','colla.png',16,'sebastianochiari8@gmail.com'),(156,'Post-it','Cubo 450 foglietti 76x76mm','post-it.png','post.png',16,'sebastianochiari8@gmail.com'),(157,'Bianchetto','Correttore a penna Papermate 7ml','papermate.png','bianchetto.png',16,'sebastianochiari8@gmail.com'),(158,'Forbice','Forbice dalla punta arrotondata, con impugnatura morbida e lame in titanio, 20 cm','coop.png','forbice.png',16,'sebastianochiari8@gmail.com'),(159,'Acquerelli','Giotto acquerelli in 24 colori','giotto.png','acquerelli.png',16,'sebastianochiari8@gmail.com'),(160,'Pennarelli','Pennarelli Stabilo Pen 68 Color Parade, scatola da 20, colore brillante e luminoso','stabilo.png','pennarelli.png',16,'sebastianochiari8@gmail.com'),(161,'North Face Borealis','Compatibile con laptop fino a 15\"','north-face.png','borealis.png',17,'sebastianochiari8@gmail.com'),(162,'North Face Surge','Compatibile con laptop fino a 15\"','north-face.png','surge.png',17,'sebastianochiari8@gmail.com'),(163,'FjÃ¤llrÃ¤ven KÃ¥nken','Classic KÃ¥nken backpack','fjallraven.png','kanken.png',17,'sebastianochiari8@gmail.com'),(164,'Element Mohave','Element Mohave 30L backpack','element.png','element.png',17,'sebastianochiari8@gmail.com'),(165,'Quechua NH500','Zaino Quechua NH500 30L verde oliva','quechua.png','quechua.png',17,'sebastianochiari8@gmail.com'),(166,'Wandrd Prvke','Wandrd Prvke 21L backpack','wandrd.png','wandrd.png',17,'sebastianochiari8@gmail.com'),(167,'Peak Design Everyday','Peak Design Everyday Backpack 20L','peak-design.png','peak.png',17,'sebastianochiari8@gmail.com'),(168,'EastPak Padded','EastPak Padded Pak\'r Black','eastpak.png','eastpak.png',17,'sebastianochiari8@gmail.com'),(169,'Manfrotto Africa','Zaino e monospalla Manfrotto Africa canvas-cuoio','manfrotto.png','manfrotto.png',17,'sebastianochiari8@gmail.com'),(170,'Invicta Jolly','Zaino Invicta Jolly III Vintage','invicta.png','invicta.png',17,'sebastianochiari8@gmail.com'),(171,'Quaderno a quadretti 4mm','Quaderno Pigna Monocromo a quadretti 4mm','pigna.png','4mm.png',18,'sebastianochiari8@gmail.com'),(172,'Quaderno a righe','Quaderno One Color a righe','school.png','righe.png',18,'sebastianochiari8@gmail.com'),(173,'Quaderno a quadretti 5mm','Quaderno Pigna Monocromo a quadretti 5mm','pigna.png','5mm.png',18,'sebastianochiari8@gmail.com'),(174,'Quaderno a quadretti 10mm','Quaderno Pigna Monocromo a quadretti 10mm','pigna.png','10mm.png',18,'sebastianochiari8@gmail.com'),(175,'Raccoglitore ad anelli','Raccoglitore ad anelli Pigna, A4','pigna.png','anelli.png',18,'sebastianochiari8@gmail.com'),(176,'Quaderno con fogli bianchi','Quaderno Pigna Monocromo con fogli bianchi','pigna.png','bianchi.png',18,'sebastianochiari8@gmail.com'),(177,'Buste trasparenti','Esselte busta trasparente a foratura universale, A4','school.png','trasparenti.png',18,'sebastianochiari8@gmail.com'),(178,'Pacco quaderni','PIGNA quaderni monocromo\" quadretto 4mm (10 pezzi)\"','pigna.png','pacco.png',18,'sebastianochiari8@gmail.com'),(179,'Ricambi a righe','Ricambi rinforzati PIGNARIC con banda trasparente, righe','pigna.png','ricambi-righe.png',18,'sebastianochiari8@gmail.com'),(180,'Ricambi a quadretti','Ricambi rinforzati POOL100 con fascia trasparente, quadretti 5mm','school.png','ricambi-quadretti.png',18,'sebastianochiari8@gmail.com'),(181,'Calcolatrice standard','Calcolatrice standard desktop con display a 12 cifre','elettronica.png','standard.png',19,'sebastianochiari8@gmail.com'),(182,'Calcolatrice scientifica','Calcolatrice scientifica Sharp EL501XBWH','elettronica.png','scientifica.png',19,'sebastianochiari8@gmail.com'),(183,'Calcolatrice Texas','Calcolatrice Texas TI-89 titanium','elettronica.png','texas.png',19,'sebastianochiari8@gmail.com'),(184,'MacBook Pro','MacBook Pro 15\", Space Gray, 2.2GHz Intel i7 processor, 16Gb DDR4 memory, Radeon Pro 555X with 4Gb DDR5 memory, 256Gb SSD storage','apple.png','mac.png',19,'sebastianochiari8@gmail.com'),(185,'Asus VivoBook','ASUS VivoBook 15 X510UF, Intel Core i5 processor, 16Gb RAM, NVIDIA GeForce MX130 graphics','asus.png','vivobook.png',19,'sebastianochiari8@gmail.com'),(186,'Dell Inspiron','Dell Inspiron 15 5570, Intel Core i7 processor, 8Gb RAM DDR4','dell.png','inspiron.png',19,'sebastianochiari8@gmail.com'),(187,'Asus ZenBook','ASUS ZenBook UX430 UQ, 13\", Intel Core i7 processor, 16Gb RAM DDR4, 512Gb SSD storage','asus.png','zenbook.png',19,'sebastianochiari8@gmail.com'),(188,'MSI Apache','MSI GE62 Apache, Intel Core i7 processor, 8Gb DDR4 memory, NVIDIA GeForce GTX 965M GPU','msi.png','msi.png',19,'sebastianochiari8@gmail.com'),(189,'Acer Swift 7','Acer Swift 7, Intel Core i7 processor, 8Gb DDR3 memory, 256Gb SSD storage','acer.png','acer.png',19,'sebastianochiari8@gmail.com'),(190,'Microsoft Surface Pro 6','Microsoft Surface Pro 6, Intel Core i5 processor, 8Gb memory, 128Gb SSD storage','microsoft.png','surface.png',19,'sebastianochiari8@gmail.com'),(191,'Diario Smemoranda','Diario Smemoranda 16 mesi 13 x17 ','school.png','smemoranda.png',20,'sebastianochiari8@gmail.com'),(192,'Diario Comix','Diario Comix Classic black & white, se fa ridere è Comix','school.png','comix.png',20,'sebastianochiari8@gmail.com'),(193,'Diario Bastardi Dentro','Diario Bastardi Dentro standard 16 mesi','school.png','bastardi.png',20,'sebastianochiari8@gmail.com'),(194,'Diario ScuolaZoo','Diario ScuolaZoo standard 16 mesi','school.png','scuolazoo.png',20,'sebastianochiari8@gmail.com'),(195,'Diario Winx','Diario scuola 10 mesi Winx (Flora) - dimensioni 15x20cm','school.png','winx.png',20,'sebastianochiari8@gmail.com'),(196,'Diario Minnie','Diario scuola Auguri Preziosi MN907','school.png','minnie.png',20,'sebastianochiari8@gmail.com'),(197,'Diario Ben 10','Ben 10 diario scuola 10 mesi, formato standard','school.png','ben10.png',20,'sebastianochiari8@gmail.com'),(198,'Diario Smile','Smiley 53137 Diario, 12 mesi, Viola/Arancione','school.png','smile.png',20,'sebastianochiari8@gmail.com'),(199,'Diario Be You','Diario scuola Be You Arancio','school.png','beyou.png',20,'sebastianochiari8@gmail.com'),(200,'Moleskine','Moleskine Taccuino Classico, copertina flessibile a righe, XL, nero','school.png','moleskine.png',20,'sebastianochiari8@gmail.com');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sharing`
--

DROP TABLE IF EXISTS `sharing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sharing` (
  `Email` varchar(250) NOT NULL,
  `LID` int(11) NOT NULL,
  `Modify` tinyint(1) DEFAULT '0',
  `AddRemProd` tinyint(1) DEFAULT '0',
  `DeleteList` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`Email`,`LID`),
  KEY `sharing_LID` (`LID`),
  CONSTRAINT `sharing_Email` FOREIGN KEY (`Email`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sharing_LID` FOREIGN KEY (`LID`) REFERENCES `lists` (`lid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sharing`
--

LOCK TABLES `sharing` WRITE;
/*!40000 ALTER TABLE `sharing` DISABLE KEYS */;
INSERT INTO `sharing` VALUES ('gianni.morandi@alice.it',5,1,1,0),('giorgiosgl@gmail.com',3,0,1,0),('giovanni.muciacia@gmail.com',2,1,1,0),('giovanni.muciacia@gmail.com',4,0,1,0),('leonardoremondini@gmail.com',3,0,1,0),('marta.toniolli@gmail.com',3,0,1,0),('qwer@tom.tom',11,1,1,0),('sebastiano.chiari@gmail.com',3,1,1,0),('sebastiano.chiari@gmail.com',4,0,1,0),('tommaso.bosetti@gmail.com',4,0,1,0);
/*!40000 ALTER TABLE `sharing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sharing_products`
--

DROP TABLE IF EXISTS `sharing_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sharing_products` (
  `Email` varchar(250) NOT NULL,
  `PID` int(11) NOT NULL,
  PRIMARY KEY (`Email`,`PID`),
  KEY `productPID_idx` (`PID`),
  CONSTRAINT `productPID` FOREIGN KEY (`PID`) REFERENCES `products` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userEmail` FOREIGN KEY (`Email`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sharing_products`
--

LOCK TABLES `sharing_products` WRITE;
/*!40000 ALTER TABLE `sharing_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `sharing_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `Email` varchar(250) NOT NULL,
  `Password` varchar(250) NOT NULL,
  `Name` varchar(250) NOT NULL,
  `Surname` varchar(250) NOT NULL,
  `Avatar` varchar(250) DEFAULT NULL,
  `Admin` tinyint(1) DEFAULT '0',
  `List_Owner` tinyint(1) DEFAULT '0',
  `Confirmed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('aaa@cercasi.cercasi','bad762b4162f40eae2c909cd2833bbc0309978561d1297edb2121185322132cb','Donne','DuDuDu','icon4.png',0,1,1),('britney.spears@hotmail.it','5d80e63deaaf1ade33b19e461ace5f834437f2facc730a5adc54993a84717c83','Britney','Spears','8.png',0,1,1),('gianni.morandi@alice.it','dd68750e8d21ae8969f915b91cef0c1270b9812ec73bbad42167f95478704952','Gianni','Morandi','4.png',0,1,1),('giorgiosgl@gmail.com','3da3992c261db7ad508953c4d7e2701b508938602c7783eaec9e0d59b492c827','Giorgio','Segalla','icon3.png',0,1,1),('giovanni.muciacia@gmail.com','9eb6d03c2edd963e630f47bd56ba59842bc4747e16c9a49aa7612551c919874f','Giovanni','Muciacia','icon0.png',0,1,1),('leonardoremondini@gmail.com','f7aa6cd779253cb7c2667ae445adff94d83f4a63afb936a53cacec54b723dee3','Leonardo','Remondini','1.png',0,1,1),('marta.toniolli@gmail.com','2968ae57208de4b49068a417d520548eb9ecde4aca3bdfe08b6505c125ecbae2','Marta','Toniolli','10.png',0,1,1),('pippo.baudo@rai.tv.it','c82dd4d1b85f0c5f6fd107f5de65b4dd25d265812e504aca67d3356f9e0f40dd','Pippo','Baudo','5.png',0,1,1),('qwer@tom.tom','d0bb76a550b87bd9ec2e8aefbe838948b9d5c63915c01912cb5a3127577ce779','Ariana','Grande','icon1.png',0,1,1),('romeocarta@yahoo.it','458bf0271edb299f64beeb23e43232abcdc62dc8daaec0233931cea8d91ae9d9','Romeo','Carta','2.png',0,0,1),('sebastiano.chiari@gmail.com','d9ca43d142e06c25ae462892515b7d5787d3b71e582c92cc0286a75cc25b00ab','Sebastiano','Chiari','icon1.png',0,1,1),('sebastianochiari8@gmail.com','passw0rd','Sebastiano','Chiari',NULL,1,0,1),('tommaso.bosetti@gmail.com','fddffb3bb70a1961e98fa80f0a30158915db6387b6d23646662301dec5b5d3a1','Tommaso','Bosetti','3.png',1,1,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-14 13:57:44
