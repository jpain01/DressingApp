-------------------------
-- Creation des tables --
-------------------------

CREATE TABLE IF NOT EXISTS MATIERE_SAISON (
	matiere VARCHAR(30) PRIMARY KEY CHECK (matiere IN ('Laine','Coton','Jean','Lin','Velours','Cuir','Dentelle','Daim', 'Satin','Paillete')),
	saison VARCHAR(30) CHECK (saison IN ('Toutes','Automne/Hiver','Printemps/Ete'))	
);

CREATE TABLE IF NOT EXISTS FORME (
       signe VARCHAR(5) PRIMARY KEY CHECK (signe IN ('Huit','V','O','A','H','X'))
);

CREATE TABLE IF NOT EXISTS PERSONNE (
        id INTEGER PRIMARY KEY AUTOINCREMENT ,
        nom  VARCHAR(20) NOT NULL,
        prenom VARCHAR(20) NOT NULL,
        age INTEGER NOT NULL,
        taille INTEGER NOT NULL,
        identifiant  VARCHAR(20) NOT NULL,
        mdp  VARCHAR(20) NOT NULL,
        couleurCheveux VARCHAR(20) NOT NULL CHECK ( couleurCheveux  IN ('Blond','Brun','Roux','Chatain')),
        couleurPreferee INTEGER NOT NULL,
        signe VARCHAR(5) NOT NULL REFERENCES FORME(signe) ON DELETE CASCADE 
);


CREATE TABLE IF NOT EXISTS DRESSING (
        idDressing INTEGER PRIMARY KEY AUTOINCREMENT,
        idPers INTEGER REFERENCES PERSONNE(id)
);

 
CREATE TABLE IF NOT EXISTS SAC (
        idObjet INTEGER PRIMARY KEY AUTOINCREMENT,
        idDressing INTEGER REFERENCES DRESSING(idDressing) ON DELETE CASCADE,
        couleur INTEGER NOT NULL,
        typeS VARCHAR(20) NOT NULL CHECK (typeS IN ('Sacados','Sacamain','Pochette')),
        image VARCHAR(200) 
);


CREATE TABLE IF NOT EXISTS CHAUSSURE (
        idObjet INTEGER PRIMARY KEY AUTOINCREMENT,
        idDressing INTEGER REFERENCES DRESSING(idDressing) ON DELETE CASCADE,
        couleur INTEGER NOT NULL,
        typeC VARCHAR(20) NOT NULL CHECK (typeC IN ('Escarpins','Ballerines','Baskets','Bottesplates','Bottesatalons','Sandales')),
        image VARCHAR(200) 
);


CREATE TABLE IF NOT EXISTS HAUT (
        idObjet INTEGER PRIMARY KEY AUTOINCREMENT,
        idDressing INTEGER REFERENCES DRESSING(idDressing) ON DELETE CASCADE,
        couleur INTEGER NOT NULL,
        matiere VARCHAR(30) REFERENCES MATIERE_SAISON(matiere) ON DELETE CASCADE CHECK ( matiere IN ('Laine','Coton','Jean','Lin','Velours','Cuir','Dentelle','Daim', 'Satin','Paillete')),
        couche INTEGER  CHECK (couche>0 AND couche<4),
        niveau VARCHAR(20)  CHECK (niveau IN ('Haut')),
        sale_propre BOOLEAN NOT NULL,
        image VARCHAR(200),
        typeH VARCHAR(20) NOT NULL CHECK (typeH IN ('Teeshirt','Chemisier','Pull','Veste','Manteau')),
        coupeH VARCHAR(20) NOT NULL CHECK (coupeH IN ('Cintre','Droit','Large'))
);


CREATE TABLE IF NOT EXISTS PANTALON (
        idObjet INTEGER PRIMARY KEY AUTOINCREMENT,
        idDressing INTEGER REFERENCES DRESSING(idDressing) ON DELETE CASCADE,
        couleur INTEGER NOT NULL,
        matiere VARCHAR(30) REFERENCES MATIERE_SAISON(matiere) ON DELETE CASCADE CHECK ( matiere IN ('Laine','Coton','Jean','Lin','Velours','Cuir','Dentelle','Daim', 'Satin','Paillete')),
        couche INTEGER  CHECK (couche>0 AND couche<4),
        niveau VARCHAR(20)  CHECK (niveau IN ('Bas')),
        sale_propre BOOLEAN NOT NULL,
        image VARCHAR(200),
	typeP VARCHAR(20) NOT NULL CHECK (typeP IN ('Pantalon','Pantacourt','Jogging')),
        coupeP VARCHAR(20) NOT NULL CHECK (coupeP IN ('Slim','Droit','Evase','Baggy'))
);


