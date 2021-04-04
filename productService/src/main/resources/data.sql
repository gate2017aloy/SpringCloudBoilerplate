DROP TABLE IF EXISTS PRODUCTS;

CREATE TABLE PRODUCTS (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              name VARCHAR(250) NOT NULL,
                              description VARCHAR(250) NOT NULL,
                              price DECIMAL(10,2) DEFAULT NULL
);

INSERT INTO PRODUCTS (name, description, price) VALUES
('Toothpaste', 'Colgate', '100'),
('Brush', 'Oral B', '50'),
('Shampoo', 'Pantene', '300');