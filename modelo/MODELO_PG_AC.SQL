/*
Created: 01/05/2017
Modified: 04/08/2017
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
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."serial", "a"."computername", "a"."filename", "a"."content", "a"."json"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_AC_activityCockpits" "a"
;




GRANT USAGE ON SCHEMA "FormulaVR" TO public;

GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."T_AC_activityCockpits" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."T_CP_cockpits" TO public;
GRANT ALL                ON TABLE "FormulaVR"."T_LG_logs" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."T_LO_location" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."T_PA_systemParameters" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."T_PM_promosManuales" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."T_PR_promos" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."T_PS_countries" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."T_PT_products" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."T_PY_PayPalTokens" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."T_RS_reservations" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."T_SG_publicProcesses" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."T_TK_tokens" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."T_TS_timeSlices" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."T_TT_timeTableReference" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."T_US_users" TO public;

GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."V_AC_RTV_activityCockpits" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."V_CP_RTV_cockpits" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."V_LG_RTV_logs" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."V_LO_RTV_location" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."V_PA_RTV_systemParameters" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."V_PM_RTV_promosManuales" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."V_PR_RTV_promos" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."V_PS_RTV_countries" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."V_PT_RTV_products" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."V_PY_RTV_PayPalTokens" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."V_RS_RTV_reservations" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."V_SG_RTV_publicProcesses" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."V_TK_RTV_tokens" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."V_TS_RTV_timeSlices" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."V_TT_RTV_timeTableReference" TO public;
GRANT SELECT, REFERENCES ON TABLE "FormulaVR"."V_US_RTV_users" TO public;
