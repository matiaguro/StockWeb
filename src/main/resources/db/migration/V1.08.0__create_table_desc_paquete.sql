drop table if exists DESC_PAQUETE;

CREATE TABLE DESC_PAQUETE
(
   id_desc_paquete BIGINT AUTO_INCREMENT NOT NULL,
   id_paquete BIGINT NOT NULL,
   id_producto BIGINT NOT NULL,
   cantidad  BIGINT NOT NULL,
   CONSTRAINT pk_paquete PRIMARY KEY (id_desc_paquete),
   CONSTRAINT fk_paquete foreign key (id_paquete) references PAQUETE (id_paquete),
   CONSTRAINT fk_producto foreign key (id_producto) references PRODUCTO (id_producto)
)engine = InnoDB;