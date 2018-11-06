/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudmvcrun;
import modelo.*;
import vista.*;
import controlador.*;

/**
 *
 * @author Ricardo
 */
public class CrudMvcvendedores {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFCrudvendedores vistaE = new JFCrudvendedores();
        vendedoresDAO modeloE = new vendedoresDAO();
        ControladorCrudvendedores controlaE = new ControladorCrudvendedores(vistaE, modeloE);
        
        vistaE.setVisible(true);
        vistaE.setLocationRelativeTo(null);
    }
    
}
