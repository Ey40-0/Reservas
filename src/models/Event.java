/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author PROG
 */
public class Event {
    
    private int id;
    private Class classObject; // Por si acaso

    public Event() {
    }

    public Event(int id, Class classObject) {
        this.id = id;
        this.classObject = classObject;
    }
    
}
