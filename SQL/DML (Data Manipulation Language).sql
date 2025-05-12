/*
Los comandos DML utilizazdos en el proyecto fueron SELECT, INSERT, UPDATE y DELETE
*/


---- Inserta Persona
INSERT INTO person(
  name ,
  last_name ,
  address ,
  location ,
  province ,
  phone_number ,
  identity_card ,
  date_birth 
) VALUES( 'Sebastian', 'Tesitore', 'Congreso', 'Santa Rosa', 'La Pampa', '2954536377', 36314268, 1992-08-12 )

INSERT INTO person(
  name ,
  last_name ,
  address ,
  location ,
  province ,
  phone_number ,
  identity_card ,
  date_birth 
) VALUES( 'Luciana', 'Alzuri', 'San Martin', 'Santa Rosa', 'La Pampa', '2955522447', 38255248, '1996-11-20' );


--SELECT
select * from person where identity_card = 36314268;

select * from person where last_name like 'V%';

select * from person where location like 'La%';

select * from person limit 10;

select * from person where id_person > 100;

--UPDATE
UPDATE person set address = 'Rivadavia' where id_person = 101;
UPDATE person set date_birth = '1992-08-12' where id_person = 101;
UPDATE person set date_birth = '1992-12-18' where id_person = 102;



--CREA USUARIO
INSERT INTO user (
  email  ,
  password ,
  id_person 
) VALUES(
    'Cristionna@gmail.com', '123456', 4
);




/*
ROLES
1 administrativo
2 programador 
3 cliente
*/

INSERT INTO role (
  id_role  ,
	role_name
) VALUES(
   3, 'cliente');
   

INSERT INTO role (
	role_name
) VALUES(
    'cliente');
    
INSERT INTO role (
	role_name
) VALUES(
    'administrativo');

------------
INSERT INTO role (
	role_name
) VALUES(
    'programador');


select * from role; 

/* ---------User_Role*/

/*insert into user_role(id_user, id_role) values (101,7)  ACA HAY QUE TENER CUIDADO Y USAR EL ID DE USER Y NO DE    PERSONA*/

INSERT INTO user_role (
    id_user ,
    id_role 
) VALUES( 7, 5 )
    
INSERT INTO user_role (
id_user ,
id_role 
) VALUES( 6, 5 )

select * from user_role;



/*-------Account -------------------*/

INSERT INTO account(
  id_user,
  cbu,
  balance ,
  alias,
  account_type_id,
  currency 
) VALUES( 3, 0112545879632145785295, 5000.00, 'valentina_p', 1, 'EUR' )




/*  JOIN */


select person.name as Nombre, person.last_name as Apellido, role.role_name as Rol_que_cumple
from person 
inner join user
on person.id_person = user.id_person
inner join user_role 
on user.id_user = user_role.id_user
inner join role
on user_role.id_role = role.id_role

select *
from person 
inner join user
on person.id_person = user.id_person;


//Modficiacion de tablas - agregado de columnas, asignacion Foreign

alter table `aws-alkemy`.virtual_payment
add id_payment integer
first;
alter table `aws-alkemy`.transfer
add id_transfer integer
first;