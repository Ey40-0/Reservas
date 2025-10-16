/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author PROG
 */
public class Product {
    
    private int id;
    private String description;
    private ProdFamily family;

    public Product() {
    }

    public Product(int id, String description, ProdFamily family) {
        this.id = id;
        this.description = description;
        this.family = family;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProdFamily getFamily() {
        return family;
    }

    public void setFamily(ProdFamily family) {
        this.family = family;
    }
    
}
