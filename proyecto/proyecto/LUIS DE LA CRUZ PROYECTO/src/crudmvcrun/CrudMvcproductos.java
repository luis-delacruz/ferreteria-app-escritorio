package crudmvcrun;
import controlador.*;
import modelo.*;
import vista.*;


public class CrudMvcproductos {

    public static void main(String[] args) {
        JFCrudproductos vistaD = new JFCrudproductos();
        productosDAO modeloD = new productosDAO();
        Controladorproductos controlaD = new Controladorproductos(vistaD, modeloD);
        
        vistaD.setVisible(true);
        vistaD.setLocationRelativeTo(null);
    }

    
}