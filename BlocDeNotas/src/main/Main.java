
package main;

import models.ModelBlocDeNotas;
import views.ViewBlocDeNotas;
import controllers.ControllerBlocDeNotas;
/**
 *
 * @author Jocelyn
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ModelBlocDeNotas modelBlocDeNotas = new ModelBlocDeNotas();
        ViewBlocDeNotas viewBlocDeNotas = new ViewBlocDeNotas();
        ControllerBlocDeNotas controllerBlocDeNotas = new ControllerBlocDeNotas(modelBlocDeNotas, viewBlocDeNotas);
    }
    
}
