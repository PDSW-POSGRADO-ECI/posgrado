---TRIGGER clase
CREATE OR REPLACE FUNCTION insertclase()
RETURNS TRIGGER AS $$
BEGIN
NEW.id:=(select max(clase.id)+1 FROM clase);
RETURN NEW;
END;
$$ language plpgsql;

CREATE TRIGGER id_clase BEFORE INSERT
    ON Clase FOR EACH ROW 
	EXECUTE PROCEDURE 
	insertclase();

---TRIGGER posgrado
CREATE OR REPLACE FUNCTION insertposgrado()
RETURNS TRIGGER AS $$
BEGIN
NEW.id:=(select max(posgrado.id)+1 FROM posgrado);
RETURN NEW;
END;
$$ language plpgsql;

CREATE TRIGGER id_posgrado BEFORE INSERT
    ON posgrado FOR EACH ROW 
	EXECUTE PROCEDURE 
	insertposgrado();
	
---TRIGGER asignatura
CREATE OR REPLACE FUNCTION insertasignatura()
RETURNS TRIGGER AS $$
BEGIN
NEW.id:=(select max(asignatura.id)+1 FROM asignatura);
RETURN NEW;
END;
$$ language plpgsql;

CREATE TRIGGER id_asignatura BEFORE INSERT
    ON asignatura FOR EACH ROW 
	EXECUTE PROCEDURE 
	insertasignatura();
	
---TRIGGER recurso
CREATE OR REPLACE FUNCTION insertarecurso()
RETURNS TRIGGER AS $$
BEGIN
NEW.id:=(select max(recurso.id)+1 FROM recurso);
RETURN NEW;
END;
$$ language plpgsql;

--- constraints 
ALTER TABLE Profesor ADD CONSTRAINT ck_tipodoc CHECK (tipo_documento IN ('cc','NIT','ce' ) ) ;
ALTER TABLE profesor ADD CONSTRAINT ck_profevorreo CHECK (correo <> '' );
ALTER TABLE profesor ADD CONSTRAINT ck_profenombre CHECK (nombre <> '' ) ;
ALTER TABLE asignatura ADD CONSTRAINT ck_asignombre CHECK (nombre <> '' ) ;
ALTER TABLE posgrado ADD CONSTRAINT ck_posnombre CHECK (nombre <> '' ) ;
ALTER TABLE materia ADD CONSTRAINT ck_matnombre CHECK (nombre <> '' ) ;
ALTER TABLE recurso ADD CONSTRAINT ck_recrec CHECK (recurso <> '' ) ;
ALTER TABLE recurso ADD CONSTRAINT ck_reccant CHECK (cantidad>=0);
ALTER TABLE usr ADD CONSTRAINT ck_usrnombre CHECK (nombre <> '' ) ;