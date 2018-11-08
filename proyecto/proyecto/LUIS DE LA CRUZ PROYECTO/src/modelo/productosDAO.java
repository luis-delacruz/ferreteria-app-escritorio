/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class productosDAO {
    Conexion conexion;
    
    public productosDAO(){
        conexion = new Conexion();
    }
    
    public String insertproductos(String CODIGO, String NOMBREPRODUCTO, String DESCRIPCION, String CANTIDAD, String PRECIO){
        String rptaRegistro=null;
        try {
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("{call sp_insertproductos(?,?,?,?,?)}");
            cs.setString(1, CODIGO);
            cs.setString(2, NOMBREPRODUCTO);
            cs.setString(3, DESCRIPCION);
            cs.setString(4, CANTIDAD);
            cs.setString(5, PRECIO);
            
            int numFAfectas = cs.executeUpdate();
            
            if(numFAfectas>0){
                rptaRegistro=("Registro exitoso.");
            }
        } catch (Exception e) {
        }
        return rptaRegistro;
    }
    
    public ArrayList<productos> listproductos(){
        ArrayList listaproductos = new ArrayList();
        productos Productos;
        try {
            Connection acceDB = conexion.getConexion();
            PreparedStatement ps = acceDB.prepareStatement("select * from Productos");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Productos = new productos();
                Productos.setCODIGO(rs.getString(1));
                Productos.setNOMBREPRODUCTO(rs.getString(2));
                Productos.setDESCRIPCION(rs.getString(3));
                Productos.setCANTIDAD(rs.getString(4));
                Productos.setPRECIO(rs.getString(5));
                listaproductos.add(Productos);
            }
        } catch (Exception e) {
        }
        return listaproductos;
    }
    
    public int deleteproductos(String CODIGO){
        int filAfectadas= 0;
        try {
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("{call sp_deletePRODUCTO(?)}");
            cs.setString(1, CODIGO);
            filAfectadas = cs.executeUpdate();
        } catch (Exception e) {
        }
        
        return filAfectadas;
    }
    
    public int editproductos(String CODIGO, String NOMBREPRODUCTO, String DESCRIPCION, String CANTIDAD, String PRECIO){
        int filAfectadas=0;
        try {
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("{call sp_editPRODUCTO(?,?,?,?,?)}");
            cs.setString(1, CODIGO);
            cs.setString(2, NOMBREPRODUCTO);
            cs.setString(3, DESCRIPCION);
            cs.setString(4, CANTIDAD);
            cs.setString(5, PRECIO);
            filAfectadas = cs.executeUpdate();
        } catch (Exception e) {
        }
        return filAfectadas;  
    }
    
    public ArrayList<productos> buscarproductos(String NOMBREPRODUCTO){
        ArrayList listaproductos = new ArrayList();
        productos Productos;
        try {
            Connection acceDB = conexion.getConexion();
            CallableStatement cs = acceDB.prepareCall("{call sp_buscaPxNOMBREPRODUCTO(?)}");
            cs.setString(1, NOMBREPRODUCTO);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Productos = new productos();
                Productos.setCODIGO(rs.getString(1));
                Productos.setNOMBREPRODUCTO(rs.getString(2));
                Productos.setDESCRIPCION(rs.getString(3));
                Productos.setCANTIDAD(rs.getString(4));
                Productos.setPRECIO(rs.getString(5));
                listaproductos.add(Productos);
            }
        } catch (Exception e) {
        }
        return listaproductos;
    }
}
