/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.*;
import java.util.ArrayList;
import modelo.Conexion;

/**
 *
 * @author Ricardo
 */
public class vendedoresDAO {
    Conexion conexion;
    
    public vendedoresDAO(){
        conexion = new Conexion();
    }
    
    public String insertvendedores(String DPI, String APELLIDO, String NOMBRE, String TELEFONO, String DIRECCION){
        String rptaRegistro=null;
        try {
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("{call sp_insertVendedores(?,?,?,?,?)}");
            cs.setString(1, DPI);
            cs.setString(2, APELLIDO);
            cs.setString(3, NOMBRE);
            cs.setString(4, TELEFONO);
            cs.setString(5, DIRECCION);
            
            int numFAfectas = cs.executeUpdate();
            
            if(numFAfectas>0){
                rptaRegistro=("Registro exitoso.");
            }
        } catch (Exception e) {
        }
        return rptaRegistro;
    }
    
    public ArrayList<vendedores> listvendedores(){
        ArrayList listavendedores = new ArrayList();
        vendedores vendedores;
        try {
            Connection acceDB = conexion.getConexion();
            PreparedStatement ps = acceDB.prepareStatement("select * from vendedores");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               vendedores = new vendedores();
                vendedores.setDPI(rs.getString(1));
                vendedores.setAPELLIDO(rs.getString(2));
                vendedores.setNOMBRE(rs.getString(3));
                vendedores.setTELEFONO(rs.getString(4));
                vendedores.setDIRECCION(rs.getString(5));
                listavendedores.add(vendedores);
            }
        } catch (Exception e) {
        }
        return listavendedores;
    }
    
    public int deletevendedores(String DPI){
        int filAfectadas= 0;
        try {
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("{call sp_deleteVendedores(?)}");
            cs.setString(1, DPI);
            filAfectadas = cs.executeUpdate();
        } catch (Exception e) {
        }
        
        return filAfectadas;
    }
    
    public int editvendedores(String DPI, String APELLIDO, String NOMBRE,String TELEFONO, String DIRECCION){
        int filAfectadas=0;
        try {
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("{call sp_editvendedores(?,?,?,?,?)}");
            cs.setString(1, DPI);
            cs.setString(2, APELLIDO);
            cs.setString(3, NOMBRE);
            cs.setString(4, TELEFONO);
            cs.setString(5, DIRECCION);
            filAfectadas = cs.executeUpdate();
        } catch (Exception e) {
        }
        return filAfectadas;  
    }
    
    public ArrayList<vendedores> buscavendedores(String APELLIDO){
        ArrayList listaPersona = new ArrayList();
        vendedores persona;
        try {
            Connection acceDB = conexion.getConexion();
            CallableStatement cs = acceDB.prepareCall("{call sp_buscaPxApellidovendedor(?)}");
            cs.setString(1, APELLIDO);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                persona = new vendedores();
                persona.setDPI(rs.getString(1));
                persona.setAPELLIDO(rs.getString(2));
                persona.setNOMBRE(rs.getString(3));
                persona.setTELEFONO(rs.getString(4));
                persona.setDIRECCION(rs.getString(5));
                listaPersona.add(persona);
            }
        } catch (Exception e) {
        }
        return listaPersona;
    }
}
