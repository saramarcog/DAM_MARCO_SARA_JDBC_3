DROP TABLE IF EXISTS socs CASCADE;
DROP TABLE IF EXISTS muestras_forenses CASCADE;
DROP TABLE IF EXISTS informes_forenses CASCADE;

CREATE TABLE socs  (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    pais VARCHAR(100) NOT NULL,
    nivel_seguridad INT,
    autor_examen VARCHAR(150) NOT NULL
);

CREATE TABLE incidentes (
    id SERIAL PRIMARY KEY,
    codigo_incidente  VARCHAR(150) NOT NULL,
    tipo_incidente VARCHAR(150) NOT NULL,
    fecha_deteccion VARCHAR(15),
    estado VARCHAR(150),
    fk_soc_id INT,
    autor_examen VARCHAR(150) NOT NULL,
    CONSTRAINT fk_indicente_soc FOREIGN KEY (fk_soc_id) REFERENCES socs(id) ON DELETE CASCADE
);

CREATE TABLE informes_incidente(
    id SERIAL PRIMARY KEY,
    malware_detectado BOOLEAN DEFAULT FALSE,
    nivel_severidad INT,
    conclusion VARCHAR(150),
    FK_incidente_id INT UNIQUE,
    autor_examen VARCHAR(150) NOT NULL,
    CONSTRAINT fk_informe_incidente FOREIGN KEY (fk_incidente_id) REFERENCES incidentes(id)  
);