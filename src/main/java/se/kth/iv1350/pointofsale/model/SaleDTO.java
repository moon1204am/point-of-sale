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
public class SaleDTO {
    private Amount itemPrice;
    private Amount runningTotal;
    private String itemDescription;
    private int quantity;
    private int itemIdentifier;
    
    public SaleDTO(Amount itemPrice, Amount runningTotal, String itemDescription, int quantity, int itemIdentifier) {
        this.itemPrice = itemPrice;
        this.runningTotal = runningTotal;
        this.itemDescription = itemDescription;
        this.quantity = quantity;
        this.itemIdentifier = itemIdentifier;
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

    public int getQuantity() {
        return quantity;
    }

    public int getItemIdentifier() {
        return itemIdentifier;
    }
}
