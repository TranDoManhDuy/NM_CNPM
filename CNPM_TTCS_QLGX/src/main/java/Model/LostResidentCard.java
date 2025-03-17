/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class LostResidentCard {
    private int lost_resident_card_id;
    private int pk_resident_card;
    private int parking_session_id;

    public LostResidentCard() {
    }

    public LostResidentCard(int pk_resident_card, int parking_session_id) {
        this.pk_resident_card = pk_resident_card;
        this.parking_session_id = parking_session_id;
    }

    public LostResidentCard(int lost_resident_card_id, int pk_resident_card, int parking_session_id) {
        this.lost_resident_card_id = lost_resident_card_id;
        this.pk_resident_card = pk_resident_card;
        this.parking_session_id = parking_session_id;
    }

    public int getLost_resident_card_id() {
        return lost_resident_card_id;
    }

    public void setLost_resident_card_id(int lost_resident_card_id) {
        this.lost_resident_card_id = lost_resident_card_id;
    }

    public int getPk_resident_card() {
        return pk_resident_card;
    }

    public void setPk_resident_card(int pk_resident_card) {
        this.pk_resident_card = pk_resident_card;
    }

    public int getParking_session_id() {
        return parking_session_id;
    }

    public void setParking_session_id(int parking_session_id) {
        this.parking_session_id = parking_session_id;
    }

    
}
