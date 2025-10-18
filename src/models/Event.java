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
import java.time.LocalDateTime;

/**
 *
 * @author PROG
 */
public class Event {
    
    private int id;
    private int idClass;
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

    public static boolean addEvent(Event eve) {
        String insertQuery = "INSERT INTO event (id_cla, start_at, end_at) VALUES (?, ?, ?)";
        String checkQuery = "SELECT COUNT(id_eve) FROM event WHERE id_cla = ? AND start_at = ? AND end_at = ?";

        try (Connection con = new connect().getConectar()) {
            try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, eve.getIdClass());
                checkStmt.setTimestamp(2, Timestamp.valueOf(eve.getStartAt()));
                checkStmt.setTimestamp(3, Timestamp.valueOf(eve.getEndAt()));

                
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        MainCllr.showAlert("Error al insertar", "El evento ya existe, no se puede añadir de nuevo.");
                        return false;
                    }
                }
            }
            
            try (PreparedStatement ps = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, eve.getIdClass());
                ps.setTimestamp(2, Timestamp.valueOf(eve.getStartAt()));
                ps.setTimestamp(3, Timestamp.valueOf(eve.getEndAt()));
                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            eve.setId(rs.getInt(1));
                            System.out.println("Evento añadido con el id: " + eve.getId());
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
