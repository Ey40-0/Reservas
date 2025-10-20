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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PROG
 */
public class Register {
    
    private int id;
    private Event event;
    private Client client;
    private String className;
    private String run;
    private String date;
    private String time;
    private Timestamp registeredAt;

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Timestamp getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Timestamp registeredAt) {
        this.registeredAt = registeredAt;
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
    
    public static List<Register> getItems() {
        List<Register> regsList = new ArrayList<>();
        String sql = """
            SELECT 
                c.name AS class_name,
                cl.run AS client_run,
                DATE_FORMAT(e.start_at, '%d/%m/%Y') AS class_date,
                DATE_FORMAT(e.start_at, '%H:%i') AS start_time,
                DATE_FORMAT(e.end_at, '%H:%i') AS end_time,
                r.registered_at
            FROM register r
            INNER JOIN event e ON r.id_eve = e.id_eve
            INNER JOIN client cl ON r.id_cli = cl.id_cli
            INNER JOIN class c ON e.id_cla = c.id_cla"""; 

        try (Connection con = new connect().getConectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Register reg = new Register();
                reg.setClassName(rs.getString("class_name"));
                reg.setRun(rs.getString("client_run"));
                reg.setDate(rs.getString("class_date"));
                reg.setTime(rs.getString("start_time") + " - " + rs.getString("end_time"));
                reg.setRegisteredAt(rs.getTimestamp("registered_at"));

                regsList.add(reg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return regsList;
    }
}
