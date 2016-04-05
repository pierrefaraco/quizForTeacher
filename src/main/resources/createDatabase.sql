CREATE DATABASE quiz_for_teacher CHARACTER SET 'utf8';

CREATE TABLE USERS (
    id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(40) NOT NULL,
    lastname VARCHAR(40) NOT NULL,
    birthday DATETIME NOT NULL,
    email VARCHAR(255) NOT NULL,
    presentation TEXT,
    login VARCHAR(40) NOT NULL,
    password CHAR(32) NOT NULL,
    account_type CHAR(1)NOT NULL,
    PRIMARY KEY (id)
)
ENGINE=INNODB;

CREATE TABLE TOPICS (
    id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    description TEXT,
	user_id SMALLINT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
)
ENGINE=INNODB;

INSERT INTO users 
VALUES (1,2,'1981-02-04','pierre.faraco@gmail.com','Pierre', 'Faraco', 'admin','password', 'Salut je m appel Pierre');
INSERT INTO users 
VALUES (2,2,'1981-02-04','jennyferPipereau@gmail.com','Jennyfer', 'Pipereau', 'admin','password', 'Salut je m appel Jen');

INSERT INTO topics VALUES (1, 'Le java c est cool','java',1);
INSERT INTO topics VALUES (2, 'Le html c est cool','html',1);
INSERT INTO topics VALUES (3, 'Le c++ c est cool','c++',1);
INSERT INTO topics VALUES (4, 'Le c# c est cool','c#',1);
INSERT INTO topics VALUES (5, 'Le css c est cool','css',1);

INSERT INTO topics VALUES (6, 'Le java c est cool2','java2',2);
INSERT INTO topics VALUES (7, 'Le html c est cool2','html2',2);
INSERT INTO topics VALUES (8, 'Le c++ c est cool2','c++2',2);
INSERT INTO topics VALUES (9, 'Le c# c est cool2','c#2',2);
INSERT INTO topics VALUES (10, 'Le css c est cool2','css2',2);