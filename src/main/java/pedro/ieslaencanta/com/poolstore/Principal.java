/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.poolstore;

import pedro.ieslaencanta.com.poolstore.model.App;

/**
 *
 * @author Pedro
 */
public class Principal {

    private static Principal instance;
    private String usuario, password;
    private App aplicacion;
    private boolean islogin;

    static {
        instance = null;
    }

    private Principal() {
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

    public static Principal getInstance() {
        if (Principal.instance == null) {
            Principal.instance = new Principal();
        }
        return Principal.instance;
    }

    public boolean validate(String user, String password) {
        if (this.usuario.equals(user) && this.password.equals(password)) {
            this.islogin = true;
            return true;
        }
        return false;
    }

    public App getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(App aplicacion) {
        this.aplicacion = aplicacion;
    }

}
