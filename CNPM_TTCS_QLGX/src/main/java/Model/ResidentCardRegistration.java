/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class ResidentCardRegistration {
    private int resident_cards_registration_id;
    private int pk_resident_card; 
    private int registration_id;

    public ResidentCardRegistration() {
    }

    public ResidentCardRegistration(int pk_resident_card, int registration_id) {
        this.pk_resident_card = pk_resident_card;
        this.registration_id = registration_id;
    }

    public ResidentCardRegistration(int resident_cards_registration_id, int pk_resident_card, int registration_id) {
        this.resident_cards_registration_id = resident_cards_registration_id;
        this.pk_resident_card = pk_resident_card;
        this.registration_id = registration_id;
    }

    public int getResident_cards_registration_id() {
        return resident_cards_registration_id;
    }

    public void setResident_cards_registration_id(int resident_cards_registration_id) {
        this.resident_cards_registration_id = resident_cards_registration_id;
    }

    public int getPk_resident_card() {
        return pk_resident_card;
    }

    public void setPk_resident_card(int pk_resident_card) {
        this.pk_resident_card = pk_resident_card;
    }

    public int getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(int registration_id) {
        this.registration_id = registration_id;
    }

    
}
