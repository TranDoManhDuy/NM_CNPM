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
public class TimeFrame {
    private int time_frame_id;
    private LocalDate decision_date;
    private LocalTime time_start;
    private LocalTime time_end;
    private boolean is_active;

    public TimeFrame() {
    }

    public TimeFrame(int time_frame_id, LocalDate decision_date, LocalTime time_start, LocalTime time_end, boolean is_active) {
        this.time_frame_id = time_frame_id;
        this.decision_date = decision_date;
        this.time_start = time_start;
        this.time_end = time_end;
        this.is_active = is_active;
    }

    public int getTime_frame_id() {
        return time_frame_id;
    }

    public void setTime_frame_id(int time_frame_id) {
        this.time_frame_id = time_frame_id;
    }

    public LocalDate getDecision_date() {
        return decision_date;
    }

    public void setDecision_date(LocalDate decision_date) {
        this.decision_date = decision_date;
    }

    public LocalTime getTime_start() {
        return time_start;
    }

    public void setTime_start(LocalTime time_start) {
        this.time_start = time_start;
    }

    public LocalTime getTime_end() {
        return time_end;
    }

    public void setTime_end(LocalTime time_end) {
        this.time_end = time_end;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
}
