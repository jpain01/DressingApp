--Insertion formes

INSERT INTO FORME(signe) VALUES ('Huit');

INSERT INTO FORME(signe) VALUES ('V');

INSERT INTO FORME(signe) VALUES ('O');

INSERT INTO FORME(signe) VALUES ('A');

INSERT INTO FORME(signe) VALUES ('H');

INSERT INTO FORME(signe) VALUES ('X');

--Insertion matieres saison

INSERT INTO MATIERE_SAISON(matiere) VALUES ('Coton');

INSERT INTO MATIERE_SAISON(matiere) VALUES ('Jean');

INSERT INTO MATIERE_SAISON(matiere) VALUES ('Cuir');

INSERT INTO MATIERE_SAISON(matiere) VALUES ('Dentelle');

INSERT INTO MATIERE_SAISON(matiere) VALUES ('Paillete');

INSERT INTO MATIERE_SAISON(matiere) VALUES ('Satin');

INSERT INTO MATIERE_SAISON(matiere) VALUES ('Laine');

INSERT INTO MATIERE_SAISON(matiere) VALUES ('Velours');

INSERT INTO MATIERE_SAISON(matiere) VALUES ('Lin');


--Insertion personnes

INSERT INTO PERSONNE(identifiant, mdp, nom, prenom, age, taille, couleurCheveux, couleurPreferee, signe) VALUES ('pmouches','pmouches76','Mouches', 'Pauline', 21, 168, 'Brun', 23, 'A');

--Insertion sacs
 
INSERT INTO SAC (iddressing, couleur, types,image) VALUES (1,1,'Sacamain','sac-a-main-bleu.jpg');

INSERT INTO SAC (iddressing, couleur, types,image) VALUES (1,25,'Sacamain','sac-a-main-noir.jpg');

INSERT INTO SAC (iddressing, couleur, types,image) VALUES (1,14,'Sacamain','sac-a-main-rouge.jpg');

INSERT INTO SAC (iddressing, couleur, types,image) VALUES (1,25,'Pochette','pochette-noire.jpg');

INSERT INTO SAC (iddressing, couleur, types,image) VALUES (1,21,'Sacados','sac-a-dos-blanc.jpg');

INSERT INTO SAC (iddressing, couleur, types,image) VALUES (1,25,'Sacados','sac-a-dos-noir.jpg');

INSERT INTO SAC (iddressing, couleur, types,image) VALUES (1,7,'Sacados','sac-a-dos-gris-fonce.jpg');

--Insertion chaussures

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,25,'Bottesplates','botte-plate-noir.jpg');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,9,'Bottesatalons','chaussures-icone.jpeg');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,25,'Bottesatalons','chaussures-icone.jpeg');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,8,'Sandales','sandales-marron-clair.jpg');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,25,'Sandales','sandale-noir.jpg');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,31,'Ballerines','ballerine-beige.jpg');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,14,'Ballerines','chaussures-icone.jpeg');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,15,'Baskets','basket-rose-pale.jpg');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,12,'Baskets','basket-bordeaux.jpg');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,7,'Baskets','chaussures-icone.jpeg');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,8,'Escarpins','chaussures-icone.jpeg');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,25,'Escarpins','chaussures-icone.jpeg');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,6,'Escarpins','chaussures-icone.jpeg');

--Insertion pantalons

INSERT INTO PANTALON (iddressing, couleur, matiere,sale_propre,typep, coupep, image) VALUES (1,1,'Jean',0,'Pantalon','Slim','jean-slim-bleu.jpeg');

INSERT INTO PANTALON (iddressing, couleur, matiere,sale_propre,typep, coupep,image) VALUES (1,25,'Jean',0,'Pantalon','Slim','jean-slim-noir.jpg');

INSERT INTO PANTALON (iddressing, couleur, matiere,sale_propre,typep, coupep,image) VALUES (1,5,'Jean',0,'Pantalon','Slim','jean-slim-gris-clair.jpg');

