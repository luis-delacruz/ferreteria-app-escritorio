/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Ricardo
 */
public class productos{
    String CODIGO;
    String NOMBREPRODUCTO;
    String DESCRIPCION;
    String CANTIDAD;
    String PRECIO;
    
    public productos(){
        CODIGO="";
        NOMBREPRODUCTO="";
        DESCRIPCION="";
        CANTIDAD="";
        PRECIO="";
    }

    public String getCODIGO() {
        return CODIGO;
    }

    public void setCODIGO(String CODIGO) {
        this.CODIGO = CODIGO;
    }

    public String getNOMBREPRODUCTO() {
        return NOMBREPRODUCTO;
    }

    public void setNOMBREPRODUCTO(String NOMBREPRODUCTO) {
        this.NOMBREPRODUCTO = NOMBREPRODUCTO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getCANTIDAD() {
        return CANTIDAD;
    }

    public void setCANTIDAD(String CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }

    public String getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(String PRECIO) {
        this.PRECIO = PRECIO;
    }
    
    
}
