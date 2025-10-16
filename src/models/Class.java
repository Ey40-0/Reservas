/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import initialize.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author PROG
 */
public class Class {
    
    private int id;
    private String name;
    private ProdFamily family; // Todo por si acaso

    public Class() {
    }

    public Class(int id, String name, ProdFamily family) {
        this.id = id;
        this.name = name;
        this.family = family;
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

    public ProdFamily getFamily() {
        return family;
    }

    public void setFamily(ProdFamily family) {
        this.family = family;
    }
    
    public static ObservableList<Class> getItems() {
        ObservableList<Class> classes = FXCollections.observableArrayList();
        String sql = "SELECT * FROM class ORDER BY name";

        try (Connection con = new connect().getConectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            /*while (rs.next()) {
                classes.add(new Class(rs.getInt("id"), rs.getString("name", rs.getInt("id_fam"))));
            }*/

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classes;
    }
    
}
