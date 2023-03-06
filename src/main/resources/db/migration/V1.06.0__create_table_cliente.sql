drop table if exists cliente;

CREATE TABLE cliente (
   id_cliente BIGINT AUTO_INCREMENT NOT NULL,
   firstname  VARCHAR(255) NOT NULL,
   lastname  VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   CONSTRAINT pk_cliente PRIMARY KEY (id_cliente)
)engine = InnoDB;
