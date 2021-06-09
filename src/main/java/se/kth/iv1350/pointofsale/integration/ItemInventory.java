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
import se.kth.iv1350.pointofsale.model.SaleDTO;

public class ItemInventory extends java.lang.Object {
    private String itemName;
    private Amount price;
    private Amount vatRate;
    private String itemDescription;
    private int itemIdentifier;
    private List<ItemDTO> itemsInInventory = new ArrayList<>();
    private boolean connectedToDatabase = true;
    
    /**
     * Adds items to item inventory.
     */
    public ItemInventory() {
        addItems();
    }
    
    private void addItems() {
        
        ItemDTO milk = new ItemDTO("Milk", new Amount(1.5), new Amount(1.06), "Almond breeze", 123);
        ItemDTO bread = new ItemDTO("Bread", new Amount(2.55), new Amount(1.12), "Fluffy milk bread", 124);
        ItemDTO eggs = new ItemDTO("Eggs", new Amount(1.30), new Amount(1.25), "Eggs from happy hens", 125);
        ItemDTO icecream = new ItemDTO("Ice cream", new Amount(2.99), new Amount(1.06), "Matcha ice cream", 126);
        ItemDTO cucumber = new ItemDTO("Cheesecake", new Amount(2.0), new Amount(1.06), "Pillowy soft, light-as-air & heavenly cheesecake", 127);
        ItemDTO cereal = new ItemDTO("Cereal", new Amount(3.0), new Amount(1.12), "Kellog's Cornflakes", 128);
        ItemDTO yogurt = new ItemDTO("Yogurt", new Amount(1.3), new Amount(1.25), "Low Fat Yogurt", 129);
        ItemDTO strawberries = new ItemDTO("Strawberries", new Amount(2.5), new Amount(1.25), "Sweet Strawberries", 130);
        
        itemsInInventory.add(milk);
        itemsInInventory.add(bread);
        itemsInInventory.add(eggs);
        itemsInInventory.add(icecream);
        itemsInInventory.add(cucumber);
        itemsInInventory.add(cereal);
        itemsInInventory.add(yogurt);
        itemsInInventory.add(strawberries);
    }
    
    /**
     * Fetches the returned item that matched with the searched ID.
     * @param itemIdentifier
     * @return An <code>ItemDTO</code> that is the item that corresponds to the parameter item identifier.
     */
    public ItemDTO fetchItemFromInventory(int itemIdentifier) throws UnknownItemIdentifierException, SQLException {
        ItemDTO foundItem = findItemByID(itemIdentifier);
        return foundItem;
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
                    return currentIterationItem;
                }
            }
        
            return currentIterationItem;
        } catch(SQLException sqle) {
            throw new DatabaseNotReachableException("Could not register the item.", sqle);
        }
    }
    
    /**
     * If an item with a specific ID is registered, it returns wether its ID is valid or invalid.
     * @param itemId Method bases search on this item identifier number.
     * @return Returns true if item is in inventory.
     * @throws SQLException if the database call failed, or if the specific ID did not exist.
     */
    public boolean itemExists(int itemId) throws SQLException {
        boolean itemFound = false;
        
        ItemDTO registeredItem = findItemByID(itemId);

        if(registeredItem != null) {
            if(registeredItem.getItemIdentifier() == itemId) {
                itemFound = true;
            }
        }
        return itemFound;
    }

    public void updateInventory(SaleDTO totalDTO) {
    }

    /*@Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof ItemDTO)) {
            return false;
        }
        ItemDTO otherItemDTO = (ItemDTO) other;
        return this.itemName == otherItemDTO.getItemName() &&
        this.price == otherItemDTO.getPrice() &&
        this.vatRate == otherItemDTO.getVatRate() &&
        this.itemDescription == otherItemDTO.getItemDescription() &&
        this.itemIdentifier == otherItemDTO.getItemIdentifier();
    }*/

    /*@Override
    public boolean equals(ItemDTO expected, ItemDTO actual, String message) {
        if (actual == null || !(actual instanceof ItemDTO)) {
            return false;
        }
        return this.itemName == actual.getItemName() &&
                expected.getPrice() == actual.getPrice() &&
                expected.getVatRate() == actual.getVatRate() &&
                expected.getItemDescription() == actual.getItemDescription() &&
                expected.getItemIdentifier() == actual.getItemIdentifier();
    }*/
    
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