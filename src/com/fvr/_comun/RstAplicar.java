
package com.fvr._comun;

import java.util.List;

/**
 * @author Emilio Estecha 2013
 *
 */
public class RstAplicar {
	
	public static final boolean isPostgres = true;
    
    public String rwUpperCase = null;
    public String rwLike = null;
    public String rwAnyString = null;
    public final String COMILLAS = "\""; // SI ES POSTGRESQL poner ===>  "\"";
    public static final String APOSTROFO = "'"; // SI ES POSTGRESQL poner ===>  "'";
    
    public RstAplicar( String rwUpperCase, String rwLike, String rwAnyString ) {
		this.rwUpperCase = rwUpperCase;
		this.rwLike = rwLike;
		this.rwAnyString = rwAnyString;
    }
    
    public static String escapeChars(String rst) {
    	if (isPostgres && null!=rst) {
    		rst = rst.replace("''","'");	// Si vienen dos se pone uno para evitar multiplicaciones por si se llama dos  o más veces.
    		return rst.replace("'","''");	// Si viene un apóstrofo se fuerza a que sea el carácter apóstrofo
    	}
    	return rst;
    }
    
    public String getCHAR_EQ_Estricto(String rst, String campo, String sqlWhere){
		if (sqlWhere.trim().length()>0) sqlWhere += " AND "; else sqlWhere = " WHERE ";
		sqlWhere += COMILLAS + campo + COMILLAS;
		if (rst == null){
		    sqlWhere += " IS null";
		} else if ( rst != null && rst.trim().length()== 0 ) {
		    sqlWhere += " = ''";
		} else if ( rst != null && rst.trim().length() > 0 ) {
		    sqlWhere += " = '"  + rst.trim() + "'";
		}
		return sqlWhere;
    }
    
    public String getCHAR_NE_Estricto(String rst, String campo, String sqlWhere){
		if (sqlWhere.trim().length()>0) sqlWhere += " AND "; else sqlWhere = " WHERE ";
		sqlWhere += COMILLAS + campo + COMILLAS;
		if (rst == null){
		    sqlWhere += " IS NOT null";
		} else if ( rst != null && rst.trim().length()== 0 ) {
		    sqlWhere += " <> ''";
		} else if ( rst != null && rst.trim().length() > 0 ) {
		    sqlWhere += " <> '"  + rst.trim() + "'";
		}
		return sqlWhere;
    }
    
    public String getCHAR_LIKE(String rst, String campo, String sqlWhere){
		//Para evitar distinción de cadenas por mayúsculas y minúsculas,
		//se transforman las cadenas a mayúsculas con la funcion de SQL 'UPPER'.
		if ( rst != null && rst.trim().length() > 0 ) {
		    if (sqlWhere.trim().length()>0) sqlWhere += " AND " + rwUpperCase +"("; else sqlWhere = " WHERE " + rwUpperCase +"(";
		    sqlWhere += COMILLAS + campo + COMILLAS;
		    sqlWhere += ") " + rwLike + " '" + rwAnyString  + rst.trim().toUpperCase() + rwAnyString + "'";
		}
		return sqlWhere;
    }
    
    public String getCHAR_EQ(String rst, String campo, String sqlWhere){
		if ( rst != null && rst.trim().length() > 0 ) {
		    if (sqlWhere.trim().length()>0) sqlWhere += " AND "; else sqlWhere = " WHERE ";
		    sqlWhere += COMILLAS + campo + COMILLAS;
		    sqlWhere += " = '"  + rst.trim() + "'";
		}
		return sqlWhere;
    }
    
    public String getCHAR_NE(String rst, String campo, String sqlWhere){
		if ( rst != null && rst.trim().length() > 0 ) {
		    if (sqlWhere.trim().length()>0) sqlWhere += " AND "; else sqlWhere = " WHERE ";
		    sqlWhere += COMILLAS + campo + COMILLAS;
		    sqlWhere += " <> '"  + rst.trim() + "'";
		}
		return sqlWhere;
    }
    
    public String getNUM_EQ_Estricto(String rst, String campo, String sqlWhere){
		if (sqlWhere.trim().length()>0) sqlWhere += " AND "; else sqlWhere = " WHERE ";
		sqlWhere += COMILLAS + campo + COMILLAS;
		if (rst == null){
		    sqlWhere += " IS null";
		} else if ( rst != null && rst.trim().length()== 0 ) {
		    sqlWhere += " = 0";
		} else if ( rst != null && rst.trim().length() > 0 ) {
		    sqlWhere += " = "  + rst.trim() + "";
		}
		return sqlWhere;
    }
    
