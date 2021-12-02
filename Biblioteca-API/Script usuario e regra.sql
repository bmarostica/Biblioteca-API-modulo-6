create table usuario(
ID_USUARIO INT not null primary key generated always as identity,
LOGIN VARCHAR(255) not null UNIQUE,
SENHA VARCHAR(255) not null
)

create table REGRA(
ID_REGRA INT not null primary key generated always as identity,
NOME VARCHAR(255) not null unique
)

create TABLE USUARIO_REGRA(
ID_USUARIO INT,
ID_REGRA INT,
PRIMARY KEY(ID_USUARIO, ID_REGRA),
FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO (ID_USUARIO),
FOREIGN KEY (ID_REGRA) REFERENCES REGRA (ID_REGRA)
)

select * from USUARIO_REGRA


insert into REGRA (NOME)
VALUES('ROLE_ADMIN')

insert into REGRA (NOME)
VALUES('ROLE_FUNCIONARIO')

insert into REGRA (NOME)
values('ROLE_CLIENTE')

insert into USUARIO(LOGIN, SENHA)
VALUES('bianca', '$2a$10$Y2Ny5elCmSLvlRPZDZb5sO8adb8589T1sR9CLh/YDVxdyKwHg9jBO')

insert into USUARIO(LOGIN, SENHA)
VALUES('camile', '$2a$10$Y2Ny5elCmSLvlRPZDZb5sO8adb8589T1sR9CLh/YDVxdyKwHg9jBO')

insert into USUARIO(LOGIN, SENHA)
VALUES('david', '$2a$10$Y2Ny5elCmSLvlRPZDZb5sO8adb8589T1sR9CLh/YDVxdyKwHg9jBO')

insert into USUARIO_REGRA(ID_USUARIO, ID_REGRA)
VALUES(1, 3)

insert into USUARIO_REGRA(ID_USUARIO, ID_REGRA)
VALUES(2, 1)

insert into USUARIO_REGRA(ID_USUARIO, ID_REGRA)
VALUES(2, 2)

insert into USUARIO_REGRA(ID_USUARIO, ID_REGRA)
VALUES(2, 3)

insert into USUARIO_REGRA(ID_USUARIO, ID_REGRA)
VALUES(3, 2)

insert into USUARIO_REGRA(ID_USUARIO, ID_REGRA)
VALUES(3, 3)




 