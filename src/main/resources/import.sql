/* Populate tables */

INSERT INTO clientes (id, nombre, apellido, email, foto, create_at) VALUES(1, 'Paco', 'Merlo', 'paco@JHG.COM', '', '2022-08-28');
INSERT INTO clientes (id, nombre, apellido, email, foto, create_at) VALUES(2, 'Maria', 'Porro', 'maria@JHG.COM', '','2022-08-28');
INSERT INTO clientes (id, nombre, apellido, email, foto, create_at) VALUES(3, 'Dolores', 'Fuertes', 'dolores@JHG.COM', '','2022-08-28');
INSERT INTO clientes (id, nombre, apellido, email, foto, create_at) VALUES(4, 'Rosa', 'Melano', 'rosa@JHG.COM', '','2022-08-28');
INSERT INTO clientes (id, nombre, apellido, email, foto, create_at) VALUES(5, 'AAJDHF', 'ASDA', 'ASDASD@JHG.COM', '','2022-08-28');
INSERT INTO clientes (id, nombre, apellido, email, foto, create_at) VALUES(6, 'AAJDHF', 'ASDA', 'ASDASD@JHG.COM', '','2022-08-28');
INSERT INTO clientes (id, nombre, apellido, email, foto, create_at) VALUES(7, 'AAJDHF', 'ASDA', 'ASDASD@JHG.COM', '','2022-08-28');
INSERT INTO clientes (id, nombre, apellido, email, foto, create_at) VALUES(8, 'AAJDHF', 'ASDA', 'ASDASD@JHG.COM', '','2022-08-28');
INSERT INTO clientes (id, nombre, apellido, email, foto, create_at) VALUES(9, 'AAJDHF', 'ASDA', 'ASDASD@JHG.COM', '','2022-08-28');
INSERT INTO clientes (id, nombre, apellido, email, foto, create_at) VALUES(10, 'AAJDHF', 'ASDA', 'ASDASD@JHG.COM','', '2022-08-28');
INSERT INTO clientes (id, nombre, apellido, email, foto, create_at) VALUES(11, 'AAJDHF', 'ASDA', 'ASDASD@JHG.COM', '','2022-08-28');
INSERT INTO clientes (id, nombre, apellido, email, foto, create_at) VALUES(12, 'AAJDHF', 'ASDA', 'ASDASD@JHG.COM', '','2022-08-28');
INSERT INTO clientes (id, nombre, apellido, email, foto, create_at) VALUES(13, 'AAJDHF', 'ASDA', 'ASDASD@JHG.COM', '','2022-08-28');
INSERT INTO clientes (id, nombre, apellido, email, foto, create_at) VALUES(14, 'AAJDHF', 'ASDA', 'ASDASD@JHG.COM', '','2022-08-28');
INSERT INTO clientes (id, nombre, apellido, email, foto, create_at) VALUES(15, 'Nacho', 'Segura', 'profesor@bolsadeideas.com', '', '2023-02-18');
INSERT INTO clientes (id, nombre, apellido, email, foto, create_at) VALUES(16, 'John', 'Doe', 'john.doe@gmail.com', '', '2017-08-28');

INSERT INTO productos (id, nombre, precio, create_at) VALUES(1,'Samsung S22',899,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(2,'IPhone 14 pro',1400,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(3,'Nintendo Switch',350,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(4,'PS5',550,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(5,'Asus VIVOBOOK',1099,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(6,'Razer Viper',120,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(7,'Samsung S22',899,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(8,'IPhone 14 pro',1400,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(9,'Nintendo Switch',350,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(10,'PS5',550,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(11,'Asus VIVOBOOK',1099,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(12,'Razer Viper',120,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(13,'Samsung S22',899,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(14,'IPhone 14 pro',1400,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(15,'Nintendo Switch',350,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(16,'PS5',550,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(17,'Asus VIVOBOOK',1099,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(17,'Razer Viper',120,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(18,'Samsung S22',899,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(19,'IPhone 14 pro',1400,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(20,'Nintendo Switch',350,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(21,'PS5',550,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(22,'Asus VIVOBOOK',1099,'2023-03-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES(23,'Razer Viper',120,'2023-03-01');

INSERT INTO facturas (descripcion, observaciones, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 6);

INSERT INTO facturas (descripcion, observaciones, cliente_id, create_at) VALUES('Factura consola', null, 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 2, 3);