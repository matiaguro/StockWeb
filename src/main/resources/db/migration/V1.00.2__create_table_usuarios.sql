drop table if exists usuarios;

CREATE TABLE usuarios (
   id_usuario BIGINT AUTO_INCREMENT NOT NULL,
   usuario  VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   id_rol   bigint  DEFAULT 1     NOT NULL,
   CONSTRAINT pk_usuario PRIMARY KEY (id_usuario),
   CONSTRAINT fk_usuario foreign key (id_rol) references roles (id_rol)
)engine = InnoDB;
