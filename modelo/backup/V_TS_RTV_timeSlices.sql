SELECT 
--	  sincro
--	, mark
--	, is_deleted
--	, author
	  reservation_id, 
--	, "RS_user_id"
	  "RS_location_id"
	, "RS_start_date"
	, "RS_start_time"
--	, "RS_pay_status"
--	, "RS_product_id"
--	, "RS_quantity"
--	, "RS_duration_minutes"
	, "RS_places"
--	, slice_id
--	, json
  FROM "FormulaVR"."V_TS_RTV_timeSlices"
  WHERE 
	    "RS_pay_status" = 'OK'
	and "RS_location_id" = 'CENTRAL'
	and "RS_start_date"
	and "RS_start_time"
  GROUP BY
--	  reservation_id ,
	  "RS_location_id"
	, "RS_start_date"
	, "RS_start_time"
  ;
