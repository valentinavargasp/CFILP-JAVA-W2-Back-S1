/*
Los comandos DDL utilizazdos en el proyecto fueron CREATE TABLE, ALTER TABLE  y DROP TABLE 
*/

/*
CREATE TABLE test(
    id_test int not null, 
    text varchar(40),
    text2 varchar(50),
    PRIMARY KEY (id_test)
);

ALTER TABLE test DROP COLUMN text2;
ALTER TABLE test ADD text3 varchar(255);

Select * from test;

DROP TABLE test;
*/

-- Las siguientes sentencias DDL fueron las utilizadas para crear las tablas de la DB del proyecto.

--PERSONAS
CREATE TABLE person (
  id_person int  NOT NULL AUTO_INCREMENT ,
  name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  address varchar(50) NOT NULL,
  location varchar(50) NOT NULL,
  province varchar(50) NOT NULL,
  phone_number varchar(50) NOT NULL,
  identity_card int NOT NULL,
  date_birth date NOT NULL,
  PRIMARY KEY (id_person)
);

--USUARIOS  cada usuario tiene una persona asociada, credenciales de acceso y roles.
-- las contraseñas deberian ser encriptadas antes de guardarse. 
CREATE TABLE user (
  id_user INT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(50) NOT NULL ,
  password VARCHAR(100) NOT NULL,
  id_person INT NOT NULL,
  FOREIGN KEY (id_person) REFERENCES person(id_person)
);


--ROLES  distintos roles para los usuarios 
CREATE TABLE role (
  id_role INT PRIMARY KEY AUTO_INCREMENT,
  role_name VARCHAR(50) NOT NULL UNIQUE
);

--USER_ROLE  vincula al usuario con un rol.
CREATE TABLE user_role (
  id_user_role INT PRIMARY KEY AUTO_INCREMENT,
  id_user INT NOT NULL,
  id_role INT NOT NULL,
  FOREIGN KEY (id_user) REFERENCES user(id_user),
  FOREIGN KEY (id_role) REFERENCES role(id_role),
  UNIQUE (id_user, id_role) -- evita roles duplicados
);

select * from person join user where id_person > 100;

--Cuenta
CREATE TABLE account (
  id_account int NOT NULL AUTO_INCREMENT,
  client_id int DEFAULT NULL,
  cbu varchar(22) NOT NULL,
  balance decimal(20,2) NOT NULL,
  alias varchar(50) NOT NULL,
  account_type_id int DEFAULT NULL,
  PRIMARY KEY (id_account),
  UNIQUE KEY cbu (cbu),
  UNIQUE KEY alias (alias),
  KEY client_id (client_id),
  KEY account_type_id (account_type_id),
  CONSTRAINT account_ibfk_1 FOREIGN KEY (client_id) REFERENCES client (id_client),
  CONSTRAINT account_ibfk_2 FOREIGN KEY (account_type_id) REFERENCES account_type (id_account_type),
  CONSTRAINT account_chk_1 CHECK ((balance >= 0)
  )
);

  --Agrega la columna currency a  la tabla account,  creo que podria ser ENUM;
  Alter table account add currency varchar(20);


--Tipo de cuenta
CREATE TABLE account_type (
  id_account_type int NOT NULL AUTO_INCREMENT,
  account_type varchar(20) NOT NULL,
  PRIMARY KEY (id_account_type)
);

------------------------
--Transacciones  (pagos, transferencias, depositos, extracciones)
CREATE TABLE `transaction` (
  `id_transaction` int NOT NULL AUTO_INCREMENT,
  `account_id` int NOT NULL,
  `transaction_date` date NOT NULL,
  `transaction_amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_transaction`),
  KEY `account_id` (`account_id`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id_account`)
);

--Pagos
CREATE TABLE `virtual_payment` (
  `id_transaction` int NOT NULL AUTO_INCREMENT,
  `commerce_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_transaction`),
  CONSTRAINT `virtual_payment_ibfk_1` FOREIGN KEY (`id_transaction`) REFERENCES `transaction` (`id_transaction`)
);

--Trasnferencias
CREATE TABLE `transfer` (
  `id_transaction` int NOT NULL AUTO_INCREMENT,
  `destination_account_id` int NOT NULL,
  `description` varchar(50) DEFAULT 'Varios',
  PRIMARY KEY (`id_transaction`),
  KEY `destination_account_id` (`destination_account_id`),
  CONSTRAINT `transfer_ibfk_1` FOREIGN KEY (`id_transaction`) REFERENCES `transaction` (`id_transaction`),
  CONSTRAINT `transfer_ibfk_2` FOREIGN KEY (`destination_account_id`) REFERENCES `account` (`id_account`)
);

--Extracciones
CREATE TABLE `withdrawal` (
  `id_transaction` int NOT NULL AUTO_INCREMENT,
  `method` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_transaction`),
  CONSTRAINT `withdrawal_ibfk_1` FOREIGN KEY (`id_transaction`) REFERENCES `transaction` (`id_transaction`)
); 

