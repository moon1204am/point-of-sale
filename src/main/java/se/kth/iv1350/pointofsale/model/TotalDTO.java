/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.model;

import se.kth.iv1350.pointofsale.integration.ItemDTO;

/**
 *
 * @author Maya
 */
public class TotalDTO {
    private Amount itemPrice;
    private Amount runningTotal;
    private String itemDescription;
    
    public TotalDTO(Amount itemPrice, Amount runningTotal, String itemDescription) {
        this.itemPrice = itemPrice;
        this.runningTotal = runningTotal;
        this.itemDescription = itemDescription;
    }
    
    public Amount getItemPrice() {
        return itemPrice;
    }
    
    public Amount getRunningTotal() {
        return runningTotal;
    }
    
    public String getItemDescription() {
        return itemDescription;
    }
}
