-- TIPOS DE DOCUMENTOS

INSERT INTO public.tipos_identificaciones(descripcion) VALUES ('Cedula de ciudadania');
INSERT INTO public.tipos_identificaciones(descripcion) VALUES ('Cedula de extranjeria');
INSERT INTO public.tipos_identificaciones(descripcion) VALUES ('Tarjeta de identidad');
INSERT INTO public.tipos_identificaciones(descripcion) VALUES ('Registro Civil');


-- Aeropuertos
INSERT INTO public.aeropuertos( nombre, ciudad) VALUES ('Aeropuerto Internacional El Edén', 'Armenia');
INSERT INTO public.aeropuertos( nombre, ciudad) VALUES ('Aeropuerto Internacional El Dorado', 'Bogotá');
INSERT INTO public.aeropuertos( nombre, ciudad) VALUES ('Aeropuerto Internacional José María Córdova', 'Medellín');
INSERT INTO public.aeropuertos( nombre, ciudad) VALUES ('Aeropuerto Internacional Gustavo Rojas Pinilla ', 'San Andrés');
INSERT INTO public.aeropuertos( nombre, ciudad) VALUES ('Aeropuerto Internacional Matecaña', 'Pereira');
INSERT INTO public.aeropuertos( nombre, ciudad) VALUES ('Aeropuerto Los Garzones', 'Monteria');
INSERT INTO public.aeropuertos( nombre, ciudad) VALUES ('Aeropuerto Gabriel Vargas Santos', 'Tame');



INSERT INTO public.aviones(modelo, capacidad, fabricante) VALUES ('Airbus A-310', 265, 'A-300');
INSERT INTO public.aviones(modelo, capacidad, fabricante) VALUES ('Airbus A-320', 265, 'A-300');
INSERT INTO public.aviones(modelo, capacidad, fabricante) VALUES ('Boeing 737', 117, 'Sabena');
INSERT INTO public.aviones(modelo, capacidad, fabricante) VALUES ('Boeing 747', 200, 'Sabena');
INSERT INTO public.aviones(modelo, capacidad, fabricante) VALUES ('British Bae 121', 149, 'Trident');

