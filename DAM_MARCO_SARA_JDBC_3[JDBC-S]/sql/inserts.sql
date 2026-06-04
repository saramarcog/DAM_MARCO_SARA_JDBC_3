INSERT INTO socs (nombre, pais, nivel_seguridad, autor_examen) VALUES ('SOC 1', 'ESPAÑA', 10, 'SARA MARCO');
INSERT INTO socs (nombre, pais, nivel_seguridad, autor_examen) VALUES ('SOC 2', 'ITALIA', 20, 'SARA MARCO');
INSERT INTO socs (nombre, pais, nivel_seguridad, autor_examen) VALUES ('SOC 3', 'JAPON', 70, 'SARA MARCO');
INSERT INTO socs (nombre, pais, nivel_seguridad, autor_examen) VALUES ('SOC 4', 'iMAGI NACION', 3, 'SARA MARCO');
INSERT INTO socs (nombre, pais, nivel_seguridad, autor_examen) VALUES ('SOC 5', 'ANDORRA', 67, 'SARA MARCO');

INSERT INTO incidentes (codigo_incidente, tipo_incidente, fecha_deteccion, estado, fk_soc_id, autor_examen) VALUES ('A320', 'Phising', '01/06/2026', 'Resuelto', 1, 'SARA MARCO');
INSERT INTO incidentes (codigo_incidente, tipo_incidente, fecha_deteccion, estado, fk_soc_id, autor_examen) VALUES ('A434', 'Ataque 1', '01/06/2026', 'Resuelto', 1, 'SARA MARCO');
INSERT INTO incidentes (codigo_incidente, tipo_incidente, fecha_deteccion, estado, fk_soc_id, autor_examen) VALUES ('B540', 'Troyano', '01/06/2026', 'Pendiente', 1, 'SARA MARCO');
INSERT INTO incidentes (codigo_incidente, tipo_incidente, fecha_deteccion, estado, fk_soc_id, autor_examen) VALUES ('B994', 'Troyano', '01/06/2026', 'Pendiente', 1, 'SARA MARCO');
INSERT INTO incidentes (codigo_incidente, tipo_incidente, fecha_deteccion, estado, fk_soc_id, autor_examen) VALUES ('A222', 'Ataque 2', '01/06/2026', 'Resuelto', 1, 'SARA MARCO');

INSERT INTO informes_incidente(malware_detectado, nivel_severidad, conclusion, fk_incidente_id, autor_examen) VALUES (true, 90, 'Conclusion',1, 'Sara Marco');
INSERT INTO informes_incidente(malware_detectado, nivel_severidad, conclusion, fk_incidente_id, autor_examen) VALUES (false, 90, 'Falsa alarma',2, 'Sara Marco');
INSERT INTO informes_incidente(malware_detectado, nivel_severidad, conclusion, fk_incidente_id, autor_examen) VALUES (true, 90, 'conclusion',3, 'Sara Marco');
INSERT INTO informes_incidente(malware_detectado, nivel_severidad, conclusion, fk_incidente_id, autor_examen) VALUES (true, 90, 'Aprobado?',5, 'Sara Marco');
INSERT INTO informes_incidente(malware_detectado, nivel_severidad, conclusion, fk_incidente_id, autor_examen) VALUES (true, 90, 'Pendiente',4, 'Sara Marco');