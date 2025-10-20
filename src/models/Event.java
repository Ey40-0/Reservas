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
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author PROG
 */
public class Event {
    
    private int id;
    private int idClass;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String className;

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }    

    @Override
    public String toString() {
        DateTimeFormatter type = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return className + " " + startAt.format(type) + " - " + endAt.format(type);
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
    
    public static ObservableList<Event> getItems() {
        ObservableList<Event> eventList = FXCollections.observableArrayList();
        String sql = "SELECT id_eve, id_cla, start_at, end_at FROM event";

        try (Connection con = new connect().getConectar()) {
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    eventList.add(
                        new Event(
                        rs.getInt("id_eve"),
                        rs.getInt("id_cla"),
                        rs.getTimestamp("start_at").toLocalDateTime(),
                        rs.getTimestamp("end_at").toLocalDateTime()
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventList;
    }
    
    public static ObservableList<Event> getItemsByFamily(int clientId) {
        ObservableList<Event> eventList = FXCollections.observableArrayList();
        String sql = """
            SELECT 
                e.id_eve,
                e.id_cla,
                e.start_at,
                e.end_at,
                c.name
            FROM client cl
            JOIN product p ON cl.id_pro = p.id_pro
            JOIN class c ON c.id_fam = p.id_fam
            JOIN event e ON e.id_cla = c.id_cla
            WHERE cl.id_cli = ?
            AND e.start_at >= NOW() - INTERVAL 10 MINUTE
        """;

        try (Connection con = new connect().getConectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, clientId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    
                    Event eve = new Event();
                    eve.setId(rs.getInt("e.id_eve"));
                    eve.setIdClass(rs.getInt("e.id_cla"));
                    eve.setStartAt(rs.getTimestamp("e.start_at").toLocalDateTime());
                    eve.setEndAt(rs.getTimestamp("e.end_at").toLocalDateTime());
                    eve.setClassName(rs.getString("c.name"));
                    eventList.add(eve);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventList;
    }
    
}
