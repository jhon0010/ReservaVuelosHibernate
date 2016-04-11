-- TIPOS DE DOCUMENTOS

INSERT INTO public.tipos_identificaciones(descripcion) VALUES ('Cedula de ciudadania');
INSERT INTO public.tipos_identificaciones(descripcion) VALUES ('Cedula de extranjeria');
INSERT INTO public.tipos_identificaciones(descripcion) VALUES ('Tarjeta de identidad');
INSERT INTO public.tipos_identificaciones(descripcion) VALUES ('Registro Civil');


-- Aeropuertos
INSERT INTO public.aeropuertos( nombre, ciudad) VALUES ('Aeropuerto Internacional El Ed�n', 'Armenia');
INSERT INTO public.aeropuertos( nombre, ciudad) VALUES ('Aeropuerto Internacional El Dorado', 'Bogot�');
INSERT INTO public.aeropuertos( nombre, ciudad) VALUES ('Aeropuerto Internacional Jos� Mar�a C�rdova', 'Medell�n');
INSERT INTO public.aeropuertos( nombre, ciudad) VALUES ('Aeropuerto Internacional Gustavo Rojas Pinilla ', 'San Andr�s');
INSERT INTO public.aeropuertos( nombre, ciudad) VALUES ('Aeropuerto Internacional Mateca�a', 'Pereira');
INSERT INTO public.aeropuertos( nombre, ciudad) VALUES ('Aeropuerto Los Garzones', 'Monteria');
INSERT INTO public.aeropuertos( nombre, ciudad) VALUES ('Aeropuerto Gabriel Vargas Santos', 'Tame');



INSERT INTO public.aviones(modelo, capacidad, fabricante) VALUES ('Airbus A-310', 265, 'A-300');
INSERT INTO public.aviones(modelo, capacidad, fabricante) VALUES ('Airbus A-320', 265, 'A-300');
INSERT INTO public.aviones(modelo, capacidad, fabricante) VALUES ('Boeing 737', 117, 'Sabena');
INSERT INTO public.aviones(modelo, capacidad, fabricante) VALUES ('Boeing 747', 200, 'Sabena');
INSERT INTO public.aviones(modelo, capacidad, fabricante) VALUES ('British Bae 121', 149, 'Trident');

