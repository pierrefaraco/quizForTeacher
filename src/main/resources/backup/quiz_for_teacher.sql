-- MySQL dump 10.13  Distrib 5.7.14, for Win64 (x86_64)
--
-- Host: localhost    Database: quiz_for_teacher
-- ------------------------------------------------------
-- Server version	5.7.14-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cours`
--

DROP TABLE IF EXISTS `cours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cours` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `description` varchar(2048) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bkc1u8xxgpc77rpfb4xgmdhue` (`user_id`),
  CONSTRAINT `FK_bkc1u8xxgpc77rpfb4xgmdhue` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cours`
--

LOCK TABLES `cours` WRITE;
/*!40000 ALTER TABLE `cours` DISABLE KEYS */;
INSERT INTO `cours` VALUES (1,'','Objectifs pédagogiques :\nMaitriser les concepts avancés de la programmation objet en Java. Apprendre à identifier et utiliser les patrons (design pattern) adaptés à une situation de conception.\nCapacités et compétences visées :\nMaitrise du langage Java et des concepts objets avancés cités dans le programme. Effectuer un choix de patron (pattern)','NFP121 - Programmation avancée 2016 - 2017',1);
/*!40000 ALTER TABLE `cours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cours_subscribers`
--

DROP TABLE IF EXISTS `cours_subscribers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cours_subscribers` (
  `Cours_id` bigint(20) NOT NULL,
  `suscriber_status` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`Cours_id`,`user_id`),
  KEY `FK_71lyl7is4rxn6d3q6muwr86j4` (`user_id`),
  CONSTRAINT `FK_23knmo5ehlh5volf834ht8heq` FOREIGN KEY (`Cours_id`) REFERENCES `cours` (`id`),
  CONSTRAINT `FK_71lyl7is4rxn6d3q6muwr86j4` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cours_subscribers`
--

