-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 02 Août 2016 à 22:41
-- Version du serveur :  5.7.9-log
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `quiz_for_teacher`
--

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

CREATE TABLE IF NOT EXISTS `cours` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `description` varchar(2048) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bkc1u8xxgpc77rpfb4xgmdhue` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `cours`
--

INSERT INTO `cours` (`id`, `active`, `description`, `name`, `user_id`) VALUES
(1, b'1', 'Objectifs pédagogiques :\nMaitriser les concepts avancés de la programmation objet en Java. Apprendre à identifier et utiliser les patrons (design pattern) adaptés à une situation de conception.\nCapacités et compétences visées :\nMaitrise du langage Java et des concepts objets avancés cités dans le programme. Effectuer un choix de patron (pattern)', 'NFP121 - Programmation avancée 2016 - 2017', 1);

-- --------------------------------------------------------

--
-- Structure de la table `cours_subscribers`
--

CREATE TABLE IF NOT EXISTS `cours_subscribers` (
  `Cours_id` bigint(20) NOT NULL,
  `suscriber_status` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`Cours_id`,`user_id`),
  KEY `FK_71lyl7is4rxn6d3q6muwr86j4` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `questions`
--

CREATE TABLE IF NOT EXISTS `questions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `points` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  `question` varchar(2048) DEFAULT NULL,
  `question_type` int(11) NOT NULL,
  `time_to_answer` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `topic_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pepxlg46pfgd0oues38b05vu4` (`topic_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `questions`
--

INSERT INTO `questions` (`id`, `points`, `position`, `question`, `question_type`, `time_to_answer`, `title`, `topic_id`) VALUES
(1, 2, 0, NULL, 0, 10, 'Les patrons de conception sont répartis dans 3 grandes familles,  lesquels ?', 2),
(2, 2, 0, NULL, 0, 15, 'Quel est le but du "Template methode pattern" ?', 2),
(3, 2, 0, NULL, 0, 15, 'Quel est le but du pattern composite ?', 2),
(4, 2, 0, NULL, 0, 15, 'Le patron COMPOSITE permet de créé une expression, quel autre pattern pourrai  permettre de la calculer.', 2);

-- --------------------------------------------------------

--
-- Structure de la table `question_answers`
--

CREATE TABLE IF NOT EXISTS `question_answers` (
  `Question_id` bigint(20) NOT NULL,
  `answers` bit(1) NOT NULL,
  `answers_KEY` varchar(255) NOT NULL,
  PRIMARY KEY (`Question_id`,`answers_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `question_answers`
--

INSERT INTO `question_answers` (`Question_id`, `answers`, `answers_KEY`) VALUES
(1, b'0', 'Collectionneurs'),
(1, b'1', 'Comportementaux'),
(1, b'1', 'Créateurs'),
(1, b'0', 'Diviseurs'),
(1, b'1', 'Structurels'),
(2, b'1', 'définit le squelette d''un algorithme à l''aide d''opérations abstraites dont le comportement concret se trouvera dans les sous-classes, qui implémenteront ces opérations'),
(2, b'0', 'Fournit un mandataire au client afin de contrôler/vérifier ses accès'),
(2, b'0', 'Permettre à son utilisateur de parcourir le conteneur, tout en isolant l''utilisateur de la structure interne du conteneur'),
(3, b'0', 'attacher dynamiquement des responsabilités à un objet'),
(3, b'0', 'Découpler l’émission d''une requête de la réception'),
(3, b'1', 'Manipuler facilement des objets complexes en les reliants selon une structure en arbre'),
(4, b'0', 'Chain of responsibility'),
(4, b'0', 'Decorator'),
(4, b'0', 'Façade'),
(4, b'1', 'Interpreter');

-- --------------------------------------------------------

--
-- Structure de la table `results`
--

CREATE TABLE IF NOT EXISTS `results` (
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
  KEY `FK_d7soxd7rbc10nfhd83h2hx2qe` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `result_answers`
--

CREATE TABLE IF NOT EXISTS `result_answers` (
  `Result_id` bigint(20) NOT NULL,
  `answers` tinyblob NOT NULL,
  `answers_KEY` varchar(255) NOT NULL,
  PRIMARY KEY (`Result_id`,`answers_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `sequences`
--

CREATE TABLE IF NOT EXISTS `sequences` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(2048) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_35je0fgsblp993exrtr4m8i4u` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `sequences_questions`
--

CREATE TABLE IF NOT EXISTS `sequences_questions` (
  `sequences_id` bigint(20) NOT NULL,
  `questions_id` bigint(20) NOT NULL,
  `questions_KEY` int(11) NOT NULL,
  PRIMARY KEY (`sequences_id`,`questions_KEY`),
  KEY `FK_hma0o7x0ciqn6lil5iv0emewd` (`questions_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `session_quiz`
--

CREATE TABLE IF NOT EXISTS `session_quiz` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `cours_id` bigint(20) NOT NULL,
  `sequence_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cdmwhv9a0bocythvdq75wrkgo` (`cours_id`),
  KEY `FK_ew1j133f6y7d4r1bifkf6rgc9` (`sequence_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `topics`
--

CREATE TABLE IF NOT EXISTS `topics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(2048) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3xwo6yg0xh1fc5i2ilcf6vbd7` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `topics`
--

INSERT INTO `topics` (`id`, `description`, `name`, `user_id`) VALUES
(2, 'En informatique, et plus particulièrement en développement logiciel, un patron de conception (plus souvent appelé design pattern) est un arrangement caractéristique de modules, reconnu comme bonne pratique en réponse à un problème de conception d''un logiciel. Il décrit une solution standard, utilisable dans la conception de différents logiciels1.\n\nUn patron de conception est issu de l''expérience des concepteurs de logiciels2. Il décrit un arrangement récurrent de rôles et d''actions joués par des modules d''un logiciel, et le nom du patron sert de vocabulaire commun entre le concepteur et le programmeur3. D''une manière analogue à un motif de conception en architecture, le patron de conception décrit les grandes lignes d''une solution, qui peuvent ensuite être modifiées et adaptées en fonction des besoins4.\n\nLes patrons de conception décrivent des procédés de conception généraux et permettent en conséquence de capitaliser l''expérience appliquée à la conception de logiciel. Ils ont une influence sur l''architecture logicielle d''un système informatique.', 'Les designs patterns', 1);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_type` int(11) NOT NULL,
  `birthday` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `presentation` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `account_type`, `birthday`, `email`, `firstName`, `lastName`, `password`, `presentation`) VALUES
(1, 1, '1984-02-21 23:00:00', 'demo@cnam.fr', 'Jhon', 'Doe', 'demo', 'Bonjour, je suis un professeur créé pour la démo.');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `cours`
--
ALTER TABLE `cours`
  ADD CONSTRAINT `FK_bkc1u8xxgpc77rpfb4xgmdhue` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `cours_subscribers`
--
ALTER TABLE `cours_subscribers`
  ADD CONSTRAINT `FK_23knmo5ehlh5volf834ht8heq` FOREIGN KEY (`Cours_id`) REFERENCES `cours` (`id`),
  ADD CONSTRAINT `FK_71lyl7is4rxn6d3q6muwr86j4` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `FK_pepxlg46pfgd0oues38b05vu4` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`id`);

--
-- Contraintes pour la table `question_answers`
--
ALTER TABLE `question_answers`
  ADD CONSTRAINT `FK_juhc82do1iii0qx5d64v8j38i` FOREIGN KEY (`Question_id`) REFERENCES `questions` (`id`);

--
-- Contraintes pour la table `results`
--
ALTER TABLE `results`
  ADD CONSTRAINT `FK_d7soxd7rbc10nfhd83h2hx2qe` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK_ohu7qg9hlt23cctl241mo70hc` FOREIGN KEY (`sessionQuiz_id`) REFERENCES `session_quiz` (`id`);

--
-- Contraintes pour la table `result_answers`
--
ALTER TABLE `result_answers`
  ADD CONSTRAINT `FK_6tu3hfg26s06tucvefoin4rfo` FOREIGN KEY (`Result_id`) REFERENCES `results` (`id`);

--
-- Contraintes pour la table `sequences`
--
ALTER TABLE `sequences`
  ADD CONSTRAINT `FK_35je0fgsblp993exrtr4m8i4u` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `sequences_questions`
--
ALTER TABLE `sequences_questions`
  ADD CONSTRAINT `FK_7wf710uvf74ik9mc4cj98333y` FOREIGN KEY (`sequences_id`) REFERENCES `sequences` (`id`),
  ADD CONSTRAINT `FK_hma0o7x0ciqn6lil5iv0emewd` FOREIGN KEY (`questions_id`) REFERENCES `questions` (`id`);

--
-- Contraintes pour la table `session_quiz`
--
ALTER TABLE `session_quiz`
  ADD CONSTRAINT `FK_cdmwhv9a0bocythvdq75wrkgo` FOREIGN KEY (`cours_id`) REFERENCES `cours` (`id`),
  ADD CONSTRAINT `FK_ew1j133f6y7d4r1bifkf6rgc9` FOREIGN KEY (`sequence_id`) REFERENCES `sequences` (`id`);

--
-- Contraintes pour la table `topics`
--
ALTER TABLE `topics`
  ADD CONSTRAINT `FK_3xwo6yg0xh1fc5i2ilcf6vbd7` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
