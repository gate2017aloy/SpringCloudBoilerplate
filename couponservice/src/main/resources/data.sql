DROP TABLE IF EXISTS COUPONS;

CREATE TABLE COUPONS (
                          id INT AUTO_INCREMENT  PRIMARY KEY,
                          code VARCHAR(250) NOT NULL,
                          discount DECIMAL(10,2) NOT NULL,
                          exp_date DATE NOT NULL
);

INSERT INTO COUPONS (code, discount, exp_date) VALUES
('APRIL10', '10', '2021-04-30');
