/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;


/**
 *
 * @author oumayma
 */

public interface RewardInterface <T>{
     public void addReward(T t);
    public void updateReward(T t);
    public void deleteReward(int id);
    public List<T> affichReward();
}
