/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class LostVisitorParkingCards {
    int lost_visitor_parking_card_id;
    int parking_session_id;

    public LostVisitorParkingCards() {
    }

    public LostVisitorParkingCards( int parking_session_id) {
        this.parking_session_id = parking_session_id;
    }

    public LostVisitorParkingCards(int lost_visitor_parking_card_id, int parking_session_id) {
        this.lost_visitor_parking_card_id = lost_visitor_parking_card_id;
        this.parking_session_id = parking_session_id;
    }

    
    public int getLost_visitor_parking_card_id() {
        return lost_visitor_parking_card_id;
    }

    public int getParking_session_id() {
        return parking_session_id;
    }

    public void setLost_visitor_parking_card_id(int lost_visitor_parking_card_id) {
        this.lost_visitor_parking_card_id = lost_visitor_parking_card_id;
    }

    public void setParking_session_id(int parking_session_id) {
        this.parking_session_id = parking_session_id;
    }
    
}
