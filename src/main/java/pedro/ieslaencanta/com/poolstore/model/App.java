/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.poolstore.model;

import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


/**
 *
 * @author Pedro
 */
public class App {

    private HashMap<Integer, Category> categorias;
    
    public App() {
        this.categorias = new HashMap<>();
    }

    public void init() {
        Category c;
        Product p;
        c = new Category();
        c.setId(1);
        c.setName("Cloro");
        this.addCategory(c);
        p= new Product();
        
        p.setDescription("A ver si lo borra");
        p.setMinstock(1);
        p.setStock(4);
        p.setPrice(4.5d);
        c.addProduct(p);
        
        
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
        if(c.getId()==-1)
            c.setId(this.getNextId());
        this.categorias.put(c.getId(), c);
    }
    public Category removeCategory(Category c){
        return this.categorias.remove(c.getId());
    }
    public Category removeCategory(Integer id){
        return this.categorias.remove(id);
    }
    public Category getCategory(Integer id) {
        return this.categorias.get(id);
    }

    public Product getProduct(Integer categoryid, Integer productid) {

        if (this.categorias.containsKey(categoryid)) {
            return this.categorias.get(categoryid).getProduct(Integer.SIZE);
        } else {
            return null;
        }
    }

    public void addProduct(Category c, Product p) throws Exception {
        if (this.categorias.containsKey(c.getId())) {
            this.categorias.get(c.getId()).addProduct(p);
        } else {
            throw new Exception("No existe esa categoria");
        }
    }

    public void addProduct(Integer categoryid, Product p) throws Exception {
        if (this.categorias.containsKey(categoryid)) {
            this.categorias.get(categoryid).addProduct(p);
        } else {
            throw new Exception("No existe esa categoria");
        }
    }
    public List<Product>getAllProducts(){
        ArrayList<Product> p= new ArrayList<>();
        this.categorias.values().stream().forEach(c->{
            p.addAll(c.getProducts().values());
        });
        return p;
    }  
    private Integer getNextId(){
       Optional<Category> o= this.categorias.values().stream().max(
               (a,b)->{ 
                   return a.getId()-b.getId();
                   }
       );
       if(o.isPresent())
           return o.get().getId()+1;
       else
           return 1;
               
   }

}
