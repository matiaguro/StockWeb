drop table if exists PRODUCTO;

CREATE TABLE producto (
  id_producto BIGINT AUTO_INCREMENT NOT NULL,
   producto VARCHAR(255) NOT NULL,
   stock BIGINT DEFAULT 0 NOT NULL,
   precio BIGINT DEFAULT 0 NOT NULL,
   img_url VARCHAR(255) DEFAULT '-' NOT NULL,
   descripcion VARCHAR(255) DEFAULT '-' NOT NULL,
   fecha_ultimo_inv datetime NOT NULL,
   CONSTRAINT pk_producto PRIMARY KEY (id_producto)
)engine = InnoDB;
