/* Populate tables */

INSERT INTO roles (nombre) VALUES ('ADMIN');
INSERT INTO roles (nombre) VALUES ('USER');

INSERT INTO clientes (nombre, apellido, email, foto, create_at) VALUES('Paco', 'Merlo', 'paco@JHG.COM', '', '2022-08-28');
INSERT INTO clientes (nombre, apellido, email, foto, create_at) VALUES('Maria', 'Porro', 'maria@JHG.COM', '','2022-08-28');
INSERT INTO clientes (nombre, apellido, email, foto, create_at) VALUES('Dolores', 'Fuertes', 'dolores@JHG.COM', '','2022-08-28');
INSERT INTO clientes (nombre, apellido, email, foto, create_at) VALUES('Rosa', 'Melano', 'rosa@JHG.COM', '','2022-08-28');
INSERT INTO clientes (nombre, apellido, email, foto, create_at) VALUES('AAJDHF', 'ASDA', 'ASDASD@JHG.COM', '','2022-08-28');
INSERT INTO clientes (nombre, apellido, email, foto, create_at) VALUES('AAJDHF', 'ASDA', 'ASDASD@JHG.COM', '','2022-08-28');
INSERT INTO clientes (nombre, apellido, email, foto, create_at) VALUES('AAJDHF', 'ASDA', 'ASDASD@JHG.COM', '','2022-08-28');
INSERT INTO clientes (nombre, apellido, email, foto, create_at) VALUES('AAJDHF', 'ASDA', 'ASDASD@JHG.COM', '','2022-08-28');
INSERT INTO clientes (nombre, apellido, email, foto, create_at) VALUES('AAJDHF', 'ASDA', 'ASDASD@JHG.COM', '','2022-08-28');
INSERT INTO clientes (nombre, apellido, email, foto, create_at) VALUES('AAJDHF', 'ASDA', 'ASDASD@JHG.COM','', '2022-08-28');
INSERT INTO clientes (nombre, apellido, email, foto, create_at) VALUES('AAJDHF', 'ASDA', 'ASDASD@JHG.COM', '','2022-08-28');
INSERT INTO clientes (nombre, apellido, email, foto, create_at) VALUES('AAJDHF', 'ASDA', 'ASDASD@JHG.COM', '','2022-08-28');
INSERT INTO clientes (nombre, apellido, email, foto, create_at) VALUES('AAJDHF', 'ASDA', 'ASDASD@JHG.COM', '','2022-08-28');
INSERT INTO clientes (nombre, apellido, email, foto, create_at) VALUES('AAJDHF', 'ASDA', 'ASDASD@JHG.COM', '','2022-08-28');
INSERT INTO clientes (nombre, apellido, email, foto, create_at) VALUES('Nacho', 'Segura', 'profesor@bolsadeideas.com', '', '2023-02-18');
INSERT INTO clientes (nombre, apellido, email, foto, create_at) VALUES('John', 'Doe', 'john.doe@gmail.com', '', '2017-08-28');

INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('Samsung S22',899,'2023-03-01','Movil de alta gama de Samsung');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('IPhone 14 pro',899,'2023-03-01','Movil de alta gama de Apple');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('Nintendo Switch',899,'2023-03-01','Videoconsola de Nintento');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('Asus VIVOBOOK',899,'2023-03-01','Portatil ASUS con pantalla OLED');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('PS5',899,'2023-03-01','Videoconsola de Sony');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('Razer Viper',899,'2023-03-01','Raton de PC para e-sports');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('Samsung S22',899,'2023-03-01','Movil de alta gama de Samsung');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('IPhone 14 pro',899,'2023-03-01','Movil de alta gama de Apple');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('Nintendo Switch',899,'2023-03-01','Videoconsola de Nintento');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('Asus VIVOBOOK',899,'2023-03-01','Portatil ASUS con pantalla OLED');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('PS5',899,'2023-03-01','Videoconsola de Sony');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('Razer Viper',899,'2023-03-01','Raton de PC para e-sports');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('Samsung S22',899,'2023-03-01','Movil de alta gama de Samsung');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('IPhone 14 pro',899,'2023-03-01','Movil de alta gama de Apple');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('Nintendo Switch',899,'2023-03-01','Videoconsola de Nintento');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('Asus VIVOBOOK',899,'2023-03-01','Portatil ASUS con pantalla OLED');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('PS5',899,'2023-03-01','Videoconsola de Sony');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('Razer Viper',899,'2023-03-01','Raton de PC para e-sports');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('Samsung S22',899,'2023-03-01','Movil de alta gama de Samsung');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('IPhone 14 pro',899,'2023-03-01','Movil de alta gama de Apple');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('Nintendo Switch',899,'2023-03-01','Videoconsola de Nintento');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('Asus VIVOBOOK',899,'2023-03-01','Portatil ASUS con pantalla OLED');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('PS5',899,'2023-03-01','Videoconsola de Sony');
INSERT INTO productos (nombre, precio, create_at, descripcion) VALUES('Razer Viper',899,'2023-03-01','Raton de PC para e-sports');


INSERT INTO facturas (descripcion, observaciones, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 6);

INSERT INTO facturas (descripcion, observaciones, cliente_id, create_at) VALUES('Factura consola', null, 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 2, 3);