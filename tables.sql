-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2017-04-22 05:44:52.99

-- tables
-- Table: Asignatura
CREATE TABLE Asignatura (
    id int  NOT NULL,
    nombre varchar(100)  NOT NULL,
    Posgrado_id int  NOT NULL
);

-- Table: Clase
CREATE TABLE Clase (
    id int  NOT NULL,
    hora_inicio int  NOT NULL,
    hora_fin int  NOT NULL,
    fecha date  NOT NULL,
    Materia_cohorte_Materia_sigla varchar(5)  NOT NULL,
    Materia_cohorte_Cohorte_id int  NOT NULL
);

-- Table: Cohorte
CREATE TABLE Cohorte (
    id int  NOT NULL,
    fecha_inicio date  NOT NULL,
    fecha_fin date  NOT NULL,
    periodo varchar(10)  NOT NULL
);

-- Table: Horario
CREATE TABLE Horario (
    id int  NOT NULL,
    hora_inicio int  NOT NULL,
    hora_fin int  NOT NULL,
    fecha date  NOT NULL,
    Profesor_documento int  NOT NULL
);

-- Table: Materia
CREATE TABLE Materia (
    sigla varchar(5)  NOT NULL,
    nombre varchar(100)  NOT NULL,
    creditos int  NOT NULL,
    Asignatura_id int  NOT NULL,
    descripcion varchar  NOT NULL
);

-- Table: MateriaCohorte
CREATE TABLE MateriaCohorte (
    Materia_sigla varchar(5)  NOT NULL,
    Cohorte_id int  NOT NULL,
    Profesor_documento int  NOT NULL
);

-- Table: MateriaPerrequisito
CREATE TABLE MateriaPerrequisito (
    Materia_sigla varchar(5)  NOT NULL,
    Correquisito boolean  NOT NULL
);

-- Table: Posgrado
CREATE TABLE Posgrado (
    id int  NOT NULL,
    nombre varchar(100)  NOT NULL,
    creditos int  NOT NULL
);

-- Table: Profesor
CREATE TABLE Profesor (
    documento int  NOT NULL,
    nombre varchar  NOT NULL,
    correo varchar(50)  NOT NULL,
    telefono int  NOT NULL,
    tipo_documento varchar(30)  NOT NULL
);

-- Table: Recurso
CREATE TABLE Recurso (
    id int  NOT NULL,
    recurso varchar(100)  NOT NULL,
    disponible boolean  NOT NULL,
    Clase_id int  NOT NULL
);

-- Table: Salon
CREATE TABLE Salon (
    especificaciones varchar(30)  NOT NULL,
    Clase_id int  NOT NULL
);

-- Table: rol
CREATE TABLE rol (
    rol varchar(30)  NOT NULL,
    usr_usuario int  NOT NULL
);

-- Table: usr
CREATE TABLE usr (
    usuario int  NOT NULL,
    pwd varchar(100)  NOT NULL,
    salt varchar(5)  NOT NULL
);
-- primary keys unique keys
ALTER TABLE Asignatura ADD CONSTRAINT Asignatura_pk PRIMARY KEY (id);
ALTER TABLE Clase ADD CONSTRAINT Clase_pk PRIMARY KEY (id);
ALTER TABLE Cohorte ADD CONSTRAINT Cohorte_pk PRIMARY KEY (id);
ALTER TABLE Horario ADD CONSTRAINT Horario_pk PRIMARY KEY (id);
ALTER TABLE Materia ADD CONSTRAINT Sigla PRIMARY KEY (sigla);
ALTER TABLE MateriaCohorte ADD CONSTRAINT MateriaCohorte_pk PRIMARY KEY (Materia_sigla,Cohorte_id);
ALTER TABLE MateriaPerrequisito ADD CONSTRAINT MateriaPerrequisito_pk PRIMARY KEY (Materia_sigla);
ALTER TABLE Posgrado ADD CONSTRAINT Posgrado_pk PRIMARY KEY (id);
ALTER TABLE Profesor ADD CONSTRAINT Profesor_pk PRIMARY KEY (documento);
ALTER TABLE Salon ADD CONSTRAINT Salon_pk PRIMARY KEY (Clase_id);
ALTER TABLE rol ADD CONSTRAINT rol_pk PRIMARY KEY (rol);
ALTER TABLE usr ADD CONSTRAINT usr_pk PRIMARY KEY (usuario);

-- unique keys
ALTER TABLE Clase ADD CONSTRAINT uk_clase UNIQUE (id) ;
ALTER TABLE Profesor ADD CONSTRAINT Profesor_ak_1 UNIQUE (nombre) ;

-- foreign keys
-- Reference: Asignatura_Posgrado (table: Asignatura)

ALTER TABLE Asignatura ADD CONSTRAINT Asignatura_Posgrado
    FOREIGN KEY (Posgrado_id)
    REFERENCES Posgrado (id)  
;

-- Reference: Clase_Materia_cohorte (table: Clase)
ALTER TABLE Clase ADD CONSTRAINT Clase_Materia_cohorte
    FOREIGN KEY (Materia_cohorte_Materia_sigla, Materia_cohorte_Cohorte_id)
    REFERENCES MateriaCohorte (Materia_sigla, Cohorte_id)  
;

-- Reference: Horario_Profesor (table: Horario)
ALTER TABLE Horario ADD CONSTRAINT Horario_Profesor
    FOREIGN KEY (Profesor_documento)
    REFERENCES Profesor (documento)  
;

-- Reference: Materia_Asignatura (table: Materia)
ALTER TABLE Materia ADD CONSTRAINT Materia_Asignatura
    FOREIGN KEY (Asignatura_id)
    REFERENCES Asignatura (id) 
;

-- Reference: Materia_cohorte_Cohorte (table: MateriaCohorte)
ALTER TABLE MateriaCohorte ADD CONSTRAINT Materia_cohorte_Cohorte
    FOREIGN KEY (Cohorte_id)
    REFERENCES Cohorte (id) 
;

-- Reference: Materia_cohorte_Materia (table: MateriaCohorte)
ALTER TABLE MateriaCohorte ADD CONSTRAINT Materia_cohorte_Materia
    FOREIGN KEY (Materia_sigla)
    REFERENCES Materia (sigla) 
;

-- Reference: Materia_cohorte_Profesor (table: MateriaCohorte)
ALTER TABLE MateriaCohorte ADD CONSTRAINT Materia_cohorte_Profesor
    FOREIGN KEY (Profesor_documento)
    REFERENCES Profesor (documento)  
;

-- Reference: Recurso_Clase (table: Recurso)
ALTER TABLE Recurso ADD CONSTRAINT Recurso_Clase
    FOREIGN KEY (Clase_id)
    REFERENCES Clase (id)  
;

-- Reference: Salon_Clase (table: Salon)
ALTER TABLE Salon ADD CONSTRAINT Salon_Clase
    FOREIGN KEY (Clase_id)
    REFERENCES Clase (id)  
;

-- Reference: Table_16_Materia (table: MateriaPerrequisito)
ALTER TABLE MateriaPerrequisito ADD CONSTRAINT Table_16_Materia
    FOREIGN KEY (Materia_sigla)
    REFERENCES Materia (sigla)  
;

-- Reference: rol_usr (table: rol)
ALTER TABLE rol ADD CONSTRAINT rol_usr
    FOREIGN KEY (usr_usuario)
    REFERENCES usr (usuario)  
;

-- End of file.

