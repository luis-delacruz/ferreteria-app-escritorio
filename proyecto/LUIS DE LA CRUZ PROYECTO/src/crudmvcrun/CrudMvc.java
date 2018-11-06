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
public class CrudMvc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFCrud vistaC = new JFCrud();
        PersonaDAO modeloC = new PersonaDAO();
        ControladorCrud controlaC = new ControladorCrud(vistaC, modeloC);
        
        vistaC.setVisible(true);
        vistaC.setLocationRelativeTo(null);
    }
    
}
