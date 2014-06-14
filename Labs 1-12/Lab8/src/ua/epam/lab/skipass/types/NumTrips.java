/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.lab.skipass.types;

/**
 * Number of Trips for Ski Passes (10, 20, 50, 100)
 * @author DruidKuma
 */
public enum NumTrips {
    
    TEN(10), TWENTY(20),
    FIFTY(50), HUNDRED(100);
    
    public int numTrips;
    
    NumTrips(int num) {
        numTrips = num;
    }
}
