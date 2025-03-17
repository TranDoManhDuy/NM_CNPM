/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class ResidentCard {
    private int pk_resident_card;  
    private int customer_id; 
    private boolean is_active;

    public ResidentCard() {
    }

    public ResidentCard(int customer_id, boolean is_active) {
        this.customer_id = customer_id;
        this.is_active = is_active;
    }
    
    public ResidentCard(int pk_resident_card, int customer_id, boolean is_active) {
        this.pk_resident_card = pk_resident_card;
        this.customer_id = customer_id;
        this.is_active = is_active;
    }

    public int getPk_resident_card() {
        return pk_resident_card;
    }

    public void setPk_resident_card(int pk_resident_card) {
        this.pk_resident_card = pk_resident_card;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    
}
