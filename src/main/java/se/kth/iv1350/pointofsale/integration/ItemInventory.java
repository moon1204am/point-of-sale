/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.integration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.pointofsale.model.Amount;

public class ItemInventory {
    private String itemName;
    private Amount price;
    private Amount vatRate;
    private String itemDescription;
    private List<ItemDTO> itemsInInventory = new ArrayList<>();
    private boolean connectedToDatabase = true;
    
    /**
     * Adds items to item inventory.
     */
    public ItemInventory() {
        addItems();
    }
    
    private void addItems() {
        
        ItemDTO milk = new ItemDTO("Milk", new Amount(1.5), new Amount(0.01), "Milk", 123);
        ItemDTO bread = new ItemDTO("Bread", new Amount(2.55), new Amount(0.05), "Bread", 124);
        ItemDTO eggs = new ItemDTO("Eggs", new Amount(1.32), new Amount(0.6), "Eggs", 125);
        ItemDTO icecream = new ItemDTO("Ice cream", new Amount(2.99), new Amount(1.5), "Ice cream", 126);
        ItemDTO cucumber = new ItemDTO("Cucumber", new Amount(2.0), new Amount(1.35), "Cucumber", 127);
        ItemDTO cereal = new ItemDTO("Cereal", new Amount(3.0), new Amount(2.99), "Cereal", 128);
        ItemDTO yogurt = new ItemDTO("Yogurt", new Amount(1.3), new Amount(0.02), "Yogurt", 129);
        ItemDTO strawberries = new ItemDTO("Strawberries", new Amount(2.1), new Amount(1.9), "Strawberries", 130);
        
        itemsInInventory.add(milk);
        itemsInInventory.add(bread);
        itemsInInventory.add(eggs);
        itemsInInventory.add(icecream);
        itemsInInventory.add(cucumber);
        itemsInInventory.add(cereal);
        itemsInInventory.add(yogurt);
        itemsInInventory.add(strawberries);

        
        /*ItemData milk = new ItemData("Milk", new Amount(1.5), new Amount(0.01), "Almond breeze", 123);
        ItemData bread = new ItemData("Bread", new Amount(2.55), new Amount(0.05), "Fluffy milk bread", 124);
        ItemData eggs = new ItemData("Eggs", new Amount(1.32), new Amount(0.6), "Eggs from happy hens", 125);
        ItemData icecream = new ItemData("Ice cream", new Amount(2.99), new Amount(1.5), "Matcha ice cream", 126);
        ItemData cucumber = new ItemData("Cheesecake", new Amount(2.0), new Amount(1.35), "Pillowy soft, light-as-air & heavenly cheesecake,", 127);
        ItemData cereal = new ItemData("Cereal", new Amount(3.0), new Amount(2.99), "Kellog's Cornflakes", 128);
        ItemData yogurt = new ItemData("Yogurt", new Amount(1.3), new Amount(0.02), "Low Fat Yogurt", 129);
        ItemData strawberries = new ItemData("Strawberries", new Amount(2.1), new Amount(1.9), "Sweet Strawberries", 130);
        
        itemsInInventory.add(milk);
        itemsInInventory.add(bread);
        itemsInInventory.add(eggs);
        itemsInInventory.add(icecream);
        itemsInInventory.add(cucumber);
        itemsInInventory.add(cereal);
        itemsInInventory.add(yogurt);
        itemsInInventory.add(strawberries);*/
    }
    
    /**
     * Fetches the returned item that matched with the searched ID.
     * @param itemIdentifier
     * @return An <code>ItemDTO</code> that is the item that corresponds to the parameter item identifier.
     */
    public ItemDTO fetchItemFromInventory(int itemIdentifier) throws UnknownItemIdentifierException, SQLException {
        ItemDTO foundItem = findItemByID(itemIdentifier);
        
        if(foundItem != null) {
            itemName = foundItem.getItemName();
            price = foundItem.getPrice();
            vatRate = foundItem.getVatRate();
            itemDescription = foundItem.getItemDescription();
            itemIdentifier = foundItem.getItemIdentifier();
        }
        
        ItemDTO registeredItem = new ItemDTO(itemName, price, vatRate, itemDescription, itemIdentifier);
        return registeredItem;
    }
    
    /**
     * Gets an item from the item inventory if it matches with the specified item ID.
     * @param itemIdentifier The item's ID.
     * @return An <code>ItemData</code> that represents the item with the matched ID by the parameter <code>itemIdentifier</code>.
     */
    private ItemDTO findItemByID(int itemIdentifier) throws SQLException, DatabaseNotReachableException {
        try {
            ItemDTO currentIterationItem = null;
            if(connectedToDatabase == false) {
                throw new SQLException();
            }
                
            for(int i = 0; i < itemsInInventory.size(); i++) {
                currentIterationItem = itemsInInventory.get(i);
                if(currentIterationItem.getItemIdentifier() == itemIdentifier) {
                    break;
                }
                
                
            }
            
            /*for (ItemDTO item: itemsInInventory) {
                if(item.getItemIdentifier() == itemIdentifier) {
                    return item;
                }
           }*/
        
            return currentIterationItem;
        }
        
        catch(SQLException sqle) {
            throw new DatabaseNotReachableException("Could not register the item.", sqle);
        }
    }
    
    /**
     * If an item with a specific ID is registered, it returns wether its ID is valid or invalid.
     * @param itemId Method bases search on this item identifier number.
     * @return Returns true if item is in inventory.
     * @throws SQLException if the database call failed, or if the specific ID did not exist.
     */
    public boolean findItem(int itemId) throws SQLException {
        boolean itemFound = false;
        
        ItemDTO registeredItem = findItemByID(itemId);
         
        //should use equals() instead
        if(registeredItem.getItemIdentifier() == itemId) {
            itemFound = true;
        }
    
        return itemFound;
    }
    
    /*private static class ItemData {
        private String itemName;
        private Amount price;
        private Amount vatRate;
        private String itemDesc;
        private int itemIdentifier;
        
        public ItemData(String itemName, Amount price, Amount vatRate, String itemDesc, int itemID) {
            this.itemName = itemName;
            this.price = price;
            this.vatRate = vatRate;
            this.itemDesc = itemDesc;
            this.itemIdentifier = itemID;
        }
    }*/
}