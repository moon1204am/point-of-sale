/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.integration;

import se.kth.iv1350.pointofsale.integration.ItemDTO;

public class UnknownItemIdentifierException extends Exception {
    private int itemIdThatWasNotFound;
    
    /**
     * Creates a new instance with a message specifying for which item that couldn't be found in the inventory.
     * @param itemIdThatWasNotFound The item ID that was registered but could not be found in inventory. 
     */
    public UnknownItemIdentifierException(int itemIdThatWasNotFound) {
        super("Item ID " + itemIdThatWasNotFound + " could not be found in the inventory.");
        this.itemIdThatWasNotFound = itemIdThatWasNotFound;
    }
    
    /**
     * 
     * @return The Item that could not be found.
     */
    public int getItemIdThatWasNotFound() {
        return itemIdThatWasNotFound;
    }
}
