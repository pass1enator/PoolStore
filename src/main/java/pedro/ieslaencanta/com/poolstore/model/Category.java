/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.poolstore.model;


import java.util.HashMap;
import java.util.Optional;

/**
 *
 * @author Pedro
 */
public class Category {
    private int id;
    private String name;
    private HashMap<Integer,Product> products;
    public Category(){
        this.products=new HashMap<>();
        this.name="";
        this.id=-1;
    }
    public void addProduct(Product p){
        p.setId(this.getNextId());
        this.getProducts().put(p.getId(), p);
    }
    public void removeProduct(Product p){
        Product t=this.getProducts().get((Integer)p.getId());
        if(t!=null)
            this.getProducts().remove(t);
    }
    public Product getProduct(Integer id){
        return this.getProducts().get(id);
    }
   private Integer getNextId(){
       Optional<Integer> o= this.getProducts().keySet().stream().max(
               (a,b)->{ 
                   
                   return 1;}
       );
       if(o.isPresent())
           return o.get()+1;
       else
           return 1;
               
   }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
  
    public HashMap<Integer,Product> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Integer,Product> products) {
        this.products = products;
    }
    @Override
    public String toString(){
        return "("+this.id+")"+this.name+": Products count "+this.products.size();
    }
}
