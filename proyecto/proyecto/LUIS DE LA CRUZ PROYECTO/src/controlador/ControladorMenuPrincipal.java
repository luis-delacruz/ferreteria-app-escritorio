package controlador;


import crudmvcrun.*;
import vista.*;
import crudmvcrun.*;
import controlador.*;
import modelo.*;
//se importan todos los formularios del paq.Vista
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class ControladorMenuPrincipal implements ActionListener{
    
    //creacion del objeto del menu principal
    MenuPrincipal vistaMenu = new MenuPrincipal();
    //se habilita la accion de los botones del menu principal
    public ControladorMenuPrincipal (MenuPrincipal FormMenu){
        this.vistaMenu = FormMenu;
        this.vistaMenu.btnPedidos.addActionListener(this);
        this.vistaMenu.btnFacturas.addActionListener(this);
        this.vistaMenu.btnAgregarC.addActionListener(this);
        this.vistaMenu.btnModificarC.addActionListener(this);
        this.vistaMenu.btnConsultarC.addActionListener(this);
        this.vistaMenu.btnAgregarE.addActionListener(this);
        this.vistaMenu.btnModificarE.addActionListener(this);
        this.vistaMenu.btnConsultarE.addActionListener(this);
        this.vistaMenu.btnAgregarF.addActionListener(this);
        this.vistaMenu.btnStock.addActionListener(this);      
        this.vistaMenu.btnAcercaDe.addActionListener(this);
        this.vistaMenu.btnWeb.addActionListener(this);
             
    }
    //se instancian las acciones del evento action de los botones del menu
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==this.vistaMenu.btnAgregarF){
        JFCrudvendedores vistaE = new JFCrudvendedores();
        vendedoresDAO modeloE = new vendedoresDAO();
        ControladorCrudvendedores controlaE = new ControladorCrudvendedores(vistaE, modeloE);
        
        vistaE.setVisible(true);
        vistaE.setLocationRelativeTo(null);
        vistaMenu.show(false);
        }
        if (ae.getSource()== vistaMenu.btnAgregarC){            
            JFCrud vistaC = new JFCrud();
        PersonaDAO modeloC = new PersonaDAO();
        ControladorCrud controlaC = new ControladorCrud(vistaC, modeloC);
        
        vistaC.setVisible(true);
        vistaC.setLocationRelativeTo(null);
        vistaMenu.show(false);
        
        }
      
        if(ae.getSource()==this.vistaMenu.btnAgregarE){
            JFCrudproductos vistaD = new JFCrudproductos();
        productosDAO modeloD = new productosDAO();
        Controladorproductos controlaD = new Controladorproductos(vistaD, modeloD);
        
        vistaD.setVisible(true);
        vistaD.setLocationRelativeTo(null);
        vistaMenu.show(false);
        }
       
        if(ae.getSource()==this.vistaMenu.btnAgregarF){
        JFCrudvendedores vistaE = new JFCrudvendedores();
        vendedoresDAO modeloE = new vendedoresDAO();
        ControladorCrudvendedores controlaE = new ControladorCrudvendedores(vistaE, modeloE);
        
        vistaE.setVisible(true);
        vistaE.setLocationRelativeTo(null);
        vistaMenu.show(false);
        }
        if(ae.getSource()==this.vistaMenu.btnAcercaDe){
            
        }
        if(ae.getSource()==this.vistaMenu.btnWeb){
            JOptionPane.showMessageDialog(null,"estamos trabajando para bindarte un mejor servicio");
        }
        
    }
    
}
