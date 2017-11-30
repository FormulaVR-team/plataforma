/*
Created: 01/05/2017
Modified: 30/11/2017
Model: FormulaVR_V10_PostgreSQL
Database: PostgreSQL 9.2
*/



-- Drop views section --------------------------------------------------- 

DROP VIEW "FormulaVR"."V_ES_RTV_eventSusbscriptions"
;

-- Create views section -------------------------------------------------

CREATE VIEW "FormulaVR"."V_ES_RTV_eventSusbscriptions" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."event_id", "b"."location_id" AS "EV_location_id", "c"."name" AS "LO_name", "a"."inscription_user_id", "a"."first_name", "a"."last_name", "a"."phone", "a"."amount", "a"."currency", "a"."pay_status", "a"."json"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_ES_eventSusbscriptions" "a"

 LEFT JOIN "FormulaVR"."T_EV_events" "b"
        ON "b"."event_id" = "a"."event_id"

 LEFT JOIN "FormulaVR"."T_LO_location" "c"
        ON "c"."location_id" = "b"."location_id"
;



