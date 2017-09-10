/*
Created: 01/05/2017
Modified: 10/09/2017
Model: FormulaVR_V10_PostgreSQL
Database: PostgreSQL 9.2
*/





-- Drop keys for tables section -------------------------------------------------

ALTER TABLE "FormulaVR"."T_AC_activityCockpits" DROP CONSTRAINT "PK_T_AC_actividadCocpits"
;



-- Drop views section --------------------------------------------------- 

DROP VIEW "FormulaVR"."V_AC_RTV_activityCockpits"
;

-- Drop tables section ---------------------------------------------------

DROP TABLE "FormulaVR"."T_AC_activityCockpits"
;

-- Create tables section -------------------------------------------------

-- Table FormulaVR.T_AC_activityCockpits

CREATE TABLE "FormulaVR"."T_AC_activityCockpits"(
 "sincro" Character varying(20) DEFAULT '' NOT NULL,
 "mark" Character(1) DEFAULT '' NOT NULL,
 "is_deleted" Character(1) DEFAULT '' NOT NULL,
 "author" Character varying(50) DEFAULT '' NOT NULL,
 "serial" Serial NOT NULL,
 "location_id" Character varying(50) DEFAULT '' NOT NULL,
 "computername" Text NOT NULL,
 "filename" Text NOT NULL,
 "content" Text NOT NULL,
 "json" Text NOT NULL
)
WITH (OIDS=FALSE)
;
COMMENT ON COLUMN "FormulaVR"."T_AC_activityCockpits"."sincro" IS 'Momento inserción: aammddhhMMssDDD'
;

-- Add keys for table FormulaVR.T_AC_activityCockpits

ALTER TABLE "FormulaVR"."T_AC_activityCockpits" ADD CONSTRAINT "PK_T_AC_actividadCocpits" PRIMARY KEY ("serial")
;

-- Create views section -------------------------------------------------

CREATE VIEW "FormulaVR"."V_AC_RTV_activityCockpits" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."serial", "a"."location_id", "b"."name" AS "LO_name", "a"."computername", "a"."filename", "a"."content", "a"."json", COALESCE( substring( filename from 1 for 7), '') AS "aaaa_mm", COALESCE( substring( filename from 1 for 10), '') AS "aaaa_mm_dd", COALESCE( substring( filename from 1 for 10) || ' ' || substring( filename from 12 for 2), '') AS "aaaa_mm_dd_hh", COALESCE( substring( filename from 1 for 10) || ' ' || substring( filename from 12 for 2) || ':' || substring( filename from 15 for 1) || '0', '') AS "aaaa_mm_dd_hh_m0", COALESCE( substring( filename from 1 for 10) || ' ' || substring( filename from 12 for 2) || ':' || substring( filename from 15 for 2), '') AS "aaaa_mm_dd_hh_mm", COALESCE( substring( filename from 1 for 10) || ' ' || substring( filename from 12 for 2) || ':' || substring( filename from 15 for 2) || ':' || substring( filename from 18 for 2), '') AS "aaaa_mm_dd_hh_mm_ss"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_AC_activityCockpits" "a"

 LEFT JOIN "FormulaVR"."T_LO_location" "b"
        ON "b"."location_id" = "a"."location_id"
;



