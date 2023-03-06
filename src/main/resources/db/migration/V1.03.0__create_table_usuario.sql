drop table if exists usuario;

CREATE TABLE usuario (
   id_usuario BIGINT AUTO_INCREMENT NOT NULL,
   firstname  VARCHAR(255) NOT NULL,
   lastname  VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   id_rol   bigint     NOT NULL,
   CONSTRAINT pk_usuario PRIMARY KEY (id_usuario),
   CONSTRAINT fk_usuario foreign key (id_rol) references role (id_rol)
)engine = InnoDB;
