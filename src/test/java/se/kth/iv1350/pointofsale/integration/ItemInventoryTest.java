/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.integration;

import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointofsale.model.Amount;
import se.kth.iv1350.pointofsale.model.Item;

public class ItemInventoryTest {

    @Test
    public void testGetExistingItemFromInventory() throws UnknownItemIdentifierException, SQLException {
        ItemDTO existingItem = new ItemDTO("Bread", new Amount(2.55), new Amount(0.05), "Fluffy milk bread", 124);
        ItemInventory instance = new ItemInventory();
        try {
            //String expResult = existingItem.getItemName();
            //String result = instance.fetchItemFromInventory(124).getItemName();
            
            ItemDTO expResult = existingItem;
            ItemDTO result = instance.fetchItemFromInventory(124);
            assertEquals(expResult, result, "Existing item was not found");
        } catch(DatabaseNotReachableException exc) {
            assertTrue(exc.getMessage().contains("Could not register"), "Existing item was not found" + exc.getMessage());
        }
    }
    
    @Test
    public void testGetNonExistingItemFromInventory() throws UnknownItemIdentifierException, SQLException {
        ItemDTO nonExistingItem = null;
        ItemInventory instance = new ItemInventory();
        try {
            //String expResult = nonExistingItem.getItemName();
            //String result = instance.fetchItemFromInventory(0).getItemName();
            
            ItemDTO expResult = nonExistingItem;
            ItemDTO result = instance.fetchItemFromInventory(10);
            
            assertEquals(expResult, result, "Non-existing item was found" + result.getItemName());
        } catch(DatabaseNotReachableException exc) {
            assertTrue(exc.getMessage().contains("Could not register"), "Non-existing item was not found" + exc.getMessage());
        }
    }
}
