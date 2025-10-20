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
import java.sql.Timestamp;

/**
 *
 * @author PROG
 */
public class Register {
    
    private int id;
    private Event event; // Por si acaso
    private Client client;

    public Register() {
    }

    public Register(int id, Event event, Client client) {
        this.id = id;
        this.event = event;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    public static boolean addRegister(Register reg) {
        String insertQuery = "INSERT INTO register (id_eve, id_cli) VALUES (?, ?)";
        String checkQuery = "SELECT COUNT(id_reg) FROM register WHERE id_cli = ? AND id_eve = ?";

        try (Connection con = new connect().getConectar()) {
            try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, reg.getClient().getId());
                checkStmt.setInt(2, reg.getEvent().getId());


                
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        MainCllr.showAlert("Error al insertar", "El cliente ya esta en el evento, no se puede añadir de nuevo.");
                        return false;
                    }
                }
            }
            
            try (PreparedStatement ps = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, reg.getEvent().getId());
                ps.setInt(2, reg.getClient().getId());
                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            reg.setId(rs.getInt(1));
                            System.out.println("Registro añadido con el id: " + reg.getId());
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
    
}
