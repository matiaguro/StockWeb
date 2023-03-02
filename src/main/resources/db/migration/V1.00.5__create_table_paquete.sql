drop table if exists PAQUETE;

CREATE TABLE PAQUETE
(
   id_paquete BIGINT AUTO_INCREMENT NOT NULL,
   nombre_paquete VARCHAR(255) DEFAULT '-',
   CONSTRAINT pk_paquete PRIMARY KEY (id_paquete)
)engine = InnoDB;