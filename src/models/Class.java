/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controllers.MainCllr;
import initialize.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
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
    private int family;

    public Class() {
    }

    public Class(int id, String name, int family) {
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

    public int getFamily() {
        return family;
    }

    public void setFamily(int family) {
        this.family = family;
    }

    @Override
    public String toString() {
        return name;
    }
    
    public static boolean addClass(Class cla) {
        String insertQuery = "INSERT INTO class (name, id_fam) VALUES (?,?)";
        String checkQuery = "SELECT COUNT(id_cla) FROM class WHERE name = ?";

        try (Connection con = new connect().getConectar()) {
            try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
                checkStmt.setString(1, cla.getName());
                
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        MainCllr.showAlert("Error al insertar", "La clase ya existe en una familia, no se puede añadir de nuevo.");
                        return false;
                    }
                }
            }

            try (PreparedStatement ps = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, cla.getName());
                ps.setInt(2, cla.getFamily());
                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            cla.setId(rs.getInt(1));
                            System.out.println("Clase añadida con el id: " + cla.getId());
                            return true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static ObservableList<Class> getItems() {
        ObservableList<Class> classes = FXCollections.observableArrayList();
        String sql = "SELECT * FROM class ORDER BY name";

        try (Connection con = new connect().getConectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                classes.add(new Class(rs.getInt("id_cla"), rs.getString("name"), rs.getInt("id_fam")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classes;
    }
    
}
