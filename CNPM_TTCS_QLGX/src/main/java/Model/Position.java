/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Model;

public class Position {
    private int position_id;
    private String position_name;

    public Position() {}

    public Position(int position_id, String position_name) {
        this.position_id = position_id;
        this.position_name = position_name;
    }

    public int getPositionId() {
        return position_id;
    }

    public void setPositionId(int position_id) {
        this.position_id = position_id;
    }

    public String getPositionName() {
        return position_name;
    }

    public void setPositionName(String position_name) {
        this.position_name = position_name;
    }

    @Override
    public String toString() {
        return "Position{" +
                "position_id=" + position_id +
                ", position_name='" + position_name + '\'' +
                '}';
    }
}
