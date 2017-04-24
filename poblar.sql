


INSERT INTO POSGRADO (id, nombre,creditos )  VALUES(1, 'Estructuras',27 );
INSERT INTO POSGRADO (id, nombre,creditos )  VALUES(2, 'Desarrollo y gerencia de proyectos',25);
INSERT INTO POSGRADO (id, nombre,creditos )  VALUES(3, 'Produccion Industrial',28);

INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Estructura de cimientos y contencion',1 );
INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(2, 'Cubiertas y estructuras industriales de acero',1 );
INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(3, 'analisis dinamico de estructuras',1 );
INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(4, 'Fundamentos gerenciales',2);
INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(5, 'Gerencia de proyectos basica',2 );

INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'HGC', 'Habilidades en comunicacion',4,2,'habilidades gerenciales para liderar proyectos' );
INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FGGG', 'fundamentos gerenciales2',4,2,'fundamentos de gerencia de proyectos para empresarios2' );
INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FGPR', 'fundamentos gerenciales',4,2,'fundamentos de gerencia de proyectos para empresarios' );
INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'ELF', 'Elementos finitos',4,1,'elemetos de fisica finitos' );
INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'PDH', 'Puentes de hormigon',4,1,'Estructura de puentes en hormigon' );
INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'CDM', 'Construcciones de madera',4,1,'Estruuctutas de madera' );
INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'PDA', 'Puentes de acero',4,1,'Estructuras de acero puentes y edificios' );


INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (5, '2017-01-01', '2017-06-02' ,'2017-1' );
INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (4, '2017-08-01', '2017-12-12' ,'2017-2' );
INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (3, '2016-01-01', '2016-06-02' ,'2016-1' );
INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (2, '2016-08-01', '2016-12-12' ,'2016-2' );
INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (1, '2015-08-01', '2015-12-12' ,'2015-2' );

INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@correo.com',3115134,'cc' );
INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1718429, 'Sergio alvarado', 'sergio@correo.com',8005134,'cc' );
INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1618430, 'Sergio Puentes', 'sergio@correo.com',6115134,'cc' );
INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1518431, 'Laura Bermudez', 'sergio@correo.com',7115134,'cc' );
INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1418432, 'Crisrtian Alba', 'sergio@correo.com',8165134,'cc' );
INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1318433, 'Hector Ramos', 'sergio@correo.com',8115134,'cc' );
INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1218434, 'Fabian Cardenas', 'sergio@correo.com',8125134,'cc' );
INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1118435, 'Luis Chamaco', 'sergio@correo.com',8115134,'cc' );

INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',5,1818428);
INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'PDA',5,1418432);
INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'HGC',5,1218434);
INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'ELF',5,1218434);
INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',4,1418432);
INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'PDA',4,1118435);
INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',3,1318433);

INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id )  VALUES(1,'7:00','10:00', '2017-01-02', 'FGPR', 5);
INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id )  VALUES(2, '15:00','17:00', '2017-01-03', 'PDA', 5 );
INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id )  VALUES(3, '13:00','15:00', '2017-01-02', 'HGC', 5 );
INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id )  VALUES(4, '12:00','14:00', '2017-01-03', 'ELF', 5 );
INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id )  VALUES(5, '10:00','12:00', '2017-01-01', 'FGPR', 4);
INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id )  VALUES(6, '8:00','10:30', '2017-01-02', 'PDA', 4);
INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id )  VALUES(7, '13:00','15:00', '2017-01-03', 'FGPR',3 );


INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(1, 'Portatiles',true, 1,43);
INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(2, 'libro de economia 1',true, 1,10);
INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(3, 'Audio',true, 3,5);
INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(4, 'Libro 2',true, 3,10);
INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(5, 'Microfono',true, 2,2);
INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(6, 'Pesa ',true, 4,6);
INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(7, 'Diccionario',true, 5,20);
INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(8, 'Tamgrams',true, 2,6);
