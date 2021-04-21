/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.integration;

import se.kth.iv1350.pointofsale.model.Amount;

public class ItemDTO {
    private String itemName;
    private Amount price;
    private Amount vatRate;
    private String itemDescription;
    private int itemIdentifier;
    
    /**
     * Creates a new instance for every item.
     * @param price how much the item costs.
     * @param vatRate how much VAT rate for each item.
     * @param itemDescription contains information about an item.
     * @param itemIdentification the item ID.
     */
    public ItemDTO(String itemName, Amount price, Amount vatRate, String itemDescription, int itemIdentifier) {
        this.price = price;
        this.vatRate = vatRate;
        this.itemDescription = itemDescription;
        this.itemIdentifier = itemIdentifier;
        this.itemName = itemName;
    }
    
    public Amount getPrice() {
        return price;
    }
    
    public Amount getVatRate() {
        return vatRate;
    }
    
    public String getItemDescription() {
        return itemDescription;
    }
    
    public int getItemIdentifier() {
        return itemIdentifier;
    }
    
    public String getItemName() {
        return itemName;
    }
}