/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author manhh
 */
public class TimeFrameToRender {
    private LocalDate decision_date;
    private LocalTime TS1; 
    private LocalTime TS2;
    private LocalTime TS3;
    private LocalTime TE1;
    private LocalTime TE2;
    private LocalTime TE3;
    private boolean isActive;
    private int T1_id;
    private int T2_id;
    private int T3_id;
    
    public TimeFrameToRender() {
    }

    public TimeFrameToRender(LocalDate decision_date, LocalTime TS1, LocalTime TS2, LocalTime TS3, LocalTime TE1, LocalTime TE2, LocalTime TE3, boolean isActive) {
        this.decision_date = decision_date;
        this.TS1 = TS1;
        this.TS2 = TS2;
        this.TS3 = TS3;
        this.TE1 = TE1;
        this.TE2 = TE2;
        this.TE3 = TE3;
        this.isActive = isActive;
    }

    public int getT1_id() {
        return T1_id;
    }

    public void setT1_id(int T1_id) {
        this.T1_id = T1_id;
    }

    public int getT2_id() {
        return T2_id;
    }

    public void setT2_id(int T2_id) {
        this.T2_id = T2_id;
    }

    public int getT3_id() {
        return T3_id;
    }

    public void setT3_id(int T3_id) {
        this.T3_id = T3_id;
    }

    public LocalDate getDecision_date() {
        return decision_date;
    }

    public void setDecision_date(LocalDate decision_date) {
        this.decision_date = decision_date;
    }

    public LocalTime getTS1() {
        return TS1;
    }

    public void setTS1(LocalTime TS1) {
        this.TS1 = TS1;
    }

    public LocalTime getTS2() {
        return TS2;
    }

    public void setTS2(LocalTime TS2) {
        this.TS2 = TS2;
    }

    public LocalTime getTS3() {
        return TS3;
    }

    public void setTS3(LocalTime TS3) {
        this.TS3 = TS3;
    }

    public LocalTime getTE1() {
        return TE1;
    }

    public void setTE1(LocalTime TE1) {
        this.TE1 = TE1;
    }

    public LocalTime getTE2() {
        return TE2;
    }

    public void setTE2(LocalTime TE2) {
        this.TE2 = TE2;
    }

    public LocalTime getTE3() {
        return TE3;
    }

    public void setTE3(LocalTime TE3) {
        this.TE3 = TE3;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    
}
