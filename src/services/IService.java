/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ksaay
 */
public interface IService<T> {
    public void ajouter(T t);
    public void modifier(T t);
    public void supprimer(int id);
    //public List<T> recuperer(int order);
    //public List<T> rec_search(String x);
}
