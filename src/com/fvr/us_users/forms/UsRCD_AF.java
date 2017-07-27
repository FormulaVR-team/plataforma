package com.fvr.us_users.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.us_users.bean.UsBean;
import com.fvr.us_users.bean.UsBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class UsRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public UsBeanFiltro us_filtro;
    
    // Para SELRCD:
    public String retFormulario;
    public String retElemento;
    public String valorInicial;
    
    // Para multiregistro:
    public StBean[] grid;
    public int    filasGrid;
    public int    filaInicioGrid;
    public int    filasTotales;
    public String[] clavesMarcadas;
    // Para todas (mono y multi):
    public String opcionPantalla;
    public String opcionJSMenu;
    
    public String logon_USR;
    public String logon_HSH;
    
    // Datos calculados, no de BD:
    public ContextVars contextVars = new ContextVars();
    
    // Formato de registro:
	public String us_sincro; // sincro
	public String us_mark; // mark
	public String us_is_deleted; // is_deleted
	public String us_author; // author
	public String us_user_id; // user_id
	public String us_role_id; // role_id
	public String us_hash_code; // hash_code
	public long   us_country_id; // country_id
	public String us_PS_name; // PS_name
	public String us_PS_flag_base64; // PS_flag_base64
	public String us_nick; // nick
	public String us_password; // password
	public String us_first_name; // first_name
	public String us_last_name; // last_name
	public String us_phone; // phone
	public String us_gender; // gender
	public String us_birth_day; // birth_day
	public String us_avatar; // avatar
	public String us_location_id; // location_id
	public String us_json; // json
    

    public UsRCD_AF() {
	super();
        if ( us_filtro == null ) { us_filtro = new UsBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new UsBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new UsBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        UsBean Destino = (UsBean)beanDestino;

	Destino.setUs_sincro( getUs_sincro() ); // sincro
	Destino.setUs_mark( getUs_mark() ); // mark
	Destino.setUs_is_deleted( getUs_is_deleted() ); // is_deleted
	Destino.setUs_author( getUs_author() ); // author
	Destino.setUs_user_id( getUs_user_id() ); // user_id
	Destino.setUs_role_id( getUs_role_id() ); // role_id
	Destino.setUs_hash_code( getUs_hash_code() ); // hash_code
	Destino.setUs_country_id( getUs_country_id() ); // country_id
	Destino.setUs_PS_name( getUs_PS_name() ); // PS_name
	Destino.setUs_PS_flag_base64( getUs_PS_flag_base64() ); // PS_flag_base64
	Destino.setUs_nick( getUs_nick() ); // nick
	Destino.setUs_password( getUs_password() ); // password
	Destino.setUs_first_name( getUs_first_name() ); // first_name
	Destino.setUs_last_name( getUs_last_name() ); // last_name
	Destino.setUs_phone( getUs_phone() ); // phone
	Destino.setUs_gender( getUs_gender() ); // gender
	Destino.setUs_birth_day( getUs_birth_day() ); // birth_day
	Destino.setUs_avatar( getUs_avatar() ); // avatar
	Destino.setUs_location_id( getUs_location_id() ); // location_id
	Destino.setUs_json( getUs_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        UsBean Origen = (UsBean)beanOrigen;

	setUs_sincro( Origen.getUs_sincro() ); // sincro
	setUs_mark( Origen.getUs_mark() ); // mark
	setUs_is_deleted( Origen.getUs_is_deleted() ); // is_deleted
	setUs_author( Origen.getUs_author() ); // author
	setUs_user_id( Origen.getUs_user_id() ); // user_id
	setUs_role_id( Origen.getUs_role_id() ); // role_id
	setUs_hash_code( Origen.getUs_hash_code() ); // hash_code
	setUs_country_id( Origen.getUs_country_id() ); // country_id
	setUs_PS_name( Origen.getUs_PS_name() ); // PS_name
	setUs_PS_flag_base64( Origen.getUs_PS_flag_base64() ); // PS_flag_base64
	setUs_nick( Origen.getUs_nick() ); // nick
	setUs_password( Origen.getUs_password() ); // password
	setUs_first_name( Origen.getUs_first_name() ); // first_name
	setUs_last_name( Origen.getUs_last_name() ); // last_name
	setUs_phone( Origen.getUs_phone() ); // phone
	setUs_gender( Origen.getUs_gender() ); // gender
	setUs_birth_day( Origen.getUs_birth_day() ); // birth_day
	setUs_avatar( Origen.getUs_avatar() ); // avatar
	setUs_location_id( Origen.getUs_location_id() ); // location_id
	setUs_json( Origen.getUs_json() ); // json
    }
    
    public void copyFrom(UsRCD_AF beanOrigen) {
        UsRCD_AF Origen = beanOrigen;

        setUs_filtro( Origen.getUs_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setUs_sincro( Origen.getUs_sincro() ); // sincro
	setUs_mark( Origen.getUs_mark() ); // mark
	setUs_is_deleted( Origen.getUs_is_deleted() ); // is_deleted
	setUs_author( Origen.getUs_author() ); // author
	setUs_user_id( Origen.getUs_user_id() ); // user_id
	setUs_role_id( Origen.getUs_role_id() ); // role_id
	setUs_hash_code( Origen.getUs_hash_code() ); // hash_code
	setUs_country_id( Origen.getUs_country_id() ); // country_id
	setUs_PS_name( Origen.getUs_PS_name() ); // PS_name
	setUs_PS_flag_base64( Origen.getUs_PS_flag_base64() ); // PS_flag_base64
	setUs_nick( Origen.getUs_nick() ); // nick
	setUs_password( Origen.getUs_password() ); // password
	setUs_first_name( Origen.getUs_first_name() ); // first_name
	setUs_last_name( Origen.getUs_last_name() ); // last_name
	setUs_phone( Origen.getUs_phone() ); // phone
	setUs_gender( Origen.getUs_gender() ); // gender
	setUs_birth_day( Origen.getUs_birth_day() ); // birth_day
	setUs_avatar( Origen.getUs_avatar() ); // avatar
	setUs_location_id( Origen.getUs_location_id() ); // location_id
	setUs_json( Origen.getUs_json() ); // json
    }
    
    public UsBeanFiltro getUs_filtro() { return us_filtro; }
    
    public void setUs_filtro(UsBeanFiltro us_filtro) { this.us_filtro = us_filtro; }

    public StBean[] getGrid() { return grid; }

    public void setGrid(StBean[] grid) { this.grid = grid; }

    public int getFilasGrid() { return filasGrid; }

    public void setFilasGrid(int filasGrid) { this.filasGrid = filasGrid; }

    public int getFilaInicioGrid() { return filaInicioGrid; }

    public void setFilaInicioGrid(int filaInicioGrid) { this.filaInicioGrid = filaInicioGrid; }

    public int getFilasTotales() { return filasTotales; }

	public void setFilasTotales(int filasTotales) { this.filasTotales = filasTotales; }

    public String[] getClavesMarcadas() { return clavesMarcadas; }

    public void setClavesMarcadas(String[] clavesMarcadas) { this.clavesMarcadas = clavesMarcadas; }

    public String getOpcionPantalla() { return opcionPantalla; }

    public void setOpcionPantalla(String opcionPantalla) { this.opcionPantalla = opcionPantalla; }

    public String getOpcionJSMenu() { return opcionJSMenu; }

    public void setOpcionJSMenu(String opcionJSMenu) { this.opcionJSMenu = opcionJSMenu; }

    public String getLogon_USR() { return logon_USR; }

    public void setLogon_USR(String logon_USR) { this.logon_USR = logon_USR; }

    public String getLogon_HSH() { return logon_HSH; }

	public void setLogon_HSH(String logon_HSH) { this.logon_HSH = logon_HSH; }

    public String getRetFormulario() { return retFormulario; }

    public void setRetFormulario(String retFormulario) { this.retFormulario = retFormulario; }

    public String getRetElemento() { return retElemento; }

    public void setRetElemento(String retElemento) { this.retElemento = retElemento; }

    public String getValorInicial() { return valorInicial; }

    public void setValorInicial(String valorInicial) { this.valorInicial = valorInicial; }

	public ContextVars getContextVars() { return contextVars; }

	public void setContextVars(ContextVars contextVars) { this.contextVars = contextVars; }



	/** Get sincro*/
	public String getUs_sincro() {return us_sincro;}
	/** Set sincro*/
	public void setUs_sincro(String us_sincro) {this.us_sincro = us_sincro;}

	/** Get mark*/
	public String getUs_mark() {return us_mark;}
	/** Set mark*/
	public void setUs_mark(String us_mark) {this.us_mark = us_mark;}

	/** Get is_deleted*/
	public String getUs_is_deleted() {return us_is_deleted;}
	/** Set is_deleted*/
	public void setUs_is_deleted(String us_is_deleted) {this.us_is_deleted = us_is_deleted;}

	/** Get author*/
	public String getUs_author() {return us_author;}
	/** Set author*/
	public void setUs_author(String us_author) {this.us_author = us_author;}

	/** Get user_id*/
	public String getUs_user_id() {return us_user_id;}
	/** Set user_id*/
	public void setUs_user_id(String us_user_id) {this.us_user_id = us_user_id;}

	/** Get role_id*/
	public String getUs_role_id() {return us_role_id;}
	/** Set role_id*/
	public void setUs_role_id(String us_role_id) {this.us_role_id = us_role_id;}

	/** Get hash_code*/
	public String getUs_hash_code() {return us_hash_code;}
	/** Set hash_code*/
	public void setUs_hash_code(String us_hash_code) {this.us_hash_code = us_hash_code;}

	/** Get country_id*/
	public long getUs_country_id() {return us_country_id;}
	/** Set country_id*/
	public void setUs_country_id(long us_country_id) {this.us_country_id = us_country_id;}

	/** Get PS_name*/
	public String getUs_PS_name() {return us_PS_name;}
	/** Set PS_name*/
	public void setUs_PS_name(String us_PS_name) {this.us_PS_name = us_PS_name;}

	/** Get PS_flag_base64*/
	public String getUs_PS_flag_base64() {return us_PS_flag_base64;}
	/** Set PS_flag_base64*/
	public void setUs_PS_flag_base64(String us_PS_flag_base64) {this.us_PS_flag_base64 = us_PS_flag_base64;}

	/** Get nick*/
	public String getUs_nick() {return us_nick;}
	/** Set nick*/
	public void setUs_nick(String us_nick) {this.us_nick = us_nick;}

	/** Get password*/
	public String getUs_password() {return us_password;}
	/** Set password*/
	public void setUs_password(String us_password) {this.us_password = us_password;}

	/** Get first_name*/
	public String getUs_first_name() {return us_first_name;}
	/** Set first_name*/
	public void setUs_first_name(String us_first_name) {this.us_first_name = us_first_name;}

	/** Get last_name*/
	public String getUs_last_name() {return us_last_name;}
	/** Set last_name*/
	public void setUs_last_name(String us_last_name) {this.us_last_name = us_last_name;}

	/** Get phone*/
	public String getUs_phone() {return us_phone;}
	/** Set phone*/
	public void setUs_phone(String us_phone) {this.us_phone = us_phone;}

	/** Get gender*/
	public String getUs_gender() {return us_gender;}
	/** Set gender*/
	public void setUs_gender(String us_gender) {this.us_gender = us_gender;}

	/** Get birth_day*/
	public String getUs_birth_day() {return us_birth_day;}
	/** Set birth_day*/
	public void setUs_birth_day(String us_birth_day) {this.us_birth_day = us_birth_day;}

	/** Get avatar*/
	public String getUs_avatar() {return us_avatar;}
	/** Set avatar*/
	public void setUs_avatar(String us_avatar) {this.us_avatar = us_avatar;}

	/** Get location_id*/
	public String getUs_location_id() {return us_location_id;}
	/** Set location_id*/
	public void setUs_location_id(String us_location_id) {this.us_location_id = us_location_id;}

	/** Get json*/
	public String getUs_json() {return us_json;}
	/** Set json*/
	public void setUs_json(String us_json) {this.us_json = us_json;}

}
