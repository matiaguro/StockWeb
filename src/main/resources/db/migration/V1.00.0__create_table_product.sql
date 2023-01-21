drop table if exists PRODUCTO;

CREATE TABLE PRODUCTO (
	ID_PRODUCTO				Long		 				    NOT NULL,
	IMG_URL					VARCHAR(300)                    null,
	PRODUCTO				VARCHAR(200)				    NOT NULL,
	PRECIO					Long 	        DEFAULT (0)	    NOT NULL,
	STOCK					Long 	        DEFAULT (0)	    NOT NULL,
	DESCRIPCION				VARCHAR(200)    default ('-')   null,
	FECHA_ULTIMO_INV		DATE          				    null,
	PRIMARY KEY (ID_PRODUCTO)
)engine = InnoDB;
