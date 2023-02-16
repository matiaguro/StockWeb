drop table if exists PRODUCTO;
drop table if exists PAQUETE;
drop table if exists PRODUCTO_PAQUETE;

CREATE TABLE producto
(
  id_producto BIGINT AUTO_INCREMENT NOT NULL,
   producto VARCHAR(255) NOT NULL,
   stock BIGINT DEFAULT 0 NOT NULL,
   precio BIGINT DEFAULT 0 NOT NULL,
   img_url VARCHAR(255) DEFAULT '-' NOT NULL,
   descripcion VARCHAR(255) DEFAULT '-' NOT NULL,
   fecha_ultimo_inv datetime NOT NULL,
   CONSTRAINT pk_producto PRIMARY KEY (id_producto)
)engine = InnoDB;



CREATE TABLE PAQUETE
(
   id_paquete BIGINT AUTO_INCREMENT NOT NULL,
   nombre_paquete VARCHAR(255) DEFAULT '-' NOT NULL,
   precio_paquete BIGINT DEFAULT 0 NOT NULL,
   CONSTRAINT pk_paquete PRIMARY KEY (id_paquete)
)engine = InnoDB;



create table producto_paquete
(
    id_producto    bigint not null,
    id_paquete     bigint not null,
    primary key (id_producto, id_paquete),
    key id_paquete (id_paquete),
    constraint fk_producto_paquete_1
        foreign key (id_producto) references producto (id_producto),
    constraint fk_producto_paquete_2
        foreign key (id_paquete) references paquete (id_paquete)
) engine = InnoDB;