    public String getNUM_NE_Estricto(String rst, String campo, String sqlWhere){
		if (sqlWhere.trim().length()>0) sqlWhere += " AND "; else sqlWhere = " WHERE ";
		sqlWhere += COMILLAS + campo + COMILLAS;
		if (rst == null){
		    sqlWhere += " IS NOT null";
		} else if ( rst != null && rst.trim().length()== 0 ) {
		    sqlWhere += " <> 0";
		} else if ( rst != null && rst.trim().length() > 0 ) {
		    sqlWhere += " <> "  + rst.trim() + "";
		}
		return sqlWhere;
    }
    
    public String getNUM_EQ(String rst, String campo, String sqlWhere){
		if ( rst != null && rst.trim().length() > 0 ) {
		    if (sqlWhere.trim().length()>0) sqlWhere += " AND "; else sqlWhere = " WHERE ";
		    sqlWhere += COMILLAS + campo + COMILLAS;
		    sqlWhere += " = "  + rst.trim() + "";
		}
		return sqlWhere;
    }
    
    public String getNUM_NE(String rst, String campo, String sqlWhere){
		if ( rst != null && rst.trim().length() > 0 ) {
		    if (sqlWhere.trim().length()>0) sqlWhere += " AND "; else sqlWhere = " WHERE ";
		    sqlWhere += COMILLAS + campo + COMILLAS;
		    sqlWhere += " <> "  + rst.trim() + "";
		}
		return sqlWhere;
    }
    
    public String getNUM_GE(String rst, String campo, String sqlWhere){
		if ( rst != null && rst.trim().length() > 0 ) {
		    if (sqlWhere.trim().length()>0) sqlWhere += " AND "; else sqlWhere = " WHERE ";
		    sqlWhere += COMILLAS + campo + COMILLAS;
		    sqlWhere += " >= "  + rst.trim() + "";
		}
		return sqlWhere;
    }
    
    public String getNUM_GT(String rst, String campo, String sqlWhere){
		if ( rst != null && rst.trim().length() > 0 ) {
		    if (sqlWhere.trim().length()>0) sqlWhere += " AND "; else sqlWhere = " WHERE ";
		    sqlWhere += COMILLAS + campo + COMILLAS;
		    sqlWhere += " > "  + rst.trim() + "";
		}
		return sqlWhere;
    }

    public String getNUM_LT(String rst, String campo, String sqlWhere){
		if ( rst != null && rst.trim().length() > 0 ) {
		    if (sqlWhere.trim().length()>0) sqlWhere += " AND "; else sqlWhere = " WHERE ";
		    sqlWhere += COMILLAS + campo + COMILLAS;
		    sqlWhere += " < "  + rst.trim() + "";
		}
		return sqlWhere;
    }

    public String getNUM_LE(String rst, String campo, String sqlWhere){
		if ( rst != null && rst.trim().length() > 0 ) {
		    if (sqlWhere.trim().length()>0) sqlWhere += " AND "; else sqlWhere = " WHERE ";
		    sqlWhere += COMILLAS + campo + COMILLAS;
		    sqlWhere += " <= "  + rst.trim() + "";
		}
		return sqlWhere;
    }

    public String getCHAR_IN(final List<String> aLista, final String campo, String sqlWhere) {
        // WHERE "T_NP_NotificPerfiles"."TipoNotificacion" IN ('0010', '0050', '0100');
    	if ( aLista != null && aLista.size() > 0 ) {
    		if (sqlWhere.trim().length()>0) sqlWhere += " AND "; else sqlWhere = " WHERE ";
    		sqlWhere += COMILLAS + campo + COMILLAS;
    		sqlWhere += " IN (";
    		boolean primeraVez = true;
    		for( String item : aLista ) {
    			if ( item != null && item.trim().length() > 0 ) {

    				item = escapeChars(item);

    				if ( ! primeraVez ) {
        				sqlWhere += ",";
    				}
    				sqlWhere += APOSTROFO + item.trim() + APOSTROFO;
    				primeraVez = false;
    			}
    		}
    		sqlWhere += ") ";
    	}
    	return sqlWhere;
    }

    public String getCHAR_IN_Estricto(final List<String> aLista, final String campo, String sqlWhere) {
        // WHERE "T_NP_NotificPerfiles"."TipoNotificacion" IN ('0010', '0050', '0100');
    	if ( aLista != null && aLista.size() > 0 ) {
    		if (sqlWhere.trim().length()>0) sqlWhere += " AND "; else sqlWhere = " WHERE ";
    		sqlWhere += COMILLAS + campo + COMILLAS;
    		sqlWhere += " IN (";
    		boolean primeraVez = true;
    		for( String item : aLista ) {
//    			if ( item != null && item.trim().length() > 0 ) {

    				item = escapeChars(item);

    				if ( ! primeraVez ) {
        				sqlWhere += ",";
    				}
    				sqlWhere += APOSTROFO + item.trim() + APOSTROFO;
    				primeraVez = false;
//    			}
    		}
    		sqlWhere += ") ";
    	}
    	return sqlWhere;
    }
    
}
