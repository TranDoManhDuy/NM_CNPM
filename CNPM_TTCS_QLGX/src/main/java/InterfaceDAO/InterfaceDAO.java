/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfaceDAO;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface InterfaceDAO<T> {
    public ArrayList<T> getList();
    public boolean insert(T object);
    public boolean update(T object);
    public T findbyID(int id);
    public boolean delete(int id);
}