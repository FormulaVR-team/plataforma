/*
Created: 01/05/2017
Modified: 18/07/2017
Model: FormulaVR_V10_PostgreSQL
Database: PostgreSQL 9.2
*/



-- Drop views section --------------------------------------------------- 

DROP VIEW "FormulaVR"."V_PM_RTV_promosManuales"
;
DROP VIEW "FormulaVR"."V_PS_RTV_countries"
;
DROP VIEW "FormulaVR"."V_TS_RTV_timeSlicesSumLocFecHor"
;
DROP VIEW "FormulaVR"."V_TS_RTV_timeSlices"
;
DROP VIEW "FormulaVR"."V_PR_RTV_promos"
;
DROP VIEW "FormulaVR"."V_PY_RTV_PayPalTokens"
;
DROP VIEW "FormulaVR"."V_PA_RTV_systemParameters"
;
DROP VIEW "FormulaVR"."V_SG_RTV_publicProcesses"
;
DROP VIEW "FormulaVR"."V_TT_RTV_timeTableReference"
;
DROP VIEW "FormulaVR"."V_LO_RTV_location"
;
DROP VIEW "FormulaVR"."V_CP_RTV_cockpits"
;
DROP VIEW "FormulaVR"."V_LG_RTV_logs"
;
DROP VIEW "FormulaVR"."V_TK_RTV_tokens"
;
DROP VIEW "FormulaVR"."V_RS_RTV_reservations"
;
DROP VIEW "FormulaVR"."V_PT_RTV_products"
;
DROP VIEW "FormulaVR"."V_US_RTV_users"
;

-- Create views section -------------------------------------------------

CREATE VIEW "FormulaVR"."V_US_RTV_users" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."user_id", "a"."role_id", "a"."hash_code", "a"."country_id", "b"."name" AS "PS_name", "b"."flag_base64" AS "PS_flag_base64", "a"."nick", "a"."password", "a"."first_name", "a"."last_name", "a"."phone", "a"."gender", "a"."birth_day", "a"."avatar", "a"."location_id", "a"."json"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_US_users" "a"

 LEFT JOIN "FormulaVR"."T_PS_countries" "b"
        ON "b"."country_id" = "a"."country_id"
;

CREATE VIEW "FormulaVR"."V_PT_RTV_products" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."product_id", "a"."name", "a"."whoCanSelect_AFU", "a"."deadline", "a"."isPercent", "a"."amount", "a"."currency", "a"."duration_minutes", "a"."json"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_PT_products" "a"
;

CREATE VIEW "FormulaVR"."V_RS_RTV_reservations" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."reservation_id", "a"."location_id", "f"."name" AS "LO_name", "a"."user_id", "b"."country_id" AS "US_country_id", "b"."nick" AS "US_nick", "b"."avatar" AS "US_avatar", "b"."role_id" AS "US_is_admin", "b"."first_name" AS "US_first_name", "b"."last_name" AS "US_last_name", "a"."product_id", "c"."name" AS "PT_name", "c"."deadline" AS "PT_deadline", "c"."isPercent" AS "PT_isPercent", "c"."amount" AS "PT_amount", "c"."duration_minutes" AS "PT_duration_minutes", "a"."quantity", "a"."product_id2", "d"."name" AS "PT_name2", "d"."deadline" AS "PT_deadline2", "d"."isPercent" AS "PT_isPercent2", "d"."amount" AS "PT_amount2", "a"."product_id3", "e"."name" AS "PT_name3", "e"."deadline" AS "PT_deadline3", "e"."isPercent" AS "PT_isPercent3", "e"."amount" AS "PT_amount3", "a"."amount", "a"."currency", "a"."phone", "a"."pay_status", "a"."start_date", "a"."start_time", "a"."duration_minutes", "a"."places", "a"."coupon_id", "a"."executed_at", "a"."note", "a"."comment", "a"."json"
FROM 

 "FormulaVR"."T_RS_reservations" "a"

 LEFT JOIN "FormulaVR"."T_US_users" "b"
        ON "b"."user_id" = "a"."user_id"

 LEFT JOIN "FormulaVR"."T_PT_products" "c"
        ON "c"."product_id" = "a"."product_id"

 LEFT JOIN "FormulaVR"."T_PT_products" "d"
        ON "d"."product_id" = "a"."product_id2"

 LEFT JOIN "FormulaVR"."T_PT_products" "e"
        ON "e"."product_id" = "a"."product_id3"

 LEFT JOIN "FormulaVR"."T_LO_location" "f"
        ON "f"."location_id" = "a"."location_id"
;

CREATE VIEW "FormulaVR"."V_TK_RTV_tokens" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."token_id", "a"."json"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_TK_tokens" "a"
;

CREATE VIEW "FormulaVR"."V_LG_RTV_logs" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."serial", "a"."text_1", "a"."text_2", "a"."text_3", "a"."json"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_LG_logs" "a"
ORDER BY "a"."serial" DESC
;

