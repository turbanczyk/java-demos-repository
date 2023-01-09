CREATE TABLE IF NOT EXISTS product (
  product_id INT NOT NULL,
  name varchar(250) NOT NULL,
  PRIMARY KEY (product_id)
);

INSERT INTO product (product_id, name)
VALUES
	(1, 'pencil'),
  (2, 'paper'),
  (3, 'stapler');
