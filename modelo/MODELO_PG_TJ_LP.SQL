/*
Created: 01/05/2017
Modified: 22/03/2018
Model: FormulaVR_V10_PostgreSQL
Database: PostgreSQL 9.2
*/





-- Drop keys for tables section -------------------------------------------------

ALTER TABLE "FormulaVR"."T_LP_labels2print" DROP CONSTRAINT "Key1"
;
ALTER TABLE "FormulaVR"."T_TJ_tarjetasPrepago" DROP CONSTRAINT "PK_T_TJ_tarjetasPrepago"
;



-- Drop views section --------------------------------------------------- 

DROP VIEW "FormulaVR"."V_LP_RTV_labels2print"
;
DROP VIEW "FormulaVR"."V_TJ_RTV_tarjetasPrepago"
;

-- Drop tables section ---------------------------------------------------

DROP TABLE "FormulaVR"."T_LP_labels2print"
;
DROP TABLE "FormulaVR"."T_TJ_tarjetasPrepago"
;

-- Create tables section -------------------------------------------------

-- Table FormulaVR.T_TJ_tarjetasPrepago

CREATE TABLE "FormulaVR"."T_TJ_tarjetasPrepago"(
 "sincro" Character varying(20) DEFAULT '' NOT NULL,
 "mark" Character(1) DEFAULT '' NOT NULL,
 "is_deleted" Character(1) DEFAULT '' NOT NULL,
 "author" Character varying(50) DEFAULT '' NOT NULL,
 "card_id" Character varying(50) DEFAULT '' NOT NULL,
 "user_id" Character varying(50) DEFAULT '' NOT NULL,
 "balance_initial" Numeric(12,3) NOT NULL,
 "balance_current" Numeric(12,3) NOT NULL,
 "last_sale_amount" Numeric(12,3) NOT NULL,
 "last_sale_moment" Character varying(20) DEFAULT '' NOT NULL,
 "qr_image_base64" Text NOT NULL,
 "json" Text NOT NULL
)
WITH (OIDS=FALSE)
;
COMMENT ON COLUMN "FormulaVR"."T_TJ_tarjetasPrepago"."sincro" IS 'Momento inserción: aammddhhMMssDDD'
;
COMMENT ON COLUMN "FormulaVR"."T_TJ_tarjetasPrepago"."last_sale_moment" IS 'aammddhhMMssDDD'
;

-- Add keys for table FormulaVR.T_TJ_tarjetasPrepago

ALTER TABLE "FormulaVR"."T_TJ_tarjetasPrepago" ADD CONSTRAINT "PK_T_TJ_tarjetasPrepago" PRIMARY KEY ("card_id")
;

-- Table FormulaVR.T_LP_labels2print

CREATE TABLE "FormulaVR"."T_LP_labels2print"(
 "card_id" Character varying(50) DEFAULT '' NOT NULL,
 "json" Text NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table FormulaVR.T_LP_labels2print

ALTER TABLE "FormulaVR"."T_LP_labels2print" ADD CONSTRAINT "Key1" PRIMARY KEY ("card_id")
;

-- Create views section -------------------------------------------------

CREATE VIEW "FormulaVR"."V_TJ_RTV_tarjetasPrepago" AS
SELECT "a"."sincro", "a"."mark", "a"."is_deleted", "a"."author", "a"."card_id", "a"."user_id", "a"."balance_initial", "a"."balance_current", "a"."last_sale_amount", "a"."last_sale_moment", "a"."qr_image_base64", "a"."json"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_TJ_tarjetasPrepago" "a"
;

CREATE VIEW "FormulaVR"."V_LP_RTV_labels2print" AS
SELECT "a"."card_id", "b"."user_id" AS "TJ_user_id", "b"."qr_image_base64" AS "TJ_qr_image_base64", "a"."json"
FROM 


-- COD.AUTOGENERADO. Para fijarlo copiar en el apartado 'Notes'...

 "FormulaVR"."T_LP_labels2print" "a"

 LEFT JOIN "FormulaVR"."T_TJ_tarjetasPrepago" "b"
        ON "b"."card_id" = "a"."card_id"
;



