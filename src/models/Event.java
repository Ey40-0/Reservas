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
import java.time.LocalDateTime;

/**
 *
 * @author PROG
 */
public class Event {
    
    private int id;
    private int idClass; // Por si acaso
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public Event() {
    }

    public Event(int id, int idClass, LocalDateTime startAt, LocalDateTime endAt) {
        this.id = id;
        this.idClass = idClass;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }

    /*public static boolean addEvent(Event eve) {
        String insertQuery = "INSERT INTO client (run, name, last_name, start, end, id_pro) VALUES (?, ?, ?, ?, ?, ?)";
        String checkQuery = "SELECT COUNT(id_cli) FROM client WHERE run = ?";

        try (Connection con = new connect().getConectar()) {
            try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
                checkStmt.setString(1, eve.getRun());
                
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        MainCllr.showAlert("Error al insertar", "El cliente ya está registrado, no se puede añadir de nuevo.");
                        return false;
                    }
                }
            }
            
            // Transformar las fechas a date de sql
            java.sql.Date dateStartAt = java.sql.Date;
            java.sql.Date dateEndAt = java.sql.Date.valueOf(cli.getEnded());
            
            try (PreparedStatement ps = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, eve.getIdClass());
                ps.setString(2, eve.getName());
                ps.setString(3, eve.getLastName());
                ps.setDate(4, dateStarted);
                ps.setDate(5, dateEnded);
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
    }*/
}