CREATE TABLE IF NOT EXISTS AUTRE (
        idObjet INTEGER PRIMARY KEY AUTOINCREMENT,
        idDressing INTEGER REFERENCES DRESSING(idDressing) ON DELETE CASCADE,
        couleur INTEGER NOT NULL,
        matiere VARCHAR(30) REFERENCES MATIERE_SAISON(matiere) ON DELETE CASCADE CHECK ( matiere IN ('Laine','Coton','Jean','Lin','Velours','Cuir','Dentelle','Daim', 'Satin','Paillete')),
        couche INTEGER  CHECK (couche>0 AND couche<4),
        niveau VARCHAR(20)  CHECK (niveau IN ('Haut','Bas','Hautbas')),
        sale_propre BOOLEAN NOT NULL,
        image VARCHAR(200),
	typeA VARCHAR(20) NOT NULL CHECK (typeA IN ('Combinaison','Robe','Jupe','Short')),
        coupeA VARCHAR(20) NOT NULL CHECK (coupeA IN ('Long','Court'))
);



--Creation associations

CREATE TABLE IF NOT EXISTS CORRESPOND_PANTALON (
        idObjet INTEGER REFERENCES PANTALON(idObjet) ON DELETE CASCADE,
        signe VARCHAR(5) REFERENCES PANTALON(signe) ON DELETE CASCADE,
        PRIMARY KEY (idObjet,signe)
);

CREATE TABLE IF NOT EXISTS CORRESPOND_AUTRE (
        idObjet INTEGER REFERENCES AUTRE(idObjet) ON DELETE CASCADE,
        signe VARCHAR(5) REFERENCES AUTRE(signe) ON DELETE CASCADE,
        PRIMARY KEY (idObjet,signe)
);

CREATE TABLE IF NOT EXISTS CORRESPOND_HAUT (
        idObjet INTEGER REFERENCES AUTRE(idObjet) ON DELETE CASCADE,
        signe VARCHAR(5) REFERENCES HAUT(signe) ON DELETE CASCADE,
        PRIMARY KEY (idObjet,signe)
);


---------------------------
-- Création des TRIGGERS --
---------------------------

-- Lorsque l'on insert une personne, le dressing correspondant à cette personne doit être automatiquement créé.
CREATE TRIGGER IF NOT EXISTS attribuerDressing
	AFTER INSERT ON PERSONNE
	FOR EACH ROW 
	BEGIN 
	INSERT INTO DRESSING(idPers) VALUES (NEW.id);
	END ;


-- Lorsqu'on supprime une personne, le dressing correspondant à cette personne doit être supprimé.
CREATE TRIGGER IF NOT EXISTS supprimerDressingPersonne
	BEFORE DELETE ON PERSONNE
	FOR EACH ROW 	
	BEGIN 
	DELETE FROM DRESSING WHERE idPers=OLD.id;
	END;


-- Lorsqu'on supprime un dressing, le contenu correspondant à ce dressing doit être supprimé.
CREATE TRIGGER IF NOT EXISTS supprimerContenuDressing
	BEFORE DELETE ON DRESSING
	FOR EACH ROW 	
	BEGIN
	DELETE FROM SAC WHERE idDressing=OLD.idDressing;
	DELETE FROM PANTALON WHERE idDressing=OLD.idDressing;
	DELETE FROM AUTRE WHERE idDressing=OLD.idDressing;
	DELETE FROM HAUT WHERE idDressing=OLD.idDressing;
	DELETE FROM CHAUSSURE WHERE idDressing=OLD.idDressing;	
	END;
    

-- Permet d'attribuer une couche (1 ou 2) au vêtement en fonction du type du vetement.
CREATE TRIGGER IF NOT EXISTS attribuerCoucheH
	AFTER INSERT ON HAUT
	FOR EACH ROW 	
	BEGIN
	UPDATE HAUT SET couche = 1;
	UPDATE HAUT SET couche = 2 WHERE typeH='Veste';
	UPDATE HAUT SET couche = 2 WHERE typeH='Manteau';
	END;


CREATE TRIGGER IF NOT EXISTS attribuerCoucheP
	AFTER INSERT ON PANTALON
	FOR EACH ROW 
	BEGIN
	UPDATE PANTALON SET couche = 1;
	END;

CREATE TRIGGER IF NOT EXISTS attribuerCoucheA
	AFTER INSERT ON AUTRE
	FOR EACH ROW 
	BEGIN
	UPDATE AUTRE SET couche = 1;
	END;


-- Permet d'attribuer un niveau (Haut, Bas, Hautbas) à un vêtement
CREATE TRIGGER IF NOT EXISTS attribuerNiveauH
	AFTER INSERT ON HAUT 
	FOR EACH ROW 	
	BEGIN 
	UPDATE HAUT SET niveau='Haut';
	END;

CREATE TRIGGER IF NOT EXISTS attribuerNiveauP
        AFTER INSERT ON PANTALON 
	FOR EACH ROW         
	BEGIN 
        UPDATE PANTALON SET niveau='Bas';
        END;

CREATE TRIGGER IF NOT EXISTS attribuerNiveauA
        AFTER INSERT ON AUTRE
	FOR EACH ROW         
	BEGIN 
        UPDATE AUTRE SET niveau='Bas';
	UPDATE AUTRE SET niveau='Hautbas' WHERE typeA='Combinaison';
	UPDATE AUTRE SET niveau='Hautbas' WHERE typeA='Robe';
        END;


-- Permet d'attribuer une saison pour chaque matiere

