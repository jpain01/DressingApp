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
 
INSERT INTO SAC (iddressing, couleur, types,image) VALUES (1,1,'Sacamain','sac_a_main_bleu');

INSERT INTO SAC (iddressing, couleur, types,image) VALUES (1,25,'Sacamain','sac_a_main_noir');

INSERT INTO SAC (iddressing, couleur, types,image) VALUES (1,14,'Sacamain','sac_a_main_rouge');

INSERT INTO SAC (iddressing, couleur, types,image) VALUES (1,25,'Pochette','pochette_noire');

INSERT INTO SAC (iddressing, couleur, types,image) VALUES (1,21,'Sacados','sac_a_dos_blanc');

INSERT INTO SAC (iddressing, couleur, types,image) VALUES (1,25,'Sacados','sac_a_dos_noir');

INSERT INTO SAC (iddressing, couleur, types,image) VALUES (1,7,'Sacados','sac_a_dos_gris_fonce');

--Insertion chaussures

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,25,'Bottesplates','botte_plate_noir');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,9,'Bottesatalons','chaussures_icone');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,25,'Bottesatalons','chaussures_icone');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,8,'Sandales','sandales_marron_clair');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,25,'Sandales','sandale_noir');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,31,'Ballerines','ballerine_beige');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,14,'Ballerines','chaussures_icone');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,15,'Baskets','basket_rose_pale');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,12,'Baskets','basket_bordeaux');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,7,'Baskets','chaussures_icone');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,8,'Escarpins','chaussures_icone');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,25,'Escarpins','chaussures_icone');

INSERT INTO CHAUSSURE (iddressing,couleur,typec,image) VALUES (1,6,'Escarpins','chaussures_icone');

--Insertion pantalons

INSERT INTO PANTALON (iddressing, couleur, matiere,sale_propre,typep, coupep, image) VALUES (1,1,'Jean',0,'Pantalon','Slim','jean_slim_bleu');

INSERT INTO PANTALON (iddressing, couleur, matiere,sale_propre,typep, coupep,image) VALUES (1,25,'Jean',0,'Pantalon','Slim','jean_slim_noir');

INSERT INTO PANTALON (iddressing, couleur, matiere,sale_propre,typep, coupep,image) VALUES (1,5,'Jean',0,'Pantalon','Slim','jean_slim_gris_clair');

INSERT INTO PANTALON (iddressing, couleur, matiere,sale_propre,typep, coupep,image) VALUES (1,26,'Coton',0,'Jogging','Droit','jogging_icone');

INSERT INTO PANTALON (iddressing, couleur, matiere,sale_propre,typep, coupep,image) VALUES (1,5,'Coton',0,'Jogging','Droit','jogging_icone');

INSERT INTO PANTALON (iddressing, couleur, matiere,sale_propre,typep, coupep,image) VALUES (1,3,'Coton',0,'Jogging','Droit','jogging_icone');

INSERT INTO PANTALON (iddressing, couleur, matiere,sale_propre, typep, coupep,image) VALUES (1,31,'Lin',0,'Pantalon','Evase','pantalon_lin_beige');

INSERT INTO PANTALON (iddressing, couleur, matiere,sale_propre, typep, coupep,image) VALUES (1,25,'Lin',0,'Pantalon','Evase','pantalon_icone');

INSERT INTO PANTALON (iddressing, couleur, matiere,sale_propre, typep, coupep,image) VALUES (1,7,'Lin',0,'Pantalon','Evase','pantalon_lin_gris_fonce');

--Insertion Haut

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,31,'Laine',0,'Pull','Large','pull_beige');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,5,'Laine',0,'Pull','Cintre','pull_gris_clair');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,21,'Laine',0,'Pull','Droit','pull_blanc');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,25,'Laine',0,'Pull','Droit','pull_icone');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,24,'Laine',0,'Pull','Large','pull_icone');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,26,'Coton',0,'Manteau','Cintre','manteau_kaki');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,8,'Laine',0,'Manteau','Large','manteau_icone');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,31,'Coton',0,'Veste','Cintre','veste_beige');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,1,'Jean',0,'Veste','Cintre','veste_jean');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,15,'Coton',0,'Veste','Cintre','veste_rose_pale');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,25,'Coton',0,'Veste','Droit','veste_icone');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,25,'Coton',0,'Chemisier','Cintre','chemisier_noir');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,21,'Coton',0,'Chemisier','Cintre','chemisier_icone');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,12,'Coton',0,'Chemisier','Cintre','chemisier_bordeaux');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,5,'Coton',0,'Teeshirt','Droit','teeshirt_icone');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,28,'Coton',0,'Teeshirt','Cintre','t_shirt_vert');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,21,'Coton',0,'Teeshirt','Large','t_shirt_blanc');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,1,'Coton',0,'Teeshirt','Droit','t_shirt_bleu');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,23,'Coton',0,'Teeshirt','Droit','t_shirt_jaune');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,25,'Coton',0,'Teeshirt','Cintre','teeshirt_icone');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,15,'Coton',0,'Teeshirt','Cintre','teeshirt_icone');

INSERT INTO HAUT (iddressing, couleur, matiere,sale_propre,typeh, coupeh,image) VALUES (1,24,'Coton',0,'Teeshirt','Cintre','t_shirt_dore');

--Insertion autres

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,12,'Coton',0,'Jupe','Court','jupe_bordeaux');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,5,'Coton',0,'Jupe','Court','jupe_gris_clair');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,8,'Laine',0,'Jupe','Court','jupe_laine_marron_clair');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre,typea, coupea,image) VALUES (1,1,'Coton',0,'Robe','Court','robe_bleue');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,21,'Coton',0,'Robe','Court','robe_blanche');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,6,'Coton',0,'Robe','Court','robe_icone');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,5,'Laine',0,'Robe','Court','robe_icone');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,25,'Laine',0,'Robe','Court','robe_icone');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,23,'Laine',0,'Robe','Court','robe_laine_jaune');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,1,'Jean',0,'Combinaison','Court','combinaison_bleue');

INSERT INTO AUTRE (iddressing, couleur, matiere, sale_propre, typea, coupea,image) VALUES (1,31,'Lin',0,'Combinaison','Long','combinaison_icone');

