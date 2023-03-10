drop table if exists PAQUETE;

CREATE TABLE PAQUETE
(
   id_paquete BIGINT AUTO_INCREMENT NOT NULL,
   nombre_paquete VARCHAR(255) DEFAULT '-',
   id_orden BIGINT,
   CONSTRAINT pk_paquete PRIMARY KEY (id_paquete),
   CONSTRAINT fk_paquete_orden foreign key (id_orden) references orden (id_orden)
)engine = InnoDB;