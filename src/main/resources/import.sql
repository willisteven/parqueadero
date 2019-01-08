insert into tipo_vehiculo(id_tipo_vehiculo,tipo,descripcion) VALUES(1,'moto','tipo de vehiculo moto');

insert into tipo_vehiculo(id_tipo_vehiculo,tipo,descripcion) VALUES(2,'carro','tipo de vehiculo carro');

insert into tipo_tiempo(id_tipo_tiempo,tipo) VALUES(1,'Hora');

insert into tipo_tiempo(id_tipo_tiempo,tipo) VALUES(2,'Dia');

insert into precio(id_precio,id_tipo_tiempo,id_tipo_vehiculo,valor) values(1,1,1,500);

insert into precio(id_precio,id_tipo_tiempo,id_tipo_vehiculo,valor) values(2,1,2,1000);

insert into precio(id_precio,id_tipo_tiempo,id_tipo_vehiculo,valor) values(3,2,1,4000);

insert into precio(id_precio,id_tipo_tiempo,id_tipo_vehiculo,valor) values(4,2,2,8000);