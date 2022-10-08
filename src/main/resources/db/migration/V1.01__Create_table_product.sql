drop table if exists product;

CREATE TABLE product (

  product_id BIGINT AUTO_INCREMENT NOT NULL,
  name VARCHAR(255) NOT NULL,
  amount BIGINT NOT NULL,
  description VARCHAR(255) NULL,
  created_at    datetime(6) not null,
  is_active     bit          not null,
  updated_at    datetime(6) null,
  PRIMARY KEY (product_id)
   )engine = InnoDB;