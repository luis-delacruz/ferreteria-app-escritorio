
package controlador;

import modelo.*;
import vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Controladorproductos implements ActionListener, KeyListener{
    
    JFCrudproductos vistaCRUD = new JFCrudproductos();
    productosDAO modeloCRUD = new productosDAO();
    
    public Controladorproductos(JFCrudproductos vistaCRUD, productosDAO modeloCRUD){
        this.modeloCRUD = modeloCRUD;
        this.vistaCRUD = vistaCRUD;
        this.vistaCRUD.btnRegistrar.addActionListener(this);
        this.vistaCRUD.btnListar.addActionListener(this);
        this.vistaCRUD.btnEditar.addActionListener(this);
        this.vistaCRUD.btnEliminar.addActionListener(this);
        this.vistaCRUD.btnGEdit.addActionListener(this);
        this.vistaCRUD.txtBusqueda2.addKeyListener(this);
        this.vistaCRUD.CODIGO.addKeyListener(this);
        this.vistaCRUD.NOMBREPRODUCTO.addKeyListener(this);
        this.vistaCRUD.DESCRIPCION.addKeyListener(this);
        this.vistaCRUD.PRECIO.addKeyListener(this);
        this.vistaCRUD.CANTIDAD.addKeyListener(this);
    }
    
    public void InicializarCrud(){
        
    }
    
    public void LLenarTabla(JTable tablaD){
        DefaultTableModel  modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);
        
        modeloT.addColumn("CODIGO");
        modeloT.addColumn("NOMBRE DEL PRODUCTO");
        modeloT.addColumn("DESCRIPCION");
        modeloT.addColumn("CANTIDAD");
        modeloT.addColumn("PRECIO");
        
        Object[] columna = new Object[5];

        int numRegistros = modeloCRUD.listproductos().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = modeloCRUD.listproductos().get(i).getCODIGO();
            columna[1] = modeloCRUD.listproductos().get(i).getNOMBREPRODUCTO();
            columna[2] = modeloCRUD.listproductos().get(i).getDESCRIPCION();
            columna[3] = modeloCRUD.listproductos().get(i).getCANTIDAD();
            columna[4] = modeloCRUD.listproductos().get(i).getPRECIO();
            modeloT.addRow(columna);
        }
    }
    
    public void LimpiarCampos(){
        vistaCRUD.CODIGO.setText("");
        vistaCRUD.CODIGO.setEditable(true);
        vistaCRUD.NOMBREPRODUCTO.setText("");
        vistaCRUD.DESCRIPCION.setText("");
        vistaCRUD.CANTIDAD.setText("");
        vistaCRUD.PRECIO.setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == vistaCRUD.btnRegistrar){
            String CODIGO = vistaCRUD.CODIGO.getText();
            String NOMBREPRODUCTO = vistaCRUD.NOMBREPRODUCTO.getText();
            String DESCRIPCION = vistaCRUD.DESCRIPCION.getText();
            String CANTIDAD = vistaCRUD.CANTIDAD.getText();
            String PRECIO = vistaCRUD.PRECIO.getText();
            
            String rptaRegistro = modeloCRUD.insertproductos(CODIGO,NOMBREPRODUCTO,DESCRIPCION,CANTIDAD,PRECIO);
            
            if(rptaRegistro!=null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
                LimpiarCampos();
            }else{
                JOptionPane.showMessageDialog(null, "Registro Erroneo.");
            }
        }
        
        if(e.getSource() == vistaCRUD.btnListar){
            LLenarTabla(vistaCRUD.jtdatosproductos);
            JOptionPane.showMessageDialog(null, "Lista de registros.");
        }
                
        if(e.getSource() == vistaCRUD.btnEditar){
            int filaEditar = vistaCRUD.jtdatosproductos.getSelectedRow();
            int numfilas = vistaCRUD.jtdatosproductos.getSelectedRowCount();
            
            if(filaEditar>=0 && numfilas==1){
                vistaCRUD.CODIGO.setText(String.valueOf(vistaCRUD.jtdatosproductos.getValueAt(filaEditar,0)));

                vistaCRUD.CODIGO.setEditable(false);
                vistaCRUD.btnGEdit.setEnabled(true);
                vistaCRUD.btnEditar.setEnabled(false);
                vistaCRUD.btnEliminar.setEnabled(false);
                vistaCRUD.btnRegistrar.setEnabled(false);
                vistaCRUD.btnListar.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione registro a editar");
            }
            
        }
        
        if(e.getSource() == vistaCRUD.btnGEdit){
            String CODIGO = vistaCRUD.CODIGO.getText();
            String NOMBREPRODUCTO = vistaCRUD.NOMBREPRODUCTO.getText();
            String DESCRIPCION = vistaCRUD.DESCRIPCION.getText();
            String CANTIDAD = vistaCRUD.CANTIDAD.getText();
            String PRECIO = vistaCRUD.PRECIO.getText();
            
            int rptEdit = modeloCRUD.editproductos(CODIGO, NOMBREPRODUCTO, DESCRIPCION, CANTIDAD, PRECIO);
            if(rptEdit>0){
                LimpiarCampos();
                JOptionPane.showMessageDialog(null, "Edicion exitosa.");
                LLenarTabla(vistaCRUD.jtdatosproductos);
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo realizar edicion.");
            }
            vistaCRUD.CODIGO.setEditable(true);
            vistaCRUD.btnGEdit.setEnabled(false);
            vistaCRUD.btnEditar.setEnabled(true);
            vistaCRUD.btnEliminar.setEnabled(true);
            vistaCRUD.btnRegistrar.setEnabled(true);
            vistaCRUD.btnListar.setEnabled(true);
        }
        
        
        if(e.getSource() == vistaCRUD.btnEliminar){
            int filInicio = vistaCRUD.jtdatosproductos.getSelectedRow();
            int numfilas = vistaCRUD.jtdatosproductos.getSelectedRowCount();
            ArrayList<String> listaCODIGO = new ArrayList<>();
            String CODIGO;
            if(filInicio>=0){
                for(int i = 0; i<numfilas; i++){
                    CODIGO = String.valueOf(vistaCRUD.jtdatosproductos.getValueAt(i+filInicio, 0));
                    listaCODIGO.add(i, CODIGO);
                }

                for(int j = 0; j<numfilas; j++){
                    int rpta = JOptionPane.showConfirmDialog(null, "Desea eliminar registro con dni: "+listaCODIGO.get(j)+"? ");
                    if(rpta==0){
                        modeloCRUD.deleteproductos(listaCODIGO.get(j));
                    }
                }
                LLenarTabla(vistaCRUD.jtdatosproductos);
            }else{
                JOptionPane.showMessageDialog(null, "Elija al menos un registro para eliminar.");
            }
        }
        
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource() == vistaCRUD.CODIGO || e.getSource() == vistaCRUD.PRECIO || e.getSource() == vistaCRUD.CANTIDAD){
            char c = e.getKeyChar();
            if(c<'0' || c>'9'){
                e.consume();
            }
        }
        
        if(e.getSource() == vistaCRUD.NOMBREPRODUCTO || e.getSource() == vistaCRUD.DESCRIPCION  ){
            char c = e.getKeyChar();
            if((c<'a' || c>'z') && (c<'A' || c>'Z')&& (c!=(char)KeyEvent.VK_SPACE)){
                e.consume();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource()== vistaCRUD.txtBusqueda2){
            
            String NOMBREPRODUCTO = vistaCRUD.txtBusqueda2.getText();
            
            DefaultTableModel  modeloT = new DefaultTableModel();
            vistaCRUD.jtdatosproductos.setModel(modeloT);

            modeloT.addColumn("CODIGO");
            modeloT.addColumn("NOMBRE DEL PRODUCTO");
            modeloT.addColumn("DESCRIPCION");
            modeloT.addColumn("CANTIDAD.");
            modeloT.addColumn("PRECIO.");

            Object[] columna = new Object[5];

            int numRegistros = modeloCRUD.buscarproductos(NOMBREPRODUCTO).size();

            for (int i = 0; i < numRegistros; i++) {
                columna[0] = modeloCRUD.buscarproductos(NOMBREPRODUCTO).get(i).getCODIGO();
                columna[1] = modeloCRUD.buscarproductos(NOMBREPRODUCTO).get(i).getNOMBREPRODUCTO();
                columna[2] = modeloCRUD.buscarproductos(NOMBREPRODUCTO).get(i).getDESCRIPCION();
                columna[3] = modeloCRUD.buscarproductos(NOMBREPRODUCTO).get(i).getCANTIDAD();
                columna[4] = modeloCRUD.buscarproductos(NOMBREPRODUCTO).get(i).getPRECIO();
                modeloT.addRow(columna);
            }
        }
        
    }

    
}