LOCK TABLES `cours_subscribers` WRITE;
/*!40000 ALTER TABLE `cours_subscribers` DISABLE KEYS */;
INSERT INTO `cours_subscribers` VALUES (1,1,1),(1,1,2184),(1,1,5785);
/*!40000 ALTER TABLE `cours_subscribers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_answers`
--

DROP TABLE IF EXISTS `question_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_answers` (
  `Question_id` bigint(20) NOT NULL,
  `answers` bit(1) NOT NULL,
  `answers_KEY` varchar(255) NOT NULL,
  PRIMARY KEY (`Question_id`,`answers_KEY`),
  CONSTRAINT `FK_juhc82do1iii0qx5d64v8j38i` FOREIGN KEY (`Question_id`) REFERENCES `questions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_answers`
--

LOCK TABLES `question_answers` WRITE;
/*!40000 ALTER TABLE `question_answers` DISABLE KEYS */;
INSERT INTO `question_answers` VALUES (1,'\0','Collectionneurs'),(1,'','Comportementaux'),(1,'','Créateurs'),(1,'\0','Diviseurs'),(1,'','Structurels'),(2,'','définit le squelette d\'un algorithme à l\'aide d\'opérations abstraites dont le comportement concret se trouvera dans les sous-classes, qui implémenteront ces opérations'),(2,'\0','Fournit un mandataire au client afin de contrôler/vérifier ses accès'),(2,'\0','Permettre à son utilisateur de parcourir le conteneur, tout en isolant l\'utilisateur de la structure interne du conteneur'),(3,'\0','attacher dynamiquement des responsabilités à un objet'),(3,'\0','Découpler l’émission d\'une requête de la réception'),(3,'','Manipuler facilement des objets complexes en les reliants selon une structure en arbre'),(4,'\0','Chain of responsibility'),(4,'\0','Decorator'),(4,'\0','Façade'),(4,'','Interpreter'),(17358,'\0','0'),(17358,'\0','1'),(17358,'','false'),(17358,'\0','n’est pas initialisée'),(17358,'\0','true'),(17359,'\0','0'),(17359,'\0','1'),(17359,'','false'),(17359,'\0','n’est pas initialisée'),(17359,'\0','true'),(17360,'\0','16 bits'),(17360,'','32 bits'),(17360,'\0','64 bits'),(17360,'\0','8 bits'),(17361,'\0','16 bits'),(17361,'\0','32 bits'),(17361,'','64 bits'),(17361,'\0','8 bits'),(17362,'\0','affiche : Object'),(17362,'','affiche : String'),(17362,'\0','lance une RuntimeException'),(17362,'\0','ne compile pas'),(17363,'\0','affiche : String'),(17363,'\0','affiche : StringBuffer'),(17363,'\0','lance une RuntimeException'),(17363,'','ne compile pas'),(17364,'','affiche : 12'),(17364,'\0','affiche : 12345'),(17364,'\0','affiche : 1245'),(17365,'\0','affiche : 12'),(17365,'\0','affiche : 12345'),(17365,'','affiche : 1245'),(17366,'','la boucle 1 est préférable à la boucle 2'),(17366,'\0','la boucle 2 est préférable à la boucle 1'),(17366,'\0','les deux boucles sont strictement équivalentes'),(17367,'','la boucle 1 est préférable à la boucle 2'),(17367,'\0','la boucle 2 est préférable à la boucle 1'),(17367,'\0','les deux boucles sont strictement équivalentes'),(17368,'\0','run, stop'),(17368,'\0','run, suspend'),(17368,'','start, stop'),(17368,'\0','start, suspend'),(17369,'\0','demander à un thread d’attendre la terminaison d’un thread particulier'),(17369,'\0','La  déclaration d’un attribut sous la forme « synchronized public int attribut1 ; » est légale.'),(17369,'','Le mot clé synchronized permet de poser un verrou sur un objet pour gérer une exclusivité entre plusieurs threads.'),(17370,'\0','Java permet de gérer nativement des programmes contenant plusieurs processus tournant au  sein d’une même machine virtuelle.'),(17370,'','Java permet de gérer nativement des programmes contenant plusieurs threads tournant au  sein d’une même machine virtuelle.  2'),(17371,'\0','La méthode notify permet de réveiller un thread endormi par l’appel à sleep.'),(17371,'','La méthode sleep  de la classe Thread a pour objectif d’endormir un thread.'),(17371,'\0','Une attente active est préférable à une attente passive.'),(17372,'\0','La méthode notifyAll permet de rendre exécutables tous les threads présents dans la liste d’attente d’un objet.'),(17372,'','La méthode wait de la classe Thread permet à un thread de libérer le verrou de l’objet synchronisé et de se placer dans la liste d’attente associée à l’objet.'),(17372,'','Un thread correspondant à un traitement long et coûteux en CPU doit avoir une priorité faible.'),(17373,'\0','Une méthode « synchronized public static void m2() » pose un verrou sur l’objet correspondant à la classe où elle est définie.'),(17373,'\0','Une méthode « synchronized public void m1() » ne peut être exécutée au même moment par deux threads sur deux instances de la classe où elle est défini'),(17373,'','Une méthode « synchronized public void m1() » pose un verrou sur l’objet correspondant à « this ».'),(17374,'','Le classpath peut contenir des fichiers jar et des répertoires'),(17374,'\0','Lors du lancement d’un programme java, il est possible de préciser quelle méthode on souhaite exécuter.'),(17374,'','Lors du lancement d’un programme java, il faut préciser le nom du fichier .class que l’on souhaite exécuter'),(17374,'','Un fichier jar est un fichier archive contenant les fichiers compilés java'),(18069,'\0','La technologie dom permet de créer un document xml.'),(18069,'','La technologie dom permet de parcourir un document xml.'),(18069,'\0','La technologie sax permet de créer un document xml.'),(18069,'','La technologie sax permet de parcourir un document xml.'),(18070,'','La technologie dtd permet d’analyser un document xml à l’aide d’une grammaire.'),(18070,'\0','Les balises d’un document xml peuvent se chevaucher. <a><b>eert</a></b> est légal'),(18070,'','Un élément doit encapsuler tous les autres'),(18070,'','Un namespace sert à créer des synonymes au sein d’un fichier xml'),(18071,'','< !--bla bla --> est un commentaire dans un fichier xml'),(18071,'','La déclaration d’une dtd peut être interne ou externe.'),(18071,'','Le prologue d’un fichier xml permet de préciser l’encoding du document'),(18072,'','La technologie xml est normalisée.'),(18072,'\0','La technologie xml est spécifique au langage java.'),(18072,'','La technologie xml permet la représentation de données sous forme d’un arbre'),(18420,'\0','affiche : 123456'),(18420,'\0','affiche : 124365'),(18420,'','affiche : 142356'),(18420,'\0','lance une Runtime error'),(18420,'\0','ne compile pas'),(18420,'\0','n’affiche rien'),(18421,'','affiche : 1'),(18421,'\0','lance une Runtime error'),(18421,'\0','ne compile pas'),(18421,'\0','n’affiche rien'),(18422,'\0','affiche : 13'),(18422,'\0','affiche : 31'),(18422,'\0','lance une Runtime error'),(18422,'','ne compile pas'),(18422,'\0','n’affiche rien'),(18423,'','affiche : 13'),(18423,'\0','affiche : 31'),(18423,'\0','lance une Runtime error'),(18423,'\0','ne compile pas'),(18423,'\0','n’affiche rien'),(18424,'\0','affiche : 1'),(18424,'','affiche : 2'),(18424,'\0','lance une Runtime error'),(18424,'\0','ne compile pas'),(18424,'\0','n’affiche rien'),(18425,'\0','affiche : 2'),(18425,'\0','lance une Runtime error'),(18425,'','ne compile pas'),(18425,'\0','n’affiche rien'),(18426,'','affiche : 2'),(18426,'\0','lance une Runtime error'),(18426,'\0','ne compile pas'),(18426,'\0','n’affiche rien'),(18427,'','affiche : 1'),(18427,'\0','lance une Runtime error'),(18427,'\0','ne compile pas'),(18428,'','affiche : 1'),(18428,'\0','lance une Runtime error'),(18428,'\0','ne compile pas'),(18429,'\0','affiche : 1'),(18429,'\0','lance une Runtime error'),(18429,'','ne compile pas'),(18430,'','affiche : 10'),(18430,'\0','lance une Runtime error'),(18430,'\0','ne compile pas'),(18430,'\0','n’affiche rien'),(18431,'','affiche : 10'),(18431,'\0','lance une Runtime error'),(18431,'\0','ne compile pas'),(18431,'\0','n’affiche rien'),(18432,'\0','boolean b1 = 3 instanceof  Integer;'),(18432,'','boolean b2 = new Integer(3) instanceof Integer;'),(18432,'\0','boolean b3 = Integer instanceof class;'),(18432,'\0','boolean b4 = Integer instanceof Class;'),(18433,'\0','affiche : 0'),(18433,'','lance une Runtime error'),(18433,'\0','ne compile pas'),(18433,'\0','n’affiche rien'),(18434,'\0','affiche : 1'),(18434,'\0','lance une Runtime error'),(18434,'\0','ne compile car la ligne double d1 = i; est invalide'),(18434,'','ne compile car la ligne Double d2 = i; est invalide'),(18434,'\0','n’affiche rien'),(18435,'\0','affiche : ok'),(18435,'','lance une Runtime error'),(18435,'\0','ne compile pas'),(18435,'\0','n’affiche rien'),(18436,'\0','affiche : ok'),(18436,'\0','lance une Runtime error'),(18436,'','ne compile pas'),(18436,'\0','n’affiche rien'),(18437,'','affiche : ok'),(18437,'\0','lance une Runtime error'),(18437,'\0','ne compile pas'),(18437,'\0','n’affiche rien'),(18438,'\0','float method(int i) et int method(int i) est une surcharge légale'),(18438,'','int method(int i) et float method(float f) est une surcharge légale.'),(18438,'','Les méthodes surchargées peuvent provenir de l\'héritage d\'une superclasse.'),(18438,'','Si deux méthodes d’une même classe possèdent un nom semblable mais différentes signatures, le nom de ces méthodes est dit surchargé.'),(18439,'\0','compile'),(18439,'','ne compile pas'),(18440,'\0','affiche : 1'),(18440,'','affiche : 2'),(18440,'\0','lance une Runtime error'),(18440,'\0','ne compile pas'),(18440,'\0','n’affiche rien');
/*!40000 ALTER TABLE `question_answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `points` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  `question` varchar(2048) DEFAULT NULL,
  `question_type` int(11) NOT NULL,
  `time_to_answer` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `topic_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pepxlg46pfgd0oues38b05vu4` (`topic_id`),
  CONSTRAINT `FK_pepxlg46pfgd0oues38b05vu4` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18441 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,2,0,NULL,0,10,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',2),(2,2,0,NULL,0,15,'Quel est le but du \"Template methode pattern\" ?',2),(3,2,0,NULL,0,15,'Quel est le but du pattern composite ?',2),(4,2,0,NULL,0,15,'Le patron COMPOSITE permet de créé une expression, quel autre pattern pourrai  permettre de la calculer.',2),(17358,2,0,NULL,0,15,'Quelle est la valeur par défaut d’un attribut de classe de type « boolean ?',53906),(17359,2,0,NULL,0,15,'Quelle est la valeur par défaut d’une variable java de type « boolean » ?',53906),(17360,2,0,NULL,0,15,'Quelle est la taille mémoire occupée par une valeur de type int en Java ?',53906),(17361,2,0,NULL,0,15,'Quelle est la taille mémoire occupée par une valeur de type long en Java ?',53906),(17362,2,0,'package org.cnam.package1;\n\npublic class Test {\n	static void method(Object obj) {\n		System.out.println(\"Object\");\n	}\n\n	static void method(String str) {\n		System.out.println(\"String\");\n	}\n\n	public static void main(String args[]) {\n		method(null);\n	}\n}',0,45,'Le programme suivant :',53906),(17363,2,0,'package org.cnam.package1; \n\npublic class Test {\n	static void method(StringBuffer obj) {\n		System.out.println(\"StringBuffer\");\n	}\n\n	static void method(String str) {\n		System.out.println(\"String\");\n	}\n\n	public static void main(String args[]) {\n		method(null);\n	}\n}',0,45,'Le programme suivant :',53906),(17364,2,0,'package org.cnam.package1; \npublic class Test {\n	public void m1() {\n		int m = 5;\n		int i = 0;\n		while (i < m) {\n			i++;\n			if (i == 3) {\n				break;\n			}\n			System.out.print(i);\n		}\n	}\n\n	public static void main(String args[]) {\n		new Test().m1();\n	}\n}',0,45,'Le programme suivant :',53906),(17365,2,0,'package org.cnam.package1;   \npublic class Test {\n	public void m1() {\n		int m = 5;\n		int i = 0;\n		while (i < m) {\n			i++;\n			if (i == 3) {\n				continue;\n			}\n			System.out.print(i);\n		}\n	}\n\n	public static void main(String args[]) {\n		new Test().m1();\n	}\n}',0,45,'Le programme suivant :',53906),(17366,2,0,'package org.cnam.package1;\nimport java.util.ArrayList;\nimport java.util.Iterator;\nimport java.util.List;\n\npublic class Test {\n	public void m1() {\n		List<Integer> l = new ArrayList<Integer>();\n		for (int i = 0; i < 1000; i++) {\n			l.add(i);\n		} \n		// boucle1\n		int i = 0;\n		double t = System.nanoTime();\n		while (i < l.size()) {\n			Integer integer = l.get(i);\n			i++;\n		} \n		// boucle2\n		Iterator<Integer> it = l.iterator();\n		while (it.hasNext()) {\n			Integer integer = it.next();\n		}\n	}\n\n	public static void main(String args[]) {\n		new Test().m1();\n	}\n}',0,45,'Dans le programme suivant :',53906),(17367,2,0,'package org.cnam.package1; \nimport java.util.ArrayList;\nimport java.util.Iterator;\nimport java.util.List;\n\npublic class Test {\n	public void m1() {\n		List<Integer> l = new ArrayList<Integer>();\n		for (int i = 0; i < 1000; i++) {\n			l.add(i);\n		}\n		// boucle1\n		int i = 0;\n		double t = System.nanoTime();\n		while (i < l.size()) {\n			Integer integer = l.get(i);\n			i++;\n		}\n		// boucle2\n		for (Integer integer : l) {\n		}\n	}\n\n	public static void main(String args[]) {\n		new Test().m1();\n	}\n}',0,45,'Dans le programme suivant :',53906),(17368,2,0,NULL,0,15,'Les bonnes méthodes pour démarrer et arrêter les Threads sont :',53906),(17369,2,0,NULL,0,15,'Le mot clé synchronized permet de :',53906),(17370,2,0,NULL,0,15,'Parmi les affirmations suivantes, lesquelles sont vraies ?',53906),(17371,2,0,NULL,0,15,'Parmi les affirmations suivantes, lesquelles sont vraies :',53906),(17372,2,0,NULL,0,15,'Parmi les affirmations suivantes, lesquelles sont vraies :',53906),(17373,2,0,NULL,0,15,'Parmi les affirmations suivantes, lesquelles sont vraies :',53906),(17374,2,0,NULL,0,15,'Parmi les affirmations suivantes concernant java.exe, lesquelles sont vraies :',53906),(18069,2,0,NULL,0,15,'Parmi les affirmations suivantes, lesquelles sont vraies ?',53906),(18070,2,0,NULL,0,15,'Parmi les affirmations suivantes, lesquelles sont vraies',53906),(18071,2,0,NULL,0,15,'Parmi les affirmations suivantes, lesquelles sont vraies :',53906),(18072,2,0,NULL,0,15,'Parmi les affirmations suivantes, lesquelles sont vraies :',53906),(18420,2,0,'//Fichier Sup.java : \npackage org.cnam.package1;\n\npublic class Sup {\n	static {\n		System.out.println(\"1\");\n	}\n	{\n		System.out.println(\"2\");\n	}\n\n	public Sup(int i) {\n		System.out.println(\"3\");\n	}\n}\n\n//Fichier Test.java : \npackage org.cnam.package1;\npublic class Test extends Sup {\n	static {\n		System.out.println(\"4\");\n	}\n	{\n		System.out.println(\"5\");\n	}\n\n	public Test(int i) {\n		super(i);\n		System.out.println(\"6\");\n	}\n\n	public static void main(String[] args) {\n		Test t = new Test(1);\n	}\n}',0,15,'Un programme comporte les deux fichiers suivants  affiche :',53904),(18421,2,0,'package org.cnam.package1;\n\npublic class Test {\n	public Test(int i) {\n		super();\n		System.out.println(\"1\");\n	}\n\n	public static void main(String[] args) {\n		Test t = new Test(1);\n	}\n}',0,15,'Le programme suivant :',53904),(18422,2,0,'//Fichier Test.java \npackage org.cnam.package1;\n\npublic class Sup {\n	public Sup() {\n		System.out.println(\"1\");\n	}\n}\n\npublic class Test extends Sup {\n	public Test() {\n		super();\n		System.out.println(\"3\");\n	}\n\n	public static void main(String[] args) {\n		Test t = new Test();\n	}\n}',0,15,'Un programme comporte le fichier suivant :',53904),(18423,2,0,'package org.cnam.package1;\n\nclass Sup {\n	public Sup() {\n		System.out.println(\"1\");\n	}\n}\n\npublic class Test extends Sup {\n	public Test() {\n		super();\n		System.out.println(\"3\");\n	}\n\n	public static void main(String[] args) {\n		Test t = new Test();\n	}\n}',0,15,'Un programme comporte le fichier suivant :',53904),(18424,2,0,'package org.cnam.package1;\n\npublic class Test {\n	public void method() {\n		System.out.println(\"1\");\n	}\n\n	static public void main(String[] args) {\n		Test t = new Test() {\n			public void method() {\n				System.out.println(\"2\");\n			}\n		};\n		t.method();\n	}\n}',0,15,'Le programme suivant :',53904),(18425,2,0,'package org.cnam.package1;\n\nclass Test {\n	static public void main(String[] args) {\n		Object t = new Object() {\n			public void method() {\n				System.out.println(\"2\");\n			}\n		};\n		t.method();\n	}\n}',0,15,'Le programme suivant :',53904),(18426,2,0,'package org.cnam.package1;\n\npublic class Test {\n	static public void main(String[] args) {\n		Object t = new Object() {\n			public String toString() {\n				return \"2\";\n			}\n		};\n		System.out.println(t);\n	}\n}',0,15,'Le programme suivant :',53904),(18427,2,0,'package org.cnam.package1;\n\nclass Test1 {\n	static public class Interne {\n		public void method() {\n			System.out.println(\"1\");\n		}\n	}\n}\n\npublic class Test {\n	static public void main(String[] args) {\n		Test1.Interne i = new Test1.Interne();\n		i.method();\n	}\n}',0,15,'Le programme suivant :',53904),(18428,2,0,'package org.cnam.package1;\n\npublic class Test {\n	private int attribut;\n\n	class Interne {\n		public void method2() {\n			System.out.println(attribut);\n		}\n	}\n\n	public void method() {\n		attribut = 1;\n		new Interne().method2();\n	}\n\n	static public void main(String[] args) {\n		Test t = new Test();\n		t.method();\n	}\n}',0,15,'Le programme suivant :',53904),(18429,2,0,'package org.cnam.package1;\n\npublic class Test {\n	private int attribut;\n\n	static class Interne {\n		public void method2() {\n			System.out.println(attribut);\n		}\n	}\n\n	public void method() {\n		attribut = 1;\n		new Interne().method2();\n	}\n\n	static public void main(String[] args) {\n		Test t = new Test();\n		t.method();\n	}\n}',0,15,'Le programme suivant :',53904),(18430,2,0,'package org.cnam.package1;\n\npublic class Test {\n	public static void main(String[] args) {\n		Integer myint1 = new Integer(10);\n		int myint2 = myint1;\n		System.out.println(myint2);\n	}\n}',0,15,'Le programme suivant :',53904),(18431,2,0,'package org.cnam.package1;\n\npublic class Test {\n	public static void main(String[] args) {\n		int myint1 = 10;\n		Integer myint2 = myint1;\n		System.out.println(myint2);\n	}\n}',0,15,'Le programme suivant :',53904),(18432,2,0,NULL,0,15,'Parmi les instructions suivantes lesquels sont valides :',53904),(18433,2,0,'package org.cnam.package1;\n\npublic class Test {\n	public static void main(String[] args) {\n		Integer myint1 = null;\n		int myint2 = myint1;\n		System.out.println(myint2);\n	}\n}',0,15,'Le programme suivant :',53904),(18434,2,0,'package org.cnam.package1;\n\npublic class Test {\n	public static void main(String[] args) {\n		int i = 10;\n		double d1 = i;\n		Double d2 = i ;\n		System.out.print(1);\n	}\n}',0,15,'Le programme suivant :',53904),(18435,2,0,'package org.cnam.package1;\n\npublic class Test {\n	public static void main(String[] args) {\n		Object myobj1 = new Object();\n		Test myobj2 = (Test) myobj1;\n		System.out.println(\"ok\");\n	}\n}',0,15,'Le programme suivant :',53904),(18436,2,0,'package org.cnam.package1;\n\npublic class Test {\n	public static void main(String[] args) {\n		Object myobj1 = new Object();\n		Test myobj2 = myobj1;\n		System.out.println(\"ok\");\n	}\n}',0,15,'Le programme suivant :',53904),(18437,2,0,'package org.cnam.package1;\n\npublic class Test {\n	public static void main(String[] args) {\n		Test myobj1 = new Test();\n		Object myobj2 = myobj1;\n		System.out.println(\"ok\");\n	}\n}',0,15,'Le programme suivant :',53904),(18438,2,0,NULL,0,15,'Parmi les affirmations suivantes, lesquelles sont valides ?',53904),(18439,2,0,'package org.cnam.package1;\n\nclass Test1 {\n	public int method(int i) {\n		return 1;\n	}\n}\n\npublic class Test extends Test1 {\n	public float method(int i) {\n		return 1;\n	}\n}',0,15,'Le programme suivant :',53904),(18440,2,0,'package org.cnam.package1;\n\nclass Test1 {\n	public void method() {\n		System.out.println(\"1\");\n	}\n}\n\npublic class Test extends Test1 {\n	public void method() {\n		System.out.println(\"2\");\n	}\n\n	static public void main(String[] args) {\n		Test1 t = new Test();\n		t.method();\n	}\n}',0,15,'Le programme suivant :',53904);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result_answers`
--

DROP TABLE IF EXISTS `result_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `result_answers` (
  `Result_id` bigint(20) NOT NULL,
  `answers` tinyblob NOT NULL,
  `answers_KEY` varchar(255) NOT NULL,
  PRIMARY KEY (`Result_id`,`answers_KEY`),
  CONSTRAINT `FK_6tu3hfg26s06tucvefoin4rfo` FOREIGN KEY (`Result_id`) REFERENCES `results` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result_answers`
--

LOCK TABLES `result_answers` WRITE;
/*!40000 ALTER TABLE `result_answers` DISABLE KEYS */;
INSERT INTO `result_answers` VALUES (1,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(1,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(1,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(1,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(1,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(2,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(2,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(2,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(2,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(2,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(3,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(3,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Comportementaux'),(3,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Créateurs'),(3,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(3,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Structurels'),(4,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(4,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(4,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Créateurs'),(4,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(4,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(5,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(5,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(5,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(5,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(5,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(6,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Chain of responsibility'),(6,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Decorator'),(6,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Façade'),(6,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Interpreter'),(7,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(7,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(7,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(7,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(7,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(8,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(8,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Comportementaux'),(8,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Créateurs'),(8,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(8,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Structurels'),(3739,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(3739,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(3739,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(3739,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(3739,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(5906,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(5906,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(5906,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(5906,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(5906,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Structurels'),(5907,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(5907,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(5907,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(5907,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(5907,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(6630,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(6630,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Comportementaux'),(6630,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Créateurs'),(6630,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(6630,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Structurels'),(12407,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(12407,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(12407,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(12407,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(12407,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(13130,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(13130,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(13130,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(13130,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(13130,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(13131,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(13131,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(13131,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(13131,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(13131,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(13132,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Collectionneurs'),(13132,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(13132,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Créateurs'),(13132,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Diviseurs'),(13132,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Structurels'),(13133,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(13133,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Comportementaux'),(13133,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Créateurs'),(13133,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(13133,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Structurels'),(16022,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','attacher dynamiquement des responsabilités à un objet'),(16022,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Découpler l’émission d\'une requête de la réception'),(16022,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Manipuler facilement des objets complexes en les reliants selon une structure en arbre'),(16745,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(16745,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(16745,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(16745,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(16745,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(16746,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(16746,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(16746,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(16746,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(16746,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(16747,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(16747,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(16747,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(16747,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(16747,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(16748,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Collectionneurs'),(16748,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(16748,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Créateurs'),(16748,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Diviseurs'),(16748,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Structurels'),(17471,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(17471,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(17471,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(17471,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(17471,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(17472,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(17472,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(17472,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(17472,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(17472,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(17473,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(17473,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(17473,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(17473,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(17473,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(17474,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(17474,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(17474,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(17474,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(17474,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(17475,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','attacher dynamiquement des responsabilités à un objet'),(17475,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Découpler l’émission d\'une requête de la réception'),(17475,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Manipuler facilement des objets complexes en les reliants selon une structure en arbre'),(17476,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','attacher dynamiquement des responsabilités à un objet'),(17476,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Découpler l’émission d\'une requête de la réception'),(17476,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Manipuler facilement des objets complexes en les reliants selon une structure en arbre'),(18199,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(18199,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(18199,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(18199,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(18199,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(20366,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(20366,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Comportementaux'),(20366,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Créateurs'),(20366,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(20366,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Structurels'),(20367,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(20367,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(20367,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(20367,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(20367,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(20368,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(20368,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(20368,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(20368,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(20368,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(29033,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(29033,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(29033,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(29033,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(29033,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(29756,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(29756,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Comportementaux'),(29756,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Créateurs'),(29756,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(29756,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Structurels'),(36255,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(36255,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Comportementaux'),(36255,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Créateurs'),(36255,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(36255,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Structurels'),(36978,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Collectionneurs'),(36978,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Comportementaux'),(36978,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Créateurs'),(36978,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Diviseurs'),(36978,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Structurels'),(36979,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','0'),(36979,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','1'),(36979,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','false'),(36979,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','n’est pas initialisée'),(36979,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','true'),(36980,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','affiche : 12'),(36980,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','affiche : 12345'),(36980,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','affiche : 1245'),(36981,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','affiche : 12'),(36981,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','affiche : 12345'),(36981,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','affiche : 1245'),(36982,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','0'),(36982,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','1'),(36982,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','false'),(36982,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','n’est pas initialisée'),(36982,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','true'),(36983,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','0'),(36983,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','1'),(36983,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','false'),(36983,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','n’est pas initialisée'),(36983,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','true'),(36984,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','0'),(36984,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','1'),(36984,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','false'),(36984,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','n’est pas initialisée'),(36984,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','true'),(36985,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','0'),(36985,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','1'),(36985,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','false'),(36985,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','n’est pas initialisée'),(36985,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','true'),(36986,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','0'),(36986,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','1'),(36986,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','false'),(36986,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','n’est pas initialisée'),(36986,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','true'),(36987,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','0'),(36987,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','1'),(36987,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','false'),(36987,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','n’est pas initialisée'),(36987,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','true'),(36988,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','0'),(36988,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','1'),(36988,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','false'),(36988,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','n’est pas initialisée'),(36988,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','true'),(36989,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','0'),(36989,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','1'),(36989,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','false'),(36989,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','n’est pas initialisée'),(36989,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','true'),(36990,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','0'),(36990,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','1'),(36990,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','false'),(36990,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','n’est pas initialisée'),(36990,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','true'),(36991,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','0'),(36991,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','1'),(36991,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','false'),(36991,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','n’est pas initialisée'),(36991,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','true'),(36992,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','0'),(36992,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','1'),(36992,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','false'),(36992,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','n’est pas initialisée'),(36992,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','true'),(36993,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','0'),(36993,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','1'),(36993,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','false'),(36993,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','n’est pas initialisée'),(36993,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','true'),(36994,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','0'),(36994,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','1'),(36994,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','false'),(36994,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','n’est pas initialisée'),(36994,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','true'),(36995,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','0'),(36995,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','1'),(36995,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','false'),(36995,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','n’est pas initialisée'),(36995,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','true'),(36996,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','0'),(36996,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','1'),(36996,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','false'),(36996,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','n’est pas initialisée'),(36996,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','true'),(36997,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','la boucle 1 est préférable à la boucle 2'),(36997,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','la boucle 2 est préférable à la boucle 1'),(36997,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','les deux boucles sont strictement équivalentes'),(36998,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','16 bits'),(36998,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','32 bits'),(36998,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','64 bits'),(36998,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','8 bits'),(36999,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','16 bits'),(36999,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','32 bits'),(36999,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','64 bits'),(36999,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','8 bits'),(37000,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','16 bits'),(37000,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','32 bits'),(37000,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','64 bits'),(37000,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','8 bits'),(37001,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','0'),(37001,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','1'),(37001,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','false'),(37001,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','n’est pas initialisée'),(37001,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','true'),(37002,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','0'),(37002,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','1'),(37002,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','false'),(37002,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','n’est pas initialisée'),(37002,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','true'),(37003,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','demander à un thread d’attendre la terminaison d’un thread particulier'),(37003,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','La  déclaration d’un attribut sous la forme « synchronized public int attribut1 ; » est légale.'),(37003,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Le mot clé synchronized permet de poser un verrou sur un objet pour gérer une exclusivité entre plusieurs threads.'),(37004,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','demander à un thread d’attendre la terminaison d’un thread particulier'),(37004,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','La  déclaration d’un attribut sous la forme « synchronized public int attribut1 ; » est légale.'),(37004,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Le mot clé synchronized permet de poser un verrou sur un objet pour gérer une exclusivité entre plusieurs threads.'),(37005,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Java permet de gérer nativement des programmes contenant plusieurs processus tournant au  sein d’une même machine virtuelle.'),(37005,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Java permet de gérer nativement des programmes contenant plusieurs threads tournant au  sein d’une même machine virtuelle.  2'),(37006,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Java permet de gérer nativement des programmes contenant plusieurs processus tournant au  sein d’une même machine virtuelle.'),(37006,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','Java permet de gérer nativement des programmes contenant plusieurs threads tournant au  sein d’une même machine virtuelle.  2'),(37007,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','affiche : String'),(37007,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','affiche : StringBuffer'),(37007,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','lance une RuntimeException'),(37007,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','ne compile pas'),(37008,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','affiche : String'),(37008,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','affiche : StringBuffer'),(37008,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','lance une RuntimeException'),(37008,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','ne compile pas'),(37009,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','affiche : Object'),(37009,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','affiche : String'),(37009,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','lance une RuntimeException'),(37009,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','ne compile pas'),(37010,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','affiche : Object'),(37010,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0','affiche : String'),(37010,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','lance une RuntimeException'),(37010,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','ne compile pas'),(37011,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Le classpath peut contenir des fichiers jar et des répertoires'),(37011,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Lors du lancement d’un programme java, il est possible de préciser quelle méthode on souhaite exécuter.'),(37011,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Lors du lancement d’un programme java, il faut préciser le nom du fichier .class que l’on souhaite exécuter'),(37011,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Un fichier jar est un fichier archive contenant les fichiers compilés java'),(37012,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Le classpath peut contenir des fichiers jar et des répertoires'),(37012,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0\0','Lors du lancement d’un programme java, il est possible de préciser quelle méthode on souhaite exécuter.'),(37012,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Lors du lancement d’un programme java, il faut préciser le nom du fichier .class que l’on souhaite exécuter'),(37012,'�\�\0ur\0[ZW� 9�]\�\0\0xp\0\0\0\0','Un fichier jar est un fichier archive contenant les fichiers compilés java');
/*!40000 ALTER TABLE `result_answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `results`
--

DROP TABLE IF EXISTS `results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `results` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `answer_time` float DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `obtained_points` int(11) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `question_Id` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `sessionQuiz_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ohu7qg9hlt23cctl241mo70hc` (`sessionQuiz_id`),
  KEY `FK_d7soxd7rbc10nfhd83h2hx2qe` (`user_id`),
  CONSTRAINT `FK_d7soxd7rbc10nfhd83h2hx2qe` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_ohu7qg9hlt23cctl241mo70hc` FOREIGN KEY (`sessionQuiz_id`) REFERENCES `session_quiz` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37013 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `results`
--

LOCK TABLES `results` WRITE;
/*!40000 ALTER TABLE `results` DISABLE KEYS */;
INSERT INTO `results` VALUES (1,8006,'2016-08-20 20:14:22',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',1,1),(2,3098,'2016-08-21 11:39:48',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',1,1),(3,1185,'2016-08-21 11:48:13',0,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',1,1),(4,6207,'2016-08-21 11:48:22',0,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',1,1),(5,3201,'2016-08-21 11:48:40',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',1,1),(6,4825,'2016-08-21 11:49:07',2,2,NULL,4,'Le patron COMPOSITE permet de créé une expression, quel autre pattern pourrai  permettre de la calculer.',1,1),(7,3013,'2016-08-21 16:58:56',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',1,1),(8,1074,'2016-08-21 17:22:15',0,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',1,1),(3739,4609,'2016-08-22 22:01:18',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(5906,4895,'2016-08-22 22:34:13',0,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(5907,2930,'2016-08-22 22:55:10',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(6630,1957,'2016-08-27 10:32:55',0,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(12407,10000,'2016-08-27 11:17:52',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(13130,6496,'2016-08-27 11:25:53',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(13131,4023,'2016-08-27 11:25:53',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,2184),(13132,8268,'2016-08-27 11:33:02',0,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(13133,10000,'2016-08-27 11:33:03',0,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,2184),(16022,7066,'2016-08-27 11:46:21',2,2,NULL,3,'Quel est le but du pattern composite ?',111,1),(16745,6779,'2016-08-27 12:02:42',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(16746,5611,'2016-08-27 12:02:42',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,2184),(16747,10000,'2016-08-27 14:48:12',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(16748,7290,'2016-08-27 14:48:12',0,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,2184),(17471,10000,'2016-08-27 14:52:36',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(17472,10000,'2016-08-27 14:52:50',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(17473,6329,'2016-08-27 14:53:19',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(17474,3667,'2016-08-27 14:53:19',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,2184),(17475,7793,'2016-08-27 15:25:10',2,2,NULL,3,'Quel est le but du pattern composite ?',111,1),(17476,6533,'2016-08-27 15:25:10',2,2,NULL,3,'Quel est le but du pattern composite ?',111,2184),(18199,3681,'2016-08-27 17:13:36',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(20366,1049,'2016-08-27 17:34:53',0,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(20367,4647,'2016-08-27 17:35:55',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(20368,6955,'2016-08-27 17:35:55',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,2184),(29033,2682,'2016-08-28 14:43:38',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(29756,2759,'2016-08-28 15:39:19',2,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(36255,2206,'2016-08-28 16:03:24',0,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(36978,10000,'2016-08-30 22:07:59',0,2,NULL,1,'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?',111,1),(36979,11276,'2016-08-30 22:57:24',2,2,NULL,17358,'Quelle est la valeur par défaut d’un attribut de classe de type « boolean ?',133,1),(36980,45000,'2016-08-30 22:58:22',0,2,'package org.cnam.package1;   \npublic class Test {\n	public void m1() {\n		int m = 5;\n		int i = 0;\n		while (i < m) {\n			i++;\n			if (i == 3) {\n				continue;\n			}\n			System.out.print(i);\n		}\n	}\n\n	public static void main(String args[]) {\n		new Test().m1();\n	}\n}',17365,'Le programme suivant :',133,1),(36981,24282,'2016-08-30 23:12:19',2,2,'package org.cnam.package1;   \npublic class Test {\n	public void m1() {\n		int m = 5;\n		int i = 0;\n		while (i < m) {\n			i++;\n			if (i == 3) {\n				continue;\n			}\n			System.out.print(i);\n		}\n	}\n\n	public static void main(String args[]) {\n		new Test().m1();\n	}\n}',17365,'Le programme suivant :',133,1),(36982,15000,'2016-08-30 23:12:37',2,2,NULL,17359,'Quelle est la valeur par défaut d’une variable java de type « boolean » ?',133,1),(36983,5806,'2016-08-30 23:12:56',2,2,NULL,17358,'Quelle est la valeur par défaut d’un attribut de classe de type « boolean ?',133,1),(36984,5291,'2016-08-30 23:18:40',0,2,NULL,17358,'Quelle est la valeur par défaut d’un attribut de classe de type « boolean ?',133,1),(36985,7598,'2016-08-30 23:20:47',0,2,NULL,17358,'Quelle est la valeur par défaut d’un attribut de classe de type « boolean ?',133,1),(36986,5664,'2016-08-30 23:21:18',2,2,NULL,17358,'Quelle est la valeur par défaut d’un attribut de classe de type « boolean ?',133,1),(36987,3068,'2016-08-30 23:27:27',2,2,NULL,17358,'Quelle est la valeur par défaut d’un attribut de classe de type « boolean ?',133,1),(36988,3210,'2016-08-30 23:28:12',0,2,NULL,17358,'Quelle est la valeur par défaut d’un attribut de classe de type « boolean ?',133,1),(36989,2788,'2016-08-30 23:29:31',0,2,NULL,17358,'Quelle est la valeur par défaut d’un attribut de classe de type « boolean ?',133,1),(36990,1548,'2016-08-30 23:31:17',0,2,NULL,17358,'Quelle est la valeur par défaut d’un attribut de classe de type « boolean ?',133,1),(36991,4355,'2016-08-30 23:32:16',2,2,NULL,17358,'Quelle est la valeur par défaut d’un attribut de classe de type « boolean ?',133,1),(36992,3772,'2016-08-30 23:33:13',2,2,NULL,17358,'Quelle est la valeur par défaut d’un attribut de classe de type « boolean ?',133,1),(36993,2914,'2016-08-30 23:33:47',2,2,NULL,17359,'Quelle est la valeur par défaut d’une variable java de type « boolean » ?',133,1),(36994,3796,'2016-08-30 23:35:42',2,2,NULL,17358,'Quelle est la valeur par défaut d’un attribut de classe de type « boolean ?',133,1),(36995,2017,'2016-08-30 23:36:33',2,2,NULL,17358,'Quelle est la valeur par défaut d’un attribut de classe de type « boolean ?',133,1),(36996,2233,'2016-08-30 23:37:14',2,2,NULL,17358,'Quelle est la valeur par défaut d’un attribut de classe de type « boolean ?',133,1),(36997,9900,'2016-08-30 23:38:05',2,2,'package org.cnam.package1;\nimport java.util.ArrayList;\nimport java.util.Iterator;\nimport java.util.List;\n\npublic class Test {\n	public void m1() {\n		List<Integer> l = new ArrayList<Integer>();\n		for (int i = 0; i < 1000; i++) {\n			l.add(i);\n		} \n		// boucl',17366,'Dans le programme suivant :',133,1),(36998,5975,'2016-08-30 23:42:34',2,2,NULL,17360,'Quelle est la taille mémoire occupée par une valeur de type int en Java ?',133,1),(36999,6770,'2016-08-30 23:43:45',2,2,NULL,17360,'Quelle est la taille mémoire occupée par une valeur de type int en Java ?',133,1),(37000,3893,'2016-08-30 23:43:45',2,2,NULL,17360,'Quelle est la taille mémoire occupée par une valeur de type int en Java ?',133,2184),(37001,2985,'2016-08-30 23:44:36',2,2,NULL,17358,'Quelle est la valeur par défaut d’un attribut de classe de type « boolean ?',133,1),(37002,7926,'2016-08-30 23:44:36',2,2,NULL,17358,'Quelle est la valeur par défaut d’un attribut de classe de type « boolean ?',133,2184),(37003,15000,'2016-08-30 23:45:00',2,2,NULL,17369,'Le mot clé synchronized permet de :',133,1),(37004,15000,'2016-08-30 23:45:00',2,2,NULL,17369,'Le mot clé synchronized permet de :',133,2184),(37005,9609,'2016-08-30 23:45:19',2,2,NULL,17370,'Parmi les affirmations suivantes, lesquelles sont vraies ?',133,1),(37006,11483,'2016-08-30 23:45:19',2,2,NULL,17370,'Parmi les affirmations suivantes, lesquelles sont vraies ?',133,2184),(37007,23601,'2016-08-30 23:46:11',2,2,'package org.cnam.package1; \n\npublic class Test {\n	static void method(StringBuffer obj) {\n		System.out.println(\"StringBuffer\");\n	}\n\n	static void method(String str) {\n		System.out.println(\"String\");\n	}\n\n	public static void main(String args[]) {\n		method(nul',17363,'Le programme suivant :',133,1),(37008,38541,'2016-08-30 23:46:11',2,2,'package org.cnam.package1; \n\npublic class Test {\n	static void method(StringBuffer obj) {\n		System.out.println(\"StringBuffer\");\n	}\n\n	static void method(String str) {\n		System.out.println(\"String\");\n	}\n\n	public static void main(String args[]) {\n		method(nul',17363,'Le programme suivant :',133,2184),(37009,29317,'2016-08-30 23:47:02',2,2,'package org.cnam.package1;\n\npublic class Test {\n	static void method(Object obj) {\n		System.out.println(\"Object\");\n	}\n\n	static void method(String str) {\n		System.out.println(\"String\");\n	}\n\n	public static void main(String args[]) {\n		method(null);\n	}\n}',17362,'Le programme suivant :',133,1),(37010,32008,'2016-08-30 23:47:02',2,2,'package org.cnam.package1;\n\npublic class Test {\n	static void method(Object obj) {\n		System.out.println(\"Object\");\n	}\n\n	static void method(String str) {\n		System.out.println(\"String\");\n	}\n\n	public static void main(String args[]) {\n		method(null);\n	}\n}',17362,'Le programme suivant :',133,2184),(37011,5944,'2016-08-30 23:51:00',0,2,NULL,17374,'Parmi les affirmations suivantes concernant java.exe, lesquelles sont vraies :',133,1),(37012,10486,'2016-08-30 23:51:00',0,2,NULL,17374,'Parmi les affirmations suivantes concernant java.exe, lesquelles sont vraies :',133,2184);
/*!40000 ALTER TABLE `results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequences`
--

DROP TABLE IF EXISTS `sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequences` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(2048) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_35je0fgsblp993exrtr4m8i4u` (`user_id`),
  CONSTRAINT `FK_35je0fgsblp993exrtr4m8i4u` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequences`
--

LOCK TABLES `sequences` WRITE;
/*!40000 ALTER TABLE `sequences` DISABLE KEYS */;
INSERT INTO `sequences` VALUES (1,NULL,'Sequence 1',1);
/*!40000 ALTER TABLE `sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequences_questions`
--

DROP TABLE IF EXISTS `sequences_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequences_questions` (
  `sequences_id` bigint(20) NOT NULL,
  `questions_id` bigint(20) NOT NULL,
  `questions_KEY` int(11) NOT NULL,
  PRIMARY KEY (`sequences_id`,`questions_KEY`),
  KEY `FK_hma0o7x0ciqn6lil5iv0emewd` (`questions_id`),
  CONSTRAINT `FK_7wf710uvf74ik9mc4cj98333y` FOREIGN KEY (`sequences_id`) REFERENCES `sequences` (`id`),
  CONSTRAINT `FK_hma0o7x0ciqn6lil5iv0emewd` FOREIGN KEY (`questions_id`) REFERENCES `questions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequences_questions`
--

LOCK TABLES `sequences_questions` WRITE;
/*!40000 ALTER TABLE `sequences_questions` DISABLE KEYS */;
INSERT INTO `sequences_questions` VALUES (1,17358,0),(1,17359,1),(1,17360,2),(1,17361,3),(1,17362,4),(1,17363,5),(1,17364,6),(1,17365,7),(1,17366,8),(1,17367,9),(1,17368,10),(1,17369,11),(1,17370,12),(1,17371,13),(1,17372,14),(1,17373,15),(1,17374,16),(1,18069,17),(1,18070,18),(1,18071,19),(1,18072,20);
/*!40000 ALTER TABLE `sequences_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session_quiz`
--

DROP TABLE IF EXISTS `session_quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_quiz` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `cours_id` bigint(20) NOT NULL,
  `sequence_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cdmwhv9a0bocythvdq75wrkgo` (`cours_id`),
  KEY `FK_ew1j133f6y7d4r1bifkf6rgc9` (`sequence_id`),
  CONSTRAINT `FK_cdmwhv9a0bocythvdq75wrkgo` FOREIGN KEY (`cours_id`) REFERENCES `cours` (`id`),
  CONSTRAINT `FK_ew1j133f6y7d4r1bifkf6rgc9` FOREIGN KEY (`sequence_id`) REFERENCES `sequences` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session_quiz`
--

LOCK TABLES `session_quiz` WRITE;
/*!40000 ALTER TABLE `session_quiz` DISABLE KEYS */;
INSERT INTO `session_quiz` VALUES (1,'2016-08-21 17:22:19','2016-08-20 20:14:10',1,1,1),(111,'2016-08-30 22:56:19','2016-08-22 22:01:05',1,1,1),(133,NULL,'2016-08-30 22:57:08',0,1,1);
/*!40000 ALTER TABLE `session_quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topics`
--

DROP TABLE IF EXISTS `topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(2048) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3xwo6yg0xh1fc5i2ilcf6vbd7` (`user_id`),
  CONSTRAINT `FK_3xwo6yg0xh1fc5i2ilcf6vbd7` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54985 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topics`
--

LOCK TABLES `topics` WRITE;
/*!40000 ALTER TABLE `topics` DISABLE KEYS */;
INSERT INTO `topics` VALUES (2,'En informatique, et plus particulièrement en développement logiciel, un patron de conception (plus souvent appelé design pattern) est un arrangement caractéristique de modules, reconnu comme bonne pratique en réponse à un problème de conception d\'un logiciel. Il décrit une solution standard, utilisable dans la conception de différents logiciels1.\n\nUn patron de conception est issu de l\'expérience des concepteurs de logiciels2. Il décrit un arrangement récurrent de rôles et d\'actions joués par des modules d\'un logiciel, et le nom du patron sert de vocabulaire commun entre le concepteur et le programmeur3. D\'une manière analogue à un motif de conception en architecture, le patron de conception décrit les grandes lignes d\'une solution, qui peuvent ensuite être modifiées et adaptées en fonction des besoins4.\n\nLes patrons de conception décrivent des procédés de conception généraux et permettent en conséquence de capitaliser l\'expérience appliquée à la conception de logiciel. Ils ont une influence sur l\'architecture logicielle d\'un système informatique.','Les designs patterns',1),(53903,NULL,'QCM1  NFP121',1),(53904,NULL,'QCM2  NFP121',1),(53905,NULL,'QCM3  NFP121',1),(53906,NULL,'QCM4 NFP121',1);
/*!40000 ALTER TABLE `topics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_type` int(11) NOT NULL,
  `birthday` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `presentation` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6026 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'1984-02-21 23:00:00','demo@cnam.fr','Jhoni','Doe','demo','Bonjour, je suis un professeur créé pour la démo.'),(2184,2,'1970-01-01 00:00:00','pierre.faraco@gmail.com','Pierre','Faraco 2','55261981',NULL),(5785,2,'1970-01-01 00:00:00','pierre.faraco@gmail.com2','dd','dd','55261981','d');
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

-- Dump completed on 2016-08-30 23:52:12
