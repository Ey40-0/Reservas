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
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author PROG
 */
public class Client {
    
    private int id;
    private String run;
    private String name;
    private String lastName;
    private int product;
    private LocalDate started;
    private LocalDate ended;

    public Client() {
    }

    public Client(int id, String run, String name, String lastName, int product, LocalDate started, LocalDate ended) {
        this.id = id;
        this.run = run;
        this.name = name;
        this.lastName = lastName;
        this.product = product;
        this.started = started;
        this.ended = ended;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public LocalDate getStarted() {
        return started;
    }

    public void setStarted(LocalDate started) {
        this.started = started;
    }

    public LocalDate getEnded() {
        return ended;
    }

    public void setEnded(LocalDate ended) {
        this.ended = ended;
    } 
    
    public static boolean addClient(Client cli) {
        String insertQuery = "INSERT INTO client (run, name, last_name, start, end, id_pro) VALUES (?, ?, ?, ?, ?, ?)";
        String checkQuery = "SELECT COUNT(id_cli) FROM client WHERE run = ?";

        try (Connection con = new connect().getConectar()) {
            try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
                checkStmt.setString(1, cli.getRun());
                
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        MainCllr.showAlert("Error al insertar", "El cliente ya está registrado, no se puede añadir de nuevo.");
                        return false;
                    }
                }
            }
            
            // Transformar las fechas a date de sql
            java.sql.Date dateStarted = java.sql.Date.valueOf(cli.getStarted());
            java.sql.Date dateEnded = java.sql.Date.valueOf(cli.getEnded());
            
            try (PreparedStatement ps = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, cli.getRun());
                ps.setString(2, cli.getName());
                ps.setString(3, cli.getLastName());
                ps.setDate(4, dateStarted);
                ps.setDate(5, dateEnded);
                ps.setInt(6, cli.getProduct());
                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            cli.setId(rs.getInt(1));
                            System.out.println("Cliente añadido con el id: " + cli.getId());
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
    
    public static ObservableList<Client> getItems() {
        ObservableList<Client> list = FXCollections.observableArrayList();
        String sql = "SELECT id_cli, run, name, last_name, start, end, id_pro FROM client";

        try (Connection con = new connect().getConectar()) {
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    LocalDate startDate = rs.getDate("start").toLocalDate();
                    LocalDate endDate = rs.getDate("end").toLocalDate();
                    
                    list.add(
                        new Client(
                        rs.getInt("id_cli"),
                        rs.getString("run"),
                        rs.getString("name"),
                        rs.getString("last_name"),
                        rs.getInt("id_pro"),
                        startDate,
                        endDate
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
