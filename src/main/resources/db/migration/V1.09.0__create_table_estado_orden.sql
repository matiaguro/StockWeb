drop table if exists ESTADO_ORDEN;

CREATE TABLE ESTADO_ORDEN
(
  id_estado BIGINT AUTO_INCREMENT NOT NULL,
  clave_estado VARCHAR(50) NOT NULL,
  desc_estado VARCHAR(50) NOT NULL,
  CONSTRAINT pk_estado PRIMARY KEY (id_estado)
)engine = InnoDB;
