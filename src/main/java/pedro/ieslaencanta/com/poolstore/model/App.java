/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.poolstore.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;


/**
 *
 * @author Pedro
 */
public class App {

    private HashMap<String, Category> categorias;
    
    public App() {
        this.categorias = new HashMap<>();
    }

    public void init() {
        Category c;
        c = new Category();
        c.setId(1);
        c.setName("Cloro");
        this.addCategory(c);

        c = new Category();
        c.setId(2);
        c.setName("Filtros");
        this.addCategory(c);

        c = new Category();
        c.setId(3);
        c.setName("Piedra artificial");
        this.addCategory(c);
        
        
        c= new Category();
        c.setId(4);
        c.setName("Anti algas");
       this.addCategory(c);
    }

    public Collection<Category> getCategorys() {
        return this.categorias.values();
    }

    public void addCategory(Category c) {
        this.categorias.put(c.getName(), c);
    }
    public void removeCategory(Category c){
        this.categorias.remove(c.getId());
    }
    public void removeCategory(int id){
        this.categorias.remove(Integer.valueOf(id));
    }
    public Category getCategory(String name) {
        return this.categorias.get(name);
    }

    public Product getProduct(String categoryname, Integer productid) {

        if (this.categorias.containsKey(categoryname)) {
            return this.categorias.get(categoryname).getProduct(Integer.SIZE);
        } else {
            return null;
        }
    }

    public void addProduct(Category c, Product p) throws Exception {
        if (this.categorias.containsKey(c.getName())) {
            this.categorias.get(c.getName()).addProduct(p);
        } else {
            throw new Exception("No existe esa categoria");
        }
    }

    public void addProduct(String categoryname, Product p) throws Exception {
        if (this.categorias.containsKey(categoryname)) {
            this.categorias.get(categoryname).addProduct(p);
        } else {
            throw new Exception("No existe esa categoria");
        }
    }

}
