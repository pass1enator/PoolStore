/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.poolstore.model;

import java.util.Collection;
import java.util.List;


/**
 *
 * @author Pedro
 */
public class Product {

    private int id;
    private String name;
    private float price;
    private int stock;
    private int minstock;
    private String description;
    public List<Product> findProduct(Collection<Product> collection){
        return null;
    }
        
    public Product() {
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getMinstock() {
        return minstock;
    }

    public void setMinstock(int minstock) {
        this.minstock = minstock;
    }
    public String toString(){
        return "Id:"+this.id+" name:"+this.description+" price:"+this.price+" stock:"+this.stock+" min stock:"+this.minstock;
    }
    public void sale(int units) throws Exception{
        if(this.stock-units>=0){
            this.stock-=units;
            if(this.stock<this.minstock){
            }
        }
        else
            throw  new Exception("No existe stock suficiente");
    }
}
