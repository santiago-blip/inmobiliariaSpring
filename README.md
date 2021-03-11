# inmobiliariaSpring
#BD
CREATE DATABASE inmobiliaria;
USE inmobiliaria;

CREATE TABLE tbl_rol(id_rol INT AUTO_INCREMENT, rol VARCHAR(10),PRIMARY KEY(id_rol));
CREATE TABLE tbl_usuario( id_usuario INT AUTO_INCREMENT, id_rol INT ,correo VARCHAR(60),contrasena VARCHAR(60),estado BOOLEAN,codigo VARCHAR(20),FOREIGN KEY(id_rol) REFERENCES tbl_rol(id_rol),PRIMARY KEY(id_usuario));
CREATE TABLE tbl_casa(id_casa INT AUTO_INCREMENT,id_usuario INT,titulo_casa VARCHAR(30),descripcion_casa VARCHAR(80),precio DOUBLE,ruta_img VARCHAR(50),FOREIGN KEY(id_usuario) REFERENCES tbl_usuario(id_usuario),PRIMARY KEY(id_casa));
CREATE TABLE tbl_imagen_casa(id_imagen INT AUTO_INCREMENT,id_casa INT,ruta_img VARCHAR(80),FOREIGN KEY(id_casa)REFERENCES tbl_casa(id_casa),PRIMARY KEY(id_imagen));

#important data
insert into tbl_rol() VALUES (1,"ROLE_USER");
