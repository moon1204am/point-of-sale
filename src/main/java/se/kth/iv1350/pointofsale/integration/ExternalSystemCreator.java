/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.integration;

public class ExternalSystemCreator {
    private AccountingSystem accountingSys;
    private ItemInventory itemInv;
    
    /**
     * Creates an instance of an accounting system and an item inventory.
     */
    public ExternalSystemCreator() {
        this.accountingSys = new AccountingSystem();
        this.itemInv = new ItemInventory();
    }
    
    public AccountingSystem getAccountingSystem() {
        return accountingSys;
    }
    
    public ItemInventory getItemInventory() {
        return itemInv;
    }
}