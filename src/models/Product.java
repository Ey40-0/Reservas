 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controllers.MainCllr;
import initialize.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author PROG
 */
public class Product {
    
    private int id;
    private String description;
    private int family;

    public Product() {
    }

    public Product(int id, String description, int family) {
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

    public int getFamily() {
        return family;
    }

    public void setFamily(int family) {
        this.family = family;
    }

    @Override
    public String toString() {
        return description;
    }
    
    
    public static boolean addProduct(Product pro) {
        String insertQuery = "INSERT INTO product (description, id_fam) VALUES (?, ?)";
        String checkQuery = "SELECT COUNT(id_pro) FROM product WHERE description = ?";

        try (Connection con = new connect().getConectar()) {
            try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
                checkStmt.setString(1, pro.getDescription());
                
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        MainCllr.showAlert("Error al insertar", "El producto ya existe, no se puede añadir de nuevo.");
                        return false;
                    }
                }
            }

            try (PreparedStatement ps = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, pro.getDescription());
                ps.setInt(2, pro.getFamily());
                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            pro.setId(rs.getInt(1));
                            System.out.println("Producto añadido con el id: " + pro.getId());
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
    
    public static ObservableList<Product> getItems() {
        ObservableList<Product> prods = FXCollections.observableArrayList();
        String sql = "SELECT * FROM product ORDER BY description";

        try (Connection con = new connect().getConectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                prods.add(new Product(rs.getInt("id_pro"), rs.getString("description"), rs.getInt("id_fam")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prods;
    }
    
}
