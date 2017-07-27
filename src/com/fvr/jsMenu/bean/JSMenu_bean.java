package com.fvr.jsMenu.bean;

import com.fvr._comun.StBean;

public class JSMenu_bean extends StBean {
    
    private String jsMenu_Nivel;    // Nivel
    private String jsMenu_Literal;  // Literal
    private String jsMenu_Accion;   // Acción
    
    public JSMenu_bean() {
        super();
        this.setJsMenu_Nivel( "" );
        this.setJsMenu_Literal( "" );
        this.setJsMenu_Accion( "" );
    }
    
    public JSMenu_bean(Object nulo) {
        super();
    }

    public StBean coalesce(StBean beanPri, StBean beanSec) {
        JSMenu_bean r = new JSMenu_bean(null);
        if ( beanPri!=null && beanSec!=null ) {
            JSMenu_bean pri = (JSMenu_bean)beanPri;
            JSMenu_bean sec = (JSMenu_bean)beanSec;
            r.setJsMenu_Nivel( (pri.getJsMenu_Nivel()==null)?sec.getJsMenu_Nivel():pri.getJsMenu_Nivel() );
            r.setJsMenu_Literal( (pri.getJsMenu_Literal()==null)?sec.getJsMenu_Literal():pri.getJsMenu_Literal() );
            r.setJsMenu_Accion( (pri.getJsMenu_Accion()==null)?sec.getJsMenu_Accion():pri.getJsMenu_Accion() );
        }
        return r;
    }
    
    public void copyTo(StBean beanDestino) {
        JSMenu_bean Destino = (JSMenu_bean)beanDestino;
        
        Destino.setJsMenu_Nivel( getJsMenu_Nivel() );
        Destino.setJsMenu_Literal( getJsMenu_Literal() );
        Destino.setJsMenu_Accion( getJsMenu_Accion() );
    }
    
    public void copyFrom(StBean beanOrigen) {
        JSMenu_bean Origen = (JSMenu_bean)beanOrigen;
        
        setJsMenu_Nivel( Origen.getJsMenu_Nivel() );
        setJsMenu_Literal( Origen.getJsMenu_Literal() );
        setJsMenu_Accion( Origen.getJsMenu_Accion() );
    }
    
    public String getJsMenu_Nivel() {
        return jsMenu_Nivel;
    }
    
    public void setJsMenu_Nivel(String jsMenu_Nivel) {
        this.jsMenu_Nivel = jsMenu_Nivel;
    }
    
    public String getJsMenu_Literal() {
        return jsMenu_Literal;
    }
    
    public void setJsMenu_Literal(String jsMenu_Literal) {
        this.jsMenu_Literal = jsMenu_Literal;
    }
    
    public String getJsMenu_Accion() {
        return jsMenu_Accion;
    }
    
    public void setJsMenu_Accion(String jsMenu_Accion) {
        this.jsMenu_Accion = jsMenu_Accion;
    }
    
}
