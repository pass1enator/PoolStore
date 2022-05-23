/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.poolstore;

/**
 *
 * @author Pedro
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import pedro.ieslaencanta.com.poolstore.model.App;

/**
 *
 * @author Pedro
 */
public class Controller {

    private static Controller instance;
    private String usuario, password;
    private App aplicacion;
    private boolean islogin;

    static {
        instance = null;
    }

    private Controller() {
        this.usuario = "pedro";
        this.password = "pedro";
        this.aplicacion = new App();
        this.aplicacion.init();
        this.islogin = false;
    }

    //simular la peticion http
   /* private void load() {
        this.getAplicacion().init();
    }*/

    public boolean islogged() {
        return this.islogin;
    }

    public static Controller getInstance() {
        if (Controller.instance == null) {
            Controller.instance = new Controller();
        }
        return Controller.instance;
    }

    public boolean validate(String user, String password) {
         Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,  "*-*-*-"+user+" "+password);
        
        if (this.usuario.equals(user) && this.password.equals(password)) {
            this.islogin = true;
            return true;
        }
        return false;
    }

    public App getAplicacion() {
        return aplicacion;
    }
    public void unloggin(){
        this.islogin=false;
    }
    public void setAplicacion(App aplicacion) {
        this.aplicacion = aplicacion;
    }

}
