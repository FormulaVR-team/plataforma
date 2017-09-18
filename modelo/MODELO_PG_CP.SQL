/*
Created: 01/05/2017
Modified: 18/09/2017
Model: FormulaVR_V10_PostgreSQL
Database: PostgreSQL 9.2
*/





-- Drop keys for tables section -------------------------------------------------

ALTER TABLE "FormulaVR"."T_CP_cockpits" DROP CONSTRAINT "PK_T_CP_cockpits"
;



-- Drop views section --------------------------------------------------- 

DROP VIEW "FormulaVR"."V_CP_RTV_cockpits"
;

-- Drop tables section ---------------------------------------------------

DROP TABLE "FormulaVR"."T_CP_cockpits"
;

-- Create tables section -------------------------------------------------

-- Table FormulaVR.T_CP_cockpits

CREATE TABLE "FormulaVR"."T_CP_cockpits"(
 "sincro" Character varying(20) DEFAULT '' NOT NULL,
 "mark" Character(1) DEFAULT '' NOT NULL,
 "is_deleted" Character(1) DEFAULT '' NOT NULL,
 "author" Character varying(50) DEFAULT '' NOT NULL,
 "location_id" Character varying(50) DEFAULT '' NOT NULL,
 "cockpit_id" Character varying(50) DEFAULT '' NOT NULL,
 "serial_number" Text DEFAULT '' NOT NULL,
 "name" Text DEFAULT '' NOT NULL,
 "isBlocked" Character(1) DEFAULT '' NOT NULL,
 "asignation_order" Integer DEFAULT 0 NOT NULL,
 "install_date" Character(10) DEFAULT '2099-12-31' NOT NULL,
 "reset_date_used" Character(10) DEFAULT '2099-12-31' NOT NULL,
 "hours_used" Integer DEFAULT 0 NOT NULL,
 "note" Text DEFAULT '' NOT NULL,
 "comment" Text DEFAULT '' NOT NULL,
 "observation" Text DEFAULT '' NOT NULL,
 "warning" Text DEFAULT '' NOT NULL,
 "contact_service" Text DEFAULT '' NOT NULL,
 "json" Text
)
WITH (OIDS=FALSE)
;
COMMENT ON COLUMN "FormulaVR"."T_CP_cockpits"."sincro" IS 'Momento inserción: aammddhhMMssDDD'
;
COMMENT ON COLUMN "FormulaVR"."T_CP_cockpits"."isBlocked" IS 'Avería, bloqueado, etc...'
;
COMMENT ON COLUMN "FormulaVR"."T_CP_cockpits"."asignation_order" IS '10, 20, 30, etc...'
;
COMMENT ON COLUMN "FormulaVR"."T_CP_cockpits"."install_date" IS 'Fecha de su instalación.'
;
COMMENT ON COLUMN "FormulaVR"."T_CP_cockpits"."reset_date_used" IS 'Fecha del último reset de horas de uso.'
;
COMMENT ON COLUMN "FormulaVR"."T_CP_cockpits"."hours_used" IS 'Horas usada desde último reset.'
;
COMMENT ON COLUMN "FormulaVR"."T_CP_cockpits"."note" IS 'Este texto se muestra al usuario.'
;
COMMENT ON COLUMN "FormulaVR"."T_CP_cockpits"."comment" IS 'Este texto solo lo ven administradores.'
;
COMMENT ON COLUMN "FormulaVR"."T_CP_cockpits"."observation" IS 'Este texto solo lo ven administradores.'
;
COMMENT ON COLUMN "FormulaVR"."T_CP_cockpits"."warning" IS 'Este texto solo lo ven administradores.'
;
COMMENT ON COLUMN "FormulaVR"."T_CP_cockpits"."contact_service" IS 'Datos contacto servicio técnico.'
;

-- Add keys for table FormulaVR.T_CP_cockpits

ALTER TABLE "FormulaVR"."T_CP_cockpits" ADD CONSTRAINT "PK_T_CP_cockpits" PRIMARY KEY ("cockpit_id","location_id")
;

-- Create views section -------------------------------------------------

CREATE VIEW "FormulaVR"."V_CP_RTV_cockpits" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."location_id", "b"."name" AS "LO_name", "b"."address" AS "LO_address", "b"."city" AS "LO_city", "b"."postal_code" AS "LO_postal_code", "b"."lat" AS "LO_lat", "b"."lon" AS "LO_lon", "a"."cockpit_id", "a"."serial_number", "a"."name", "a"."isBlocked", "a"."asignation_order", "a"."install_date", "a"."reset_date_used", "a"."hours_used", "a"."note", "a"."comment", "a"."observation", "a"."warning", "a"."contact_service", "a"."json"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_CP_cockpits" "a"

 LEFT JOIN "FormulaVR"."T_LO_location" "b"
        ON "b"."location_id" = "a"."location_id"
;



