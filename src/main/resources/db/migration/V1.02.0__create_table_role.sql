drop table if exists role;

CREATE TABLE role (
   id_rol BIGINT AUTO_INCREMENT NOT NULL,
   rol_name VARCHAR(255) NOT NULL,
   CONSTRAINT pk_rol PRIMARY KEY (id_rol)
)engine = InnoDB;
