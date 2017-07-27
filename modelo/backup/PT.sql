--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.0
-- Dumped by pg_dump version 9.4.0
-- Started on 2017-06-18 17:19:29

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = "FormulaVR", pg_catalog;

--
-- TOC entry 2108 (class 0 OID 50434)
-- Dependencies: 173
-- Data for Name: T_PT_products; Type: TABLE DATA; Schema: FormulaVR; Owner: postgres
--

INSERT INTO "T_PT_products" VALUES ('170616202608418', ' ', ' ', 'SYS', '0100', 'Introducción BRIEF+10 min', 'AFU', '2099-12-31', ' ', 5.000, '€', 10, '');
INSERT INTO "T_PT_products" VALUES ('170616202717421', ' ', ' ', 'SYS', '0210', 'Prácticas 10 min', 'AFU', '2099-12-31', ' ', 5.000, '€', 10, '');
INSERT INTO "T_PT_products" VALUES ('170616202823699', ' ', ' ', 'SYS', '0220', 'Prácticas 20 min', 'AFU', '2099-12-31', ' ', 10.000, '€', 20, '');
INSERT INTO "T_PT_products" VALUES ('170616202949165', ' ', ' ', 'SYS', '0230', 'Prácticas 30 min', 'AFU', '2099-12-31', ' ', 15.000, '€', 30, '');
INSERT INTO "T_PT_products" VALUES ('170616203050881', ' ', ' ', 'SYS', '0260', 'Prácticas 60 min', 'AFU', '2099-12-31', ' ', 29.000, '€', 60, '');
INSERT INTO "T_PT_products" VALUES ('170616203528931', ' ', ' ', 'SYS', '0300', 'Carrera 30: 5Train + 10Qual + 15Race', 'AFU', '2099-12-31', ' ', 15.000, '€', 30, '');
INSERT INTO "T_PT_products" VALUES ('170614141030159', ' ', ' ', 'SYS', 'DP10', 'Descuento del 10%', 'AF', '2099-12-31', 'S', -10.000, '', 0, '');
INSERT INTO "T_PT_products" VALUES ('170614141408984', ' ', ' ', 'SYS', 'D5', 'Descuento 5 euros', 'AF', '2099-12-31', ' ', -5.000, '€', 0, '');
INSERT INTO "T_PT_products" VALUES ('170614154851981', ' ', ' ', 'SYS', 'RP5', 'Recargo de 5%', 'AF', '2099-12-31', 'S', 5.000, '', 0, '');
INSERT INTO "T_PT_products" VALUES ('170614141127972', ' ', ' ', 'SYS', 'DP100', 'Gratis', 'A', '2099-12-31', 'S', -100.000, '', 0, '');
INSERT INTO "T_PT_products" VALUES ('170614154815664', ' ', ' ', 'SYS', 'R5', 'Recargo de 5 euros', 'AF', '2099-12-31', ' ', 5.000, '€', 0, '');
INSERT INTO "T_PT_products" VALUES ('170617165437831', ' ', ' ', 'SYS', 'DP50', 'Descuento del 50%', 'AF', '2099-12-31', 'S', -50.000, '', 0, '');
INSERT INTO "T_PT_products" VALUES ('170617173854238', ' ', ' ', 'SYS', 'DP30', 'Descuento del 30%', 'AF', '2099-12-31', 'S', -30.000, '', 0, '');
INSERT INTO "T_PT_products" VALUES ('170617183801878', ' ', ' ', 'SYS', 'DP25', 'Descuento del 25%', 'AF', '2099-12-31', 'S', -25.000, '', 0, '');


-- Completed on 2017-06-18 17:19:29

--
-- PostgreSQL database dump complete
--

