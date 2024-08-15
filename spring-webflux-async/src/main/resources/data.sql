-- Table: public.vehicle

DROP TABLE IF EXISTS public.vehicle;

CREATE TABLE IF NOT EXISTS public.vehicle
(
    id integer NOT NULL DEFAULT nextval('vehicle_id_seq'::regclass),
    model character varying(250) COLLATE pg_catalog."default" NOT NULL,
    make character varying(250) COLLATE pg_catalog."default" NOT NULL,
    vin character varying(250) COLLATE pg_catalog."default" NOT NULL,
    color character varying(250) COLLATE pg_catalog."default" NOT NULL,
    year character varying(250) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT vehicle_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.vehicle
    OWNER to postgres;