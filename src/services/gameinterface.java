/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author Ahmed.A.Hsouna
 */
public interface gameinterface<T> {
    
        public void addGame(T t);
    public void updateGame(T t);
    public void deleteGame(int id);
    public List<T> affichGame();
    
}
