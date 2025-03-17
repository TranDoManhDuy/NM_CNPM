/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class VisitorParkingCards {
    int visitor_parking_card_id;
    boolean is_active;

    public VisitorParkingCards() {
    }

    public VisitorParkingCards(boolean is_active) {
        this.is_active = is_active;
    }

    public VisitorParkingCards(int visitor_parking_card_id, boolean is_active) {
        this.visitor_parking_card_id = visitor_parking_card_id;
        this.is_active = is_active;
    }

    public int getVisitor_parking_card_id() {
        return visitor_parking_card_id;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public void setVisitor_parking_card_id(int visitor_parking_card_id) {
        this.visitor_parking_card_id = visitor_parking_card_id;
    }
    
}