--Depositos
CREATE TABLE `deposit` (
  `id_transaction` int NOT NULL AUTO_INCREMENT,
  `method_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_transaction`),
  CONSTRAINT `deposit_ibfk_1` FOREIGN KEY (`id_transaction`) REFERENCES `transaction` (`id_transaction`)
);



-----------------------
--Productos financieros (tarjetas, prestamos, plazo fijo)

CREATE TABLE `financial_product` (
  `id_financial_product` int NOT NULL AUTO_INCREMENT,
  `account_id` int DEFAULT NULL,
  `start_date` date NOT NULL,
  `due_date` date NOT NULL,
  `product_state_id` int NOT NULL,
  PRIMARY KEY (`id_financial_product`),
  KEY `account_id` (`account_id`),
  KEY `product_state_id` (`product_state_id`),
  CONSTRAINT `financial_product_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id_account`),
  CONSTRAINT `financial_product_ibfk_2` FOREIGN KEY (`product_state_id`) REFERENCES `product_state` (`id_product_state`)
); 

--estado
CREATE TABLE `product_state` (
  `id_product_state` int NOT NULL AUTO_INCREMENT,
  `state_type` varchar(20) NOT NULL,
  PRIMARY KEY (`id_product_state`)
); 

-------tarjetas
CREATE TABLE `credit_card` (
  `id_financial_product` int NOT NULL AUTO_INCREMENT,
  `card_number` varchar(16) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `card_type_id` int DEFAULT NULL,
  `purchase_limit` decimal(20,2) NOT NULL,
  PRIMARY KEY (`id_financial_product`),
  UNIQUE KEY `card_number` (`card_number`),
  KEY `card_type_id` (`card_type_id`),
  CONSTRAINT `credit_card_ibfk_1` FOREIGN KEY (`card_type_id`) REFERENCES `card_type` (`id_card_type`),
  CONSTRAINT `credit_card_ibfk_2` FOREIGN KEY (`id_financial_product`) REFERENCES `financial_product` (`id_financial_product`)
);


--extension
CREATE TABLE `credit_card_extension` (
  `id_extension` int NOT NULL AUTO_INCREMENT,
  `credit_card_id` int NOT NULL,
  `holder_name` varchar(50) NOT NULL,
  `identity_card` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_extension`),
  KEY `credit_card_id` (`credit_card_id`),
  CONSTRAINT `credit_card_extension_ibfk_1` FOREIGN KEY (`credit_card_id`) REFERENCES `credit_card` (`id_financial_product`)
); 

---prestamos
CREATE TABLE `loan` (
  `id_financial_product` int NOT NULL AUTO_INCREMENT,
  `interest` decimal(10,2) NOT NULL,
  `amount` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id_financial_product`),
  CONSTRAINT `loan_ibfk_1` FOREIGN KEY (`id_financial_product`) REFERENCES `financial_product` (`id_financial_product`),
  CONSTRAINT `loan_chk_1` CHECK ((`interest` >= 0))
);


--plazo fijo
CREATE TABLE `time_deposit` (
  `id_financial_product` int NOT NULL AUTO_INCREMENT,
  `interest` decimal(10,2) NOT NULL,
  `time_deposit_type_id` int DEFAULT NULL,
  `amount` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id_financial_product`),
  KEY `time_deposit_type_id` (`time_deposit_type_id`),
  CONSTRAINT `time_deposit_ibfk_1` FOREIGN KEY (`time_deposit_type_id`) REFERENCES `time_deposit_type` (`id_time_deposit_type`),
  CONSTRAINT `time_deposit_ibfk_2` FOREIGN KEY (`id_financial_product`) REFERENCES `financial_product` (`id_financial_product`),
  CONSTRAINT `time_deposit_chk_1` CHECK ((`interest` >= 0))
);


