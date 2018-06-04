-- Database: postgres1

-- DROP DATABASE postgres1;

CREATE DATABASE postgres1
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Russian_Russia.1251'
       LC_CTYPE = 'Russian_Russia.1251'
       CONNECTION LIMIT = -1;

CREATE TABLE public.students
(
  id bigint NOT NULL,
  name character varying(255),
  passpost_number character varying(255),
  CONSTRAINT student_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.students
  OWNER TO postgres;


insert into students values(1,'al','e1');


insert into students values(2,'al1','e2');
insert into students values(3,'a2','e3');