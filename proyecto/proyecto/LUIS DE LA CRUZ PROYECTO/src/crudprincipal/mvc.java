
package crudprincipal;


import modelo.*;
import vista.*;
import controlador.*;


public class mvc {
     
     
    public static void main(String [] args){
        MenuPrincipal menuP = new MenuPrincipal();      
        menuP.show(true);
        ControladorMenuPrincipal controladorMenu = new ControladorMenuPrincipal(menuP);
    }
    //se crea otro metodo para que se habilite el btn cuando se regrese de una ventana al menu Principal
    public static void regresar(){
        
        MenuPrincipal menuP = new MenuPrincipal();      
        menuP.show(true);
        ControladorMenuPrincipal controladorMenu = new ControladorMenuPrincipal(menuP);
    }
    
}
