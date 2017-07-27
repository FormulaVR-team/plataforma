package com.fvr._comun.test;

import java.io.File;

import org.apache.commons.codec.binary.Base64;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr.ps_countries.bean.PsBean;

public class CargarBanderas {

	public static void main(String[] args) throws StExcepcion {
		System.out.println(">>>>>>>>>>>");
		
		BDConexion dataBase = new BDConexion();
		
		cargar(dataBase);

		System.out.println("<<<<<<<<<<<");
	}

	private static void cargar(BDConexion dataBase) throws StExcepcion {
		com.fvr.ps_countries.db.PsAccesoBaseDatos dao_ps = new com.fvr.ps_countries.db.PsAccesoBaseDatos();
		com.fvr.ps_countries.bean.PsBeanFiltro    flt_ps = new com.fvr.ps_countries.bean.PsBeanFiltro();
		com.fvr.ps_countries.bean.PsBean[]        rgs_ps = dao_ps.ps_getSeq(dataBase, new ConfigPantalla(Integer.MAX_VALUE), flt_ps);

		if ( rgs_ps != null ) {
			String camino = "C:\\datos\\java\\_eclipse workspace J2EE\\FormulaVR\\modelo\\docs\\paises_y_banderas\\flags\\32x32";
			File dir = new File(camino);
			File[] bandera = null;
			String base64 = null;
			for ( PsBean item : rgs_ps ) {
				bandera = Subrutinas.getFiles_startsWith(dir,item.getPs_alpha_2());
				if ( bandera != null && bandera.length > 0 ) {
					File file = bandera[0];
					byte[] bin = Subrutinas.readFileBin(null, file.getAbsolutePath() );
					if ( bin != null && bin.length > 0) {
						base64 = new String( Base64.encodeBase64(bin) );
						item.setPs_flag_base64( "data:image/png;base64," + base64 );
						dao_ps.ps_chgObj(dataBase, item);
					}
				}
			}
		}
		
		
		
		

	}
	
}
