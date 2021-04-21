/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.integration;

public class DatabaseNotReachableException extends RuntimeException {
    
    public DatabaseNotReachableException(String message, Exception cause) {
        super(message, cause);
    }
}
