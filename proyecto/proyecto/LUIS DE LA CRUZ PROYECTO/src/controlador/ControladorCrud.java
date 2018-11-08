
package controlador;
import crudmvcrun.*;
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


public class ControladorCrud implements ActionListener, KeyListener{
    
    JFCrud vistaCRUD = new JFCrud();
    PersonaDAO modeloCRUD = new PersonaDAO();
    
    public ControladorCrud(JFCrud vistaCRUD, PersonaDAO modeloCRUD){
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
        this.vistaCRUD.btnregresar.addActionListener(this);
        
    }

   
    public void InicializarCrud(){
        
    }
    
    public void LLenarTabla(JTable tablaD){
        DefaultTableModel  modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);
        
        modeloT.addColumn("NO.CLIENTE");
        modeloT.addColumn("APELLIDOS");
        modeloT.addColumn("NOMBRES");
        modeloT.addColumn("FECHA ");
        modeloT.addColumn("DIRECCION");
        
        Object[] columna = new Object[5];

        int numRegistros = modeloCRUD.listPersona().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = modeloCRUD.listPersona().get(i).getDni();
            columna[1] = modeloCRUD.listPersona().get(i).getApellidos();
            columna[2] = modeloCRUD.listPersona().get(i).getNombres();
            columna[3] = modeloCRUD.listPersona().get(i).getFechaN();
            columna[4] = modeloCRUD.listPersona().get(i).getLugarN();
            modeloT.addRow(columna);
        }
    }
    
    public void LimpiarCampos(){
        vistaCRUD.txtDni.setText("");
        vistaCRUD.txtDni.setEditable(true);
        vistaCRUD.txtApellidos.setText("");
        vistaCRUD.txtNombres.setText("");
        vistaCRUD.jdFechaN.setDate(null);
        vistaCRUD.txtLugar.setText("");
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == vistaCRUD.btnRegistrar){
            String dni = vistaCRUD.txtDni.getText();
            String apellidos = vistaCRUD.txtApellidos.getText();
            String nombres = vistaCRUD.txtNombres.getText();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = formatoFecha.format(vistaCRUD.jdFechaN.getDate());
            String lugar = vistaCRUD.txtLugar.getText();
            
            String rptaRegistro = modeloCRUD.insertPersona(dni, apellidos, nombres, fecha, lugar);
            
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
            String dni = vistaCRUD.txtDni.getText();
            String apellidos = vistaCRUD.txtApellidos.getText();
            String nombres = vistaCRUD.txtNombres.getText();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = formatoFecha.format(vistaCRUD.jdFechaN.getDate());
            String lugar = vistaCRUD.txtLugar.getText();
            
            int rptEdit = modeloCRUD.editPersona(dni, apellidos, nombres, fecha, lugar);
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
            ArrayList<String> listaDni = new ArrayList<>();
            String dni;
            if(filInicio>=0){
                for(int i = 0; i<numfilas; i++){
                    dni = String.valueOf(vistaCRUD.jtDatos.getValueAt(i+filInicio, 0));
                    listaDni.add(i, dni);
                }

                for(int j = 0; j<numfilas; j++){
                    int rpta = JOptionPane.showConfirmDialog(null, "Desea eliminar registro con dni: "+listaDni.get(j)+"? ");
                    if(rpta==0){
                        modeloCRUD.deletePersona(listaDni.get(j));
                    }
                }
                LLenarTabla(vistaCRUD.jtDatos);
            }else{
                JOptionPane.showMessageDialog(null, "Elija al menos un registro para eliminar.");
            }
        }
         if(e.getSource()== vistaCRUD.btnregresar){
         MenuPrincipal menuP = new MenuPrincipal();      
        menuP.show(true);
        ControladorMenuPrincipal controladorMenu = new ControladorMenuPrincipal(menuP);
        
         }
       
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource() == vistaCRUD.txtDni){
            char c = e.getKeyChar();
            if(c<'0' || c>'9'){
                e.consume();
            }
        }
        
        if(e.getSource() == vistaCRUD.txtApellidos || e.getSource() == vistaCRUD.txtNombres  ){
            char c = e.getKeyChar();
            if((c<'a' || c>'z') && (c<'A' || c>'Z') && (c!=(char)KeyEvent.VK_SPACE)){
                e.consume();
            }
        }
        if( e.getSource() == vistaCRUD.txtLugar){
            char c = e.getKeyChar();
            if((c<'0' || c>'9')&&(c<'a' || c>'z') && (c<'A' || c>'Z')){
                e.consume();
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource()== vistaCRUD.txtBusqueda){
            
            String apellidos = vistaCRUD.txtBusqueda.getText();
            
            DefaultTableModel  modeloT = new DefaultTableModel();
            vistaCRUD.jtDatos.setModel(modeloT);

            modeloT.addColumn("NO.CLIENTE");
            modeloT.addColumn("APELLIDOS");
            modeloT.addColumn("NOMBRES");
            modeloT.addColumn("FECHA ");
            modeloT.addColumn("DIRECCION");

            Object[] columna = new Object[5];

            int numRegistros = modeloCRUD.buscaPersona(apellidos).size();

            for (int i = 0; i < numRegistros; i++) {
                columna[0] = modeloCRUD.buscaPersona(apellidos).get(i).getDni();
                columna[1] = modeloCRUD.buscaPersona(apellidos).get(i).getApellidos();
                columna[2] = modeloCRUD.buscaPersona(apellidos).get(i).getNombres();
                columna[3] = modeloCRUD.buscaPersona(apellidos).get(i).getFechaN();
                columna[4] = modeloCRUD.buscaPersona(apellidos).get(i).getLugarN();
                modeloT.addRow(columna);
            }
        }
        
    }
   
 
}
