/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.model;

import se.kth.iv1350.pointofsale.integration.ItemDTO;

public class Item {
    private Amount itemPriceVatExcluded;
    private Amount itemPriceVatIncluded;
    private Amount vatRate;
    private String itemDescription;
    private int itemIdentifier;
    private String itemName;
    private int quantity;
    
    /**
     * Creates an instance of an item.
     * @param itemDTO 
     */
    public Item(ItemDTO itemDTO) {
        this.itemPriceVatExcluded = itemDTO.getPrice();
        this.vatRate = itemDTO.getVatRate();
        this.itemDescription = itemDTO.getItemDescription();
        this.itemIdentifier = itemDTO.getItemIdentifier();
        this.itemName = itemDTO.getItemName();
        this.quantity = quantity;
        itemPriceVatIncluded = calculatePriceVatIncluded(itemDTO);
    }
    
    public Item(ItemDTO itemDTO, int quantity) {
        this.itemPriceVatExcluded = itemDTO.getPrice();
        this.vatRate = itemDTO.getVatRate();
        this.itemDescription = itemDTO.getItemDescription();
        this.itemIdentifier = itemDTO.getItemIdentifier();
        this.itemName = itemDTO.getItemName();
        this.quantity = quantity;
        itemPriceVatIncluded = calculatePriceVatIncluded(itemDTO);
    }
    
    /**
     * Calculates the total price of an item by multiplying the item's price with the VAT rate.
     * @param itemDTO The item that we want the price of.
     * @return The price of the item with VAT included.
     */
    private Amount calculatePriceVatIncluded(ItemDTO itemDTO) {
        Amount itemVatRate = itemDTO.getVatRate();
        Amount itemPriceVatExcluded = itemDTO.getPrice();
        Amount itemVatPrice = itemPriceVatExcluded.multiply(itemVatRate);
        //Amount itemPriceVatIncluded = itemPriceVatExcluded.plus(itemVatPrice);
        
        //return itemPriceVatIncluded;
        return itemVatPrice;
    }
    
    public Amount getItemPriceVatIncluded() {
        return itemPriceVatIncluded;
    }
    
    public Amount getItemPriceVatExcluded() {
        return itemPriceVatExcluded;
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
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString () {
    String s = "";
        s += itemName + " ";
        s += quantity + " x ";
        s += itemPriceVatIncluded;

    return s;
    }
}
