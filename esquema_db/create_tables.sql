CREATE TABLE public.aeropuertos
(
  id SERIAL PRIMARY KEY,
  nombre character varying(50),
  ciudad character varying(50)
);


CREATE TABLE public.aviones
(
  id SERIAL PRIMARY KEY,
  modelo character varying(15),
  capacidad numeric(3,0),
  fabricante character varying(50)
);


CREATE TABLE public.pasajeros
(
  id SERIAL PRIMARY KEY,
  identificacion character varying(50) NOT NULL,
  nombre_completo character varying(80),
  telefono character varying(15),
  numero_tarjeta character varying(20),
  tipo_identificacion integer,
  CONSTRAINT unique_pasajeros UNIQUE (identificacion)
);

CREATE TABLE public.ruta
(
  id SERIAL PRIMARY KEY,
  aeropuerto_origen integer,
  aeropuerto_destino integer,
  CONSTRAINT fk_ruta_aeropuertos FOREIGN KEY (aeropuerto_origen)
      REFERENCES public.aeropuertos (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_ruta_aeropuertos_destino FOREIGN KEY (aeropuerto_destino)
      REFERENCES public.aeropuertos (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.tipos_identificaciones
(
  id SERIAL PRIMARY KEY,
  descripcion character varying(50)
);


CREATE TABLE public.vuelo
(
  id SERIAL PRIMARY KEY,
  ruta_a_cumplir integer,
  avion integer,
  fecha_salida timestamp without time zone,
  fecha_arribo_estimada timestamp without time zone,
  CONSTRAINT fk_vuelo_aviones FOREIGN KEY (avion)
      REFERENCES public.aviones (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);   

CREATE TABLE public.reserva
(
  id SERIAL PRIMARY KEY,
  pasajero integer,
  vuelo integer,
  CONSTRAINT fk_reserva_pasajeros FOREIGN KEY (pasajero)
      REFERENCES public.pasajeros (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_reserva_vuelo FOREIGN KEY (vuelo)
      REFERENCES public.vuelo (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);