CREATE VIEW "FormulaVR"."V_CP_RTV_cockpits" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."location_id", "b"."name" AS "LO_name", "b"."address" AS "LO_address", "b"."city" AS "LO_city", "b"."postal_code" AS "LO_postal_code", "b"."lat" AS "LO_lat", "b"."lon" AS "LO_lon", "a"."cockpit_id", "a"."serial_number", "a"."name", "a"."isBlocked", "a"."asignation_order", "a"."install_date", "a"."reset_date_used", "a"."hours_used", "a"."note", "a"."comment", "a"."observation", "a"."warning", "a"."contact_service"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_CP_cockpits" "a"

 LEFT JOIN "FormulaVR"."T_LO_location" "b"
        ON "b"."location_id" = "a"."location_id"
;

CREATE VIEW "FormulaVR"."V_LO_RTV_location" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."location_id", "a"."name", "a"."address", "a"."city", "a"."postal_code", "a"."province", "a"."state_region", "a"."country", "a"."lat", "a"."lon", "a"."comment", "a"."json"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_LO_location" "a"
;

CREATE VIEW "FormulaVR"."V_TT_RTV_timeTableReference" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."location_id", "a"."day_type", "a"."start_time", "a"."duration_minutes", "a"."isBlocked", "a"."json"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_TT_timeTableReference" "a"
;

CREATE VIEW "FormulaVR"."V_SG_RTV_publicProcesses" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."role_id", "a"."process_id", "a"."json"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_SG_publicProcesses" "a"
;

CREATE VIEW "FormulaVR"."V_PA_RTV_systemParameters" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."group_id", "a"."key", "a"."value"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_PA_systemParameters" "a"
;

CREATE VIEW "FormulaVR"."V_PY_RTV_PayPalTokens" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."user_id", "b"."first_name" AS "US_first_name", "b"."last_name" AS "US_last_name", "a"."reservation_id", "a"."paypal_token_id", "a"."paypal_usr", "a"."paypal_pwd", "a"."paypal_signature", "a"."stsProceso", "a"."json"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_PY_PayPalTokens" "a"

 LEFT JOIN "FormulaVR"."T_US_users" "b"
        ON "b"."user_id" = "a"."user_id"
;

CREATE VIEW "FormulaVR"."V_PR_RTV_promos" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."location_id", "d"."name" AS "LO_name", "a"."product_id", "b"."name" AS "PT_name", "a"."product_id_promo", "c"."name" AS "PT_name_promo", "a"."deadline", "a"."min_quantity"
FROM 

 "FormulaVR"."T_PR_promos" "a"

 LEFT JOIN "FormulaVR"."T_PT_products" "b"
        ON "b"."product_id" = "a"."product_id"

 LEFT JOIN "FormulaVR"."T_PT_products" "c"
        ON "c"."product_id" = "a"."product_id_promo"

 LEFT JOIN "FormulaVR"."T_LO_location" "d"
        ON "d"."location_id" = "a"."location_id"
;

CREATE VIEW "FormulaVR"."V_TS_RTV_timeSlices" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."reservation_id", "b"."user_id" AS "RS_user_id", "b"."location_id" AS "RS_location_id", "b"."start_date" AS "RS_start_date", "b"."start_time" AS "RS_start_time", "b"."pay_status" AS "RS_pay_status", "b"."product_id" AS "RS_product_id", "b"."quantity" AS "RS_quantity", "b"."duration_minutes" AS "RS_duration_minutes", "b"."places" AS "RS_places", "a"."start_date", "a"."start_time", "a"."json"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_TS_timeSlices" "a"

 LEFT JOIN "FormulaVR"."T_RS_reservations" "b"
        ON "b"."reservation_id" = "a"."reservation_id"
;

CREATE VIEW "FormulaVR"."V_TS_RTV_timeSlicesSumLocFecHor"
 AS
SELECT
      "RS_location_id" as location_id
    , "start_date"
    , "start_time"
    , count(reservation_id) as "nReservations"
    , sum("RS_places") as "nPlaces"
 FROM "FormulaVR"."V_TS_RTV_timeSlices"
 WHERE ("RS_pay_status" like '%OK%' or  "RS_pay_status" = 'CASH_PDT')
 GROUP BY
      "RS_location_id"
    , "start_date"
    , "start_time"  
 ORDER BY
      "RS_location_id"
    , "start_date"
    , "start_time"  
;

;

CREATE VIEW "FormulaVR"."V_PS_RTV_countries" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."country_id", "a"."name", "a"."alpha_2", "a"."alpha_3", "a"."flag_base64", "a"."json"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_PS_countries" "a"
;

CREATE VIEW "FormulaVR"."V_PM_RTV_promosManuales" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."coupon_id", "a"."name", "a"."uses_per_user", "a"."product_id", "b"."name" AS "PT_name", "b"."whoCanSelect_AFU" AS "PT_whoCanSelect_AFU", "b"."deadline" AS "PT_deadline", "a"."location_id", "c"."name" AS "LO_name", "a"."deadline", "a"."json"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_PM_promosManuales" "a"

 LEFT JOIN "FormulaVR"."T_PT_products" "b"
        ON "b"."product_id" = "a"."product_id"

 LEFT JOIN "FormulaVR"."T_LO_location" "c"
        ON "c"."location_id" = "a"."location_id"
;