CREATE TRIGGER IF NOT EXISTS attribuerSaisonMatiere
        AFTER INSERT ON MATIERE_SAISON   
	BEGIN 
	UPDATE MATIERE_SAISON SET saison='Toutes';
UPDATE MATIERE_SAISON SET saison='Automne/Hiver' WHERE matiere='Laine';
UPDATE MATIERE_SAISON SET saison='Automne/Hiver' WHERE matiere='Velours';
UPDATE MATIERE_SAISON SET saison='Printemps/Ete' WHERE matiere='Lin';
	END;


-- Permet d'attribuer à chaque vetement les formes qui lui correspondent selon sa coupe
CREATE TRIGGER IF NOT EXISTS attribuerFormesH
        AFTER INSERT ON HAUT
	FOR EACH ROW 
	WHEN NEW.coupeH<>'Cintre' AND NEW.coupeH<>'Droit'         
	BEGIN 
        INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'O');
        INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'Huit');
        INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'V');
        INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'X');
        END;

CREATE TRIGGER IF NOT EXISTS attribuerFormesHCintre
        AFTER INSERT ON HAUT
	FOR EACH ROW 
	WHEN NEW.coupeH='Cintre'        
	BEGIN 
        INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'H');
        INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'Huit');
        INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'V');
        INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'X');
        INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'A');
	END;

CREATE TRIGGER IF NOT EXISTS attribuerFormesHDroit
        AFTER INSERT ON HAUT
	FOR EACH ROW 
	WHEN NEW.coupeH='Droit'        
	BEGIN 
        INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'H');
        INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'O');
        INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'A');
	END;

CREATE TRIGGER IF NOT EXISTS attribuerFormesP
        AFTER INSERT ON PANTALON
	FOR EACH ROW 
	WHEN NEW.coupeP<>'Evase' AND NEW.coupeP<>'Slim' AND NEW.coupeP<>'Droit'
        BEGIN 
        INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'O');
        INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'Huit');
        INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'V');
        END;

CREATE TRIGGER IF NOT EXISTS attribuerFormesPDroit
        AFTER INSERT ON PANTALON
	FOR EACH ROW 
	WHEN NEW.coupeP='Droit'
        BEGIN 
	INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'H');
        INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'Huit');
        INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'O');
        INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'X');
        INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'A');
	END;

CREATE TRIGGER IF NOT EXISTS attribuerFormesPSlim
        AFTER INSERT ON PANTALON
	FOR EACH ROW 
	WHEN NEW.coupeP='Slim'
        BEGIN 
        INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'H');
        INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'V');
        INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'A');
        INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'X');
	END;

CREATE TRIGGER IF NOT EXISTS attribuerFormesPEvase
        AFTER INSERT ON PANTALON
	FOR EACH ROW 
	WHEN NEW.coupeP='Evase'
        BEGIN 
        INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'Huit');
        INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'O');
	END;

CREATE TRIGGER IF NOT EXISTS attribuerFormesA
        AFTER INSERT ON AUTRE
	FOR EACH ROW 
	WHEN NEW.coupeA<>'Long' 
        BEGIN 
        INSERT INTO CORRESPOND_AUTRE VALUES (NEW.idobjet,'H');
        INSERT INTO CORRESPOND_AUTRE VALUES (NEW.idobjet,'Huit');
        INSERT INTO CORRESPOND_AUTRE VALUES (NEW.idobjet,'V');
        END;

CREATE TRIGGER IF NOT EXISTS attribuerFormesALongue
        AFTER INSERT ON AUTRE
	FOR EACH ROW 
	WHEN NEW.coupeA='Long'
        BEGIN 
        INSERT INTO CORRESPOND_AUTRE VALUES (NEW.idobjet,'H');
        INSERT INTO CORRESPOND_AUTRE VALUES (NEW.idobjet,'Huit');
        INSERT INTO CORRESPOND_AUTRE VALUES (NEW.idobjet,'O');
        INSERT INTO CORRESPOND_AUTRE VALUES (NEW.idobjet,'X');
        INSERT INTO CORRESPOND_AUTRE VALUES (NEW.idobjet,'A');
        INSERT INTO CORRESPOND_AUTRE VALUES (NEW.idobjet,'V');
	END;

-- Lorsqu'un vetement est supprimé, son instance dans correspond doit être supprimé également
CREATE TRIGGER IF NOT EXISTS desattribuerFormesH
        BEFORE DELETE ON HAUT
	FOR EACH ROW         
	BEGIN 
        DELETE FROM CORRESPOND_HAUT WHERE idobjet=OLD.idobjet;
        END;

CREATE TRIGGER IF NOT EXISTS desattribuerFormesA
        BEFORE DELETE ON AUTRE
	FOR EACH ROW         
	BEGIN 
        DELETE FROM CORRESPOND_AUTRE WHERE idobjet=OLD.idobjet;
        END;

CREATE TRIGGER IF NOT EXISTS desattribuerFormesP
        BEFORE DELETE ON PANTALON
	FOR EACH ROW         
	BEGIN 
        DELETE FROM CORRESPOND_PANTALON WHERE idobjet=OLD.idobjet;
        END;


