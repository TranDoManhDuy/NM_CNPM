/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class Tasks {
    int task_id;
    String task_name;
    String task_desc;

    public Tasks() {
    }

    public Tasks( String task_name, String task_desc) {
        this.task_name = task_name;
        this.task_desc = task_desc;
    }

    public Tasks(int task_id, String task_name, String task_desc) {
        this.task_id = task_id;
        this.task_name = task_name;
        this.task_desc = task_desc;
    }

    public int getTask_id() {
        return task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public String getTask_desc() {
        return task_desc;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public void setTask_desc(String task_desc) {
        this.task_desc = task_desc;
    }
    
}
