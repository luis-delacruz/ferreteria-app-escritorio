
package controlador;
import modelo.*;
import vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ControladorCrudvendedores implements ActionListener, KeyListener{
    
    JFCrudvendedores vistaCRUD = new JFCrudvendedores();
    vendedoresDAO modeloCRUD = new vendedoresDAO();
    
    public ControladorCrudvendedores(JFCrudvendedores vistaCRUD, vendedoresDAO modeloCRUD){
        this.modeloCRUD = modeloCRUD;
        this.vistaCRUD = vistaCRUD;
        this.vistaCRUD.btnRegistrar.addActionListener(this);
        this.vistaCRUD.btnListar.addActionListener(this);
        this.vistaCRUD.btnEditar.addActionListener(this);
        this.vistaCRUD.btnEliminar.addActionListener(this);
        this.vistaCRUD.btnGEdit.addActionListener(this);
        this.vistaCRUD.txtBusqueda.addKeyListener(this);
        this.vistaCRUD.txtDni.addKeyListener(this);
        this.vistaCRUD.txtApellidos.addKeyListener(this);
        this.vistaCRUD.txtNombres.addKeyListener(this);
        this.vistaCRUD.txtLugar.addKeyListener(this);
    }
    
    public void InicializarCrud(){
        
    }
    
    public void LLenarTabla(JTable tablaD){
        DefaultTableModel  modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);
        
        modeloT.addColumn("DPI");
        modeloT.addColumn("APELLIDO");
        modeloT.addColumn("NOMBRE");
        modeloT.addColumn("TELEFONO");
        modeloT.addColumn("DIRECCION");
        
        Object[] columna = new Object[5];

        int numRegistros = modeloCRUD.listvendedores().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = modeloCRUD.listvendedores().get(i).getDPI();
            columna[1] = modeloCRUD.listvendedores().get(i).getAPELLIDO();
            columna[2] = modeloCRUD.listvendedores().get(i).getNOMBRE();
            columna[3] = modeloCRUD.listvendedores().get(i).getTELEFONO();
            columna[4] = modeloCRUD.listvendedores().get(i).getDIRECCION();
            modeloT.addRow(columna);
        }
    }
    
    public void LimpiarCampos(){
        vistaCRUD.txtDni.setText("");
        vistaCRUD.txtDni.setEditable(true);
        vistaCRUD.txtApellidos.setText("");
        vistaCRUD.txtNombres.setText("");
        vistaCRUD.jTTELEFONO.setText("");
        vistaCRUD.txtLugar.setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == vistaCRUD.btnRegistrar){
            String DPI = vistaCRUD.txtDni.getText();
            String APELLIDO = vistaCRUD.txtApellidos.getText();
            String NOMBRE = vistaCRUD.txtNombres.getText();
            String TELEFONO = vistaCRUD.jTTELEFONO.getText();
            String DIRECCION = vistaCRUD.txtLugar.getText();
            
            String rptaRegistro = modeloCRUD.insertvendedores(DPI,APELLIDO , NOMBRE, TELEFONO, DIRECCION);
            
            if(rptaRegistro!=null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
                LimpiarCampos();
            }else{
                JOptionPane.showMessageDialog(null, "Registro Erroneo.");
            }
        }
        
        if(e.getSource() == vistaCRUD.btnListar){
            LLenarTabla(vistaCRUD.jtDatos);
            JOptionPane.showMessageDialog(null, "Lista de registros.");
        }
                
        if(e.getSource() == vistaCRUD.btnEditar){
            int filaEditar = vistaCRUD.jtDatos.getSelectedRow();
            int numfilas = vistaCRUD.jtDatos.getSelectedRowCount();
            
            if(filaEditar>=0 && numfilas==1){
                vistaCRUD.txtDni.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar,0)));

                vistaCRUD.txtDni.setEditable(false);
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
            String DPI = vistaCRUD.txtDni.getText();
            String APELLIDO = vistaCRUD.txtApellidos.getText();
            String NOMBRE = vistaCRUD.txtNombres.getText();
            String TELEFONO= vistaCRUD.jTTELEFONO.getText();
            String DIRECCION = vistaCRUD.txtLugar.getText();
            
            int rptEdit = modeloCRUD.editvendedores(DPI, APELLIDO, NOMBRE, TELEFONO, DIRECCION);
            if(rptEdit>0){
                LimpiarCampos();
                JOptionPane.showMessageDialog(null, "Edicion exitosa.");
                LLenarTabla(vistaCRUD.jtDatos);
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo realizar edicion.");
            }
            vistaCRUD.txtDni.setEditable(true);
            vistaCRUD.btnGEdit.setEnabled(false);
            vistaCRUD.btnEditar.setEnabled(true);
            vistaCRUD.btnEliminar.setEnabled(true);
            vistaCRUD.btnRegistrar.setEnabled(true);
            vistaCRUD.btnListar.setEnabled(true);
        }
        
        
        if(e.getSource() == vistaCRUD.btnEliminar){
            int filInicio = vistaCRUD.jtDatos.getSelectedRow();
            int numfilas = vistaCRUD.jtDatos.getSelectedRowCount();
            ArrayList<String> listaDPI = new ArrayList<>();
            String DPI;
            if(filInicio>=0){
                for(int i = 0; i<numfilas; i++){
                    DPI = String.valueOf(vistaCRUD.jtDatos.getValueAt(i+filInicio, 0));
                    listaDPI.add(i, DPI);
                }

                for(int j = 0; j<numfilas; j++){
                    int rpta = JOptionPane.showConfirmDialog(null, "Desea eliminar registro con dni: "+listaDPI.get(j)+"? ");
                    if(rpta==0){
                        modeloCRUD.deletevendedores(listaDPI.get(j));
                    }
                }
                LLenarTabla(vistaCRUD.jtDatos);
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
        
        
        if(e.getSource() == vistaCRUD.txtApellidos || e.getSource() == vistaCRUD.txtNombres  ){
            char c = e.getKeyChar();
            if((c<'a' || c>'z') && (c<'A' || c>'Z') && (c!=(char)KeyEvent.VK_SPACE)){
                e.consume();
            }
        }
        if( e.getSource() == vistaCRUD.txtLugar){
            char c = e.getKeyChar();
            if((c<'0' || c>'9')&&(c<'a' || c>'z') && (c<'A' || c>'Z')&& (c!=(char)KeyEvent.VK_SPACE)){
                e.consume();
            }
        }
        if((e.getSource() == vistaCRUD.txtDni)&&(e.getSource() == vistaCRUD.jTTELEFONO)){
            char c = e.getKeyChar();
            if(c<'0' || c>'9'){
                e.consume();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource()== vistaCRUD.txtBusqueda){
            
            String APELLIDO = vistaCRUD.txtBusqueda.getText();
            
            DefaultTableModel  modeloT = new DefaultTableModel();
            vistaCRUD.jtDatos.setModel(modeloT);

            modeloT.addColumn("DPI");
            modeloT.addColumn("APELLIDO");
            modeloT.addColumn("NOMBRE");
            modeloT.addColumn("TELEFONO");
            modeloT.addColumn("DIRECCION");

            Object[] columna = new Object[5];

            int numRegistros = modeloCRUD.buscavendedores(APELLIDO).size();

            for (int i = 0; i < numRegistros; i++) {
                columna[0] = modeloCRUD.buscavendedores(APELLIDO).get(i).getDPI();
                columna[1] = modeloCRUD.buscavendedores(APELLIDO).get(i).getAPELLIDO();
                columna[2] = modeloCRUD.buscavendedores(APELLIDO).get(i).getNOMBRE();
                columna[3] = modeloCRUD.buscavendedores(APELLIDO).get(i).getTELEFONO();
                columna[4] = modeloCRUD.buscavendedores(APELLIDO).get(i).getDIRECCION();
                modeloT.addRow(columna);
            }
        }
        
    }

    
}