INSERT INTO PANTALON (iddressing, couleur, matiere,sale_propre,typep, coupep,image) VALUES (1,26,'Coton',0,'Jogging','Droit','jogging-icone.jpeg');

INSERT INTO PANTALON (iddressing, couleur, matiere,sale_propre,typep, coupep,image) VALUES (1,5,'Coton',0,'Jogging','Droit','jogging-icone.jpeg');

INSERT INTO PANTALON (iddressing, couleur, matiere,sale_propre,typep, coupep,image) VALUES (1,3,'Coton',0,'Jogging','Droit','jogging-icone.jpeg');

INSERT INTO PANTALON (iddressing, couleur, matiere,sale_propre, typep, coupep,image) VALUES (1,31,'Lin',0,'Pantalon','Evase','pantalon-lin-beige.jpg');

INSERT INTO PANTALON (iddressing, couleur, matiere,sale_propre, typep, coupep,image) VALUES (1,25,'Lin',0,'Pantalon','Evase','pantalon-icone.png');

INSERT INTO PANTALON (iddressing, couleur, matiere,sale_propre, typep, coupep,image) VALUES (1,7,'Lin',0,'Pantalon','Evase','pantalon-lin-gris-fonce.jpg');

--Insertion Haut

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,31,'Laine',0,'Pull','Large','pull-beige.jpg');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,5,'Laine',0,'Pull','Cintre','pull-gris-clair.jpg');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,21,'Laine',0,'Pull','Droit','pull-blanc.jpg');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,25,'Laine',0,'Pull','Droit','pull-icone.png');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,24,'Laine',0,'Pull','Large','pull-icone.png');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,26,'Coton',0,'Manteau','Cintre','manteau-kaki.jpg');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,8,'Laine',0,'Manteau','Large','manteau-icone.png');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,31,'Coton',0,'Veste','Cintre','veste-beige.jpeg');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,1,'Jean',0,'Veste','Cintre','veste-jean.jpg');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,15,'Coton',0,'Veste','Cintre','veste-rose-pale.jpg');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,25,'Coton',0,'Veste','Droit','veste-icone.png');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,25,'Coton',0,'Chemisier','Cintre','chemisier-noir.jpg');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,21,'Coton',0,'Chemisier','Cintre','chemisier-icone.png');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,12,'Coton',0,'Chemisier','Cintre','chemisier-bordeaux.jpg');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,5,'Coton',0,'Teeshirt','Droit','teeshirt-icone.png');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,28,'Coton',0,'Teeshirt','Cintre','t-shirt-vert.jpg');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,21,'Coton',0,'Teeshirt','Large','t-shirt-blanc.jpg');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,1,'Coton',0,'Teeshirt','Droit','t-shirt-bleu.jpg');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,23,'Coton',0,'Teeshirt','Droit','t-shirt-jaune.jpg');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,25,'Coton',0,'Teeshirt','Cintre','teeshirt-icone.png');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,15,'Coton',0,'Teeshirt','Cintre','teeshirt-icone.png');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,24,'Coton',0,'Teeshirt','Cintre','t-shirt-dore.jpg');

--Insertion autres

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,12,'Coton',0,'Jupe','Court','jupe-bordeaux.jpg');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,5,'Coton',0,'Jupe','Court','jupe-gris-clair.jpg');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,8,'Laine',0,'Jupe','Court','jupe-laine-marron-clair.JPG');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre,typea, coupea,image) VALUES (1,1,'Coton',0,'Robe','Court','robe-bleue.jpg');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,21,'Coton',0,'Robe','Court','robe-blanche.jpg');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,6,'Coton',0,'Robe','Court','robe-icone.png');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,5,'Laine',0,'Robe','Court','robe-icone.png');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,25,'Laine',0,'Robe','Court','robe-icone.png');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,23,'Laine',0,'Robe','Court','robe-laine-jaune.jpg');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,1,'Jean',0,'Combinaison','Court','combinaison-bleue.jpg');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,31,'Lin',0,'Combinaison','Long','combinaison-icone.jpeg');

