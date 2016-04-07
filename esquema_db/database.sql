-- Database: "RESERVA_VUELOS"

-- DROP DATABASE "RESERVA_VUELOS";

CREATE DATABASE "RESERVA_VUELOS"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Spanish_Colombia.1252'
       LC_CTYPE = 'Spanish_Colombia.1252'
       CONNECTION LIMIT = -1;


-- Table: public.aeropuertos

-- DROP TABLE public.aeropuertos;

CREATE TABLE public.aeropuertos
(
  id SERIAL PRIMARY KEY,
  nombre character varying(50),
  ciudad character varying(50),
  CONSTRAINT pk_aeropuertos PRIMARY KEY (id)
)

-- DROP TABLE public.aviones;

CREATE TABLE public.aviones
(
  id SERIAL PRIMARY KEY,
  modelo character varying(15),
  capacidad numeric(3,0),
  fabricante character varying(50),
  CONSTRAINT pk_aviones PRIMARY KEY (id)
)

-- DROP TABLE public.pasajeros;

CREATE TABLE public.pasajeros
(
  identificacion character varying(50) NOT NULL,
  nombre_completo character varying(80),
  telefono character varying(15),
  numero_tarjeta character varying(20),
  tipo_identificacion numeric(10,0),
  CONSTRAINT pk_pasajeros PRIMARY KEY (identificacion)
)

CREATE TABLE public.reserva
(
  id SERIAL PRIMARY KEY,
  pasajero character varying(50),
  vuelo numeric(10,0),
  CONSTRAINT pk_reserva PRIMARY KEY (id),
  CONSTRAINT fk_reserva_pasajeros FOREIGN KEY (pasajero)
      REFERENCES public.pasajeros (identificacion) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_reserva_vuelo FOREIGN KEY (vuelo)
      REFERENCES public.vuelo (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)

-- DROP TABLE public.ruta;

CREATE TABLE public.ruta
(
  id SERIAL PRIMARY KEY,
  aeropuerto_origen numeric(10,0),
  aeropuerto_destino numeric(10,0),
  CONSTRAINT pk_ruta PRIMARY KEY (id),
  CONSTRAINT fk_ruta_aeropuertos FOREIGN KEY (aeropuerto_origen)
      REFERENCES public.aeropuertos (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

-- DROP TABLE public.tipos_identificaciones;

CREATE TABLE public.tipos_identificaciones
(
  id SERIAL PRIMARY KEY,
  descripcion character varying(50),
  CONSTRAINT pk_tipos_identificaciones PRIMARY KEY (id)
)


-- DROP TABLE public.vuelo;

CREATE TABLE public.vuelo
(
  id SERIAL PRIMARY KEY,
  ruta_a_cumplir numeric(10,0),
  avion numeric(10,0),
  fecha_salida timestamp without time zone,
  fecha_arribo_estimada timestamp without time zone,
  CONSTRAINT pk_vuelo PRIMARY KEY (id),
  CONSTRAINT fk_vuelo_aviones FOREIGN KEY (avion)
      REFERENCES public.aviones (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
	   
	   