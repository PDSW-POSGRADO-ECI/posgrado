--- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2017-05-02 22:08:34.818

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
    hora_inicio time  NOT NULL,
    hora_fin time  NOT NULL,
    fecha date  NOT NULL,
    MateriaCohorte_Materia_sigla varchar(5)  NOT NULL,
    MateriaCohorte_Cohorte_id int  NOT NULL,
    MateriaCohorte_Profesor_documento int  NOT NULL
);

-- Table: Cohorte
CREATE TABLE Cohorte (
    id int  NOT NULL,
    Periodo_periodo varchar(6)  NOT NULL
);

-- Table: Horario
CREATE TABLE Horario (
    hora_inicio time  NOT NULL,
    hora_fin time  NOT NULL,
    fecha date  NOT NULL,
    id int  NOT NULL
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

-- Table: Periodo
CREATE TABLE Periodo (
    periodo varchar(6)  NOT NULL,
    fecha_inicio date  NOT NULL,
    fecha_fin date  NOT NULL
);
-- Table: Posgrado
CREATE TABLE Posgrado (
    id int  NOT NULL,
    nombre varchar(100)  NOT NULL,
    creditos int  NOT NULL
);

-- Table: PrerequisitoMateria
CREATE TABLE PrerequisitoMateria (
    materia_sigla varchar(5)  NOT NULL,
    prerrequisito_sigla varchar(5)  NOT NULL,
    correquisito boolean  NOT NULL
);

-- Table: Profesor
CREATE TABLE Profesor (
    documento int  NOT NULL,
    nombre varchar  NOT NULL,
    correo varchar(50)  NOT NULL,
    telefono int  NOT NULL,
    tipo_documento varchar(30)  NOT NULL
);

-- Table: ProfesorXHorario
CREATE TABLE ProfesorXHorario (
    Profesor_documento int  NOT NULL,
    Horario_id int  NOT NULL
);

-- Table: Recurso
CREATE TABLE Recurso (
    id int  NOT NULL,
    recurso varchar(100)  NOT NULL,
    disponible boolean  NOT NULL,
    cantidad int  NOT NULL
);

-- Table: RecursoClase
CREATE TABLE RecursoClase (
    Clase_id int  NOT NULL,
    Recurso_id int  NOT NULL,
    cantidadUSE int  NOT NULL
);

-- Table: Salon
CREATE TABLE Salon (
    especificaciones varchar(30)  NOT NULL,
    Clase_id int  NOT NULL
);

-- Table: Usr
CREATE TABLE Usr (
    usuario varchar(10)  NOT NULL,
    pwd varchar(100)  NOT NULL,
    salt varchar(5)  NOT NULL,
    nombre varchar(50)  NOT NULL,
    rol varchar(30)  NOT NULL
);

-- Table: rol
CREATE TABLE rol (
    rol varchar(30)  NOT NULL
);

-- Table: usrXrol
--CREATE TABLE usrXrol (
--    rol_rol varchar(30)  NOT NULL,
--    usr_usuario int  NOT NULL );


-- primary keys unique keys
ALTER TABLE Asignatura ADD CONSTRAINT Asignatura_pk PRIMARY KEY (id);
ALTER TABLE Clase ADD CONSTRAINT Clase_pk PRIMARY KEY (id);
ALTER TABLE Cohorte ADD CONSTRAINT Cohorte_pk PRIMARY KEY (id);
ALTER TABLE Horario ADD CONSTRAINT Horario_pk PRIMARY KEY (id);
ALTER TABLE Materia ADD CONSTRAINT Sigla PRIMARY KEY (sigla);
ALTER TABLE Posgrado ADD CONSTRAINT Posgrado_pk PRIMARY KEY (id);
ALTER TABLE Profesor ADD CONSTRAINT Profesor_pk PRIMARY KEY (documento);
ALTER TABLE Salon ADD CONSTRAINT Salon_pk PRIMARY KEY (Clase_id);
ALTER TABLE rol ADD CONSTRAINT rol_pk PRIMARY KEY (rol);
ALTER TABLE usr ADD CONSTRAINT usr_pk PRIMARY KEY (usuario);
ALTER TABLE RecursoClase ADD CONSTRAINT RecursoClase_pk PRIMARY KEY (Clase_id,Recurso_id);
--ALTER TABLE usrXrol ADD CONSTRAINT usrXrol_pk PRIMARY KEY (rol_rol,usr_usuario);
ALTER TABLE Periodo ADD CONSTRAINT Periodo_pk PRIMARY KEY (periodo);
ALTER TABLE PrerequisitoMateria ADD CONSTRAINT PrerequisitoMateria_pk PRIMARY KEY (materia_sigla,prerrequisito_sigla);
ALTER TABLE ProfesorXHorario ADD CONSTRAINT ProfesorXHorario_pk PRIMARY KEY (Profesor_documento,Horario_id);
ALTER TABLE MateriaCohorte ADD CONSTRAINT MateriaCohorte_pk PRIMARY KEY (Materia_sigla,Cohorte_id,Profesor_documento);

-- unique keys
ALTER TABLE Clase ADD CONSTRAINT uk_clase UNIQUE (id) ;
ALTER TABLE Profesor ADD CONSTRAINT Profesor_ak_1 UNIQUE (correo) ;
ALTER TABLE Posgrado ADD CONSTRAINT Posgrado_ak_1 UNIQUE (nombre) ;
ALTER TABLE Asignatura ADD CONSTRAINT Asignatura_ak_1 UNIQUE (nombre) ;
ALTER TABLE recurso ADD CONSTRAINT recurso_ak_1 UNIQUE (recurso) ;
-- foreign keys
-- Reference: Asignatura_Posgrado (table: Asignatura)
ALTER TABLE Asignatura ADD CONSTRAINT Asignatura_Posgrado
    FOREIGN KEY (Posgrado_id)
    REFERENCES Posgrado (id)  
;

-- Reference: Clase_MateriaCohorte (table: Clase)
ALTER TABLE Clase ADD CONSTRAINT Clase_MateriaCohorte
    FOREIGN KEY (MateriaCohorte_Materia_sigla, MateriaCohorte_Cohorte_id, MateriaCohorte_Profesor_documento)
    REFERENCES MateriaCohorte (Materia_sigla, Cohorte_id, Profesor_documento) 
;

-- Reference: Cohorte_Periodo (table: Cohorte)
ALTER TABLE Cohorte ADD CONSTRAINT Cohorte_Periodo
    FOREIGN KEY (Periodo_periodo)
    REFERENCES Periodo (periodo)  
;

-- Reference: MateriaCohorte_Cohorte (table: MateriaCohorte)
ALTER TABLE MateriaCohorte ADD CONSTRAINT MateriaCohorte_Cohorte
    FOREIGN KEY (Cohorte_id)
    REFERENCES Cohorte (id)  
;

-- Reference: MateriaCohorte_Materia (table: MateriaCohorte)
ALTER TABLE MateriaCohorte ADD CONSTRAINT MateriaCohorte_Materia
    FOREIGN KEY (Materia_sigla)
    REFERENCES Materia (sigla)  
;

-- Reference: MateriaCohorte_Profesor (table: MateriaCohorte)
ALTER TABLE MateriaCohorte ADD CONSTRAINT MateriaCohorte_Profesor
    FOREIGN KEY (Profesor_documento)
    REFERENCES Profesor (documento)  
;

-- Reference: Materia_Asignatura (table: Materia)
ALTER TABLE Materia ADD CONSTRAINT Materia_Asignatura
    FOREIGN KEY (Asignatura_id)
    REFERENCES Asignatura (id)  
;

-- Reference: PrerequisitoMateria_Materia (table: PrerequisitoMateria)
ALTER TABLE PrerequisitoMateria ADD CONSTRAINT PrerequisitoMateria_Materia
    FOREIGN KEY (materia_sigla)
    REFERENCES Materia (sigla)  
;

-- Reference: PrerequisitoMateria_Materia2 (table: PrerequisitoMateria)
ALTER TABLE PrerequisitoMateria ADD CONSTRAINT PrerequisitoMateria_Materia2
    FOREIGN KEY (prerrequisito_sigla)
    REFERENCES Materia (sigla) 
;

-- Reference: ProfesorXHorario_Horario (table: ProfesorXHorario)
ALTER TABLE ProfesorXHorario ADD CONSTRAINT ProfesorXHorario_Horario
    FOREIGN KEY (Horario_id)
    REFERENCES Horario (id)  
;

-- Reference: ProfesorXHorario_Profesor (table: ProfesorXHorario)
ALTER TABLE ProfesorXHorario ADD CONSTRAINT ProfesorXHorario_Profesor
    FOREIGN KEY (Profesor_documento)
    REFERENCES Profesor (documento) 
;

-- Reference: Salon_Clase (table: Salon)
ALTER TABLE Salon ADD CONSTRAINT Salon_Clase
    FOREIGN KEY (Clase_id)
    REFERENCES Clase (id)  
;

-- Reference: Table_33_Clase (table: RecursoClase)
ALTER TABLE RecursoClase ADD CONSTRAINT Table_33_Clase
    FOREIGN KEY (Clase_id)
    REFERENCES Clase (id) 
;

-- Reference: Table_33_Recurso (table: RecursoClase)
ALTER TABLE RecursoClase ADD CONSTRAINT Table_33_Recurso
    FOREIGN KEY (Recurso_id)
    REFERENCES Recurso (id) 
;

-- Reference: usr_rol (table: Usr)
ALTER TABLE Usr ADD CONSTRAINT usr_rol
    FOREIGN KEY (rol)
    REFERENCES rol (rol)  
;

-- Reference: usrXrol_rol (table: usrXrol)
---ALTER TABLE usrXrol ADD CONSTRAINT usrXrol_rol
    --FOREIGN KEY (rol_rol)
    --REFERENCES rol (rol)  ;

-- Reference: usrXrol_usr (table: usrXrol)
--ALTER TABLE usrXrol ADD CONSTRAINT usrXrol_usr
--    FOREIGN KEY (usr_usuario)
--   REFERENCES usr (usuario)  ;

-- End of file
