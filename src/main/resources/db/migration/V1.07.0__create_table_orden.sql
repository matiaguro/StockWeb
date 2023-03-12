drop table if exists ORDEN;

CREATE TABLE ORDEN
(
    id_orden BIGINT AUTO_INCREMENT NOT NULL,
    id_usuario BIGINT NOT NULL,
    id_cliente BIGINT NOT NULL,
    id_estado BIGINT NOT NULL,
    precio_total FLOAT,
    descripcion VARCHAR (300),
    fecha_generada datetime NOT NULL,
    fecha_finalizada datetime,
    fecha_modificacion datetime,
    adelanto BOOLEAN,
    CONSTRAINT pk_orden PRIMARY KEY (id_orden),
    CONSTRAINT fk_usuario_orden foreign key (id_usuario) references USUARIO (id_usuario),
    CONSTRAINT fk_cliente_orden foreign key (id_cliente) references CLIENTE (id_cliente),
    CONSTRAINT fk_estado_orden foreign key (id_estado) references ESTADO_ORDEN (id_estado)
) engine = InnoDB;
