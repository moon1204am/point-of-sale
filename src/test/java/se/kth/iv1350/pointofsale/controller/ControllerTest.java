/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointofsale.integration.DatabaseNotReachableException;
import se.kth.iv1350.pointofsale.integration.ExternalSystemCreator;
import se.kth.iv1350.pointofsale.integration.UnknownItemIdentifierException;
import se.kth.iv1350.pointofsale.model.SaleDTO;

public class ControllerTest {

    @Test
    public void testRegisterNonExistingItem() throws Exception {
        ExternalSystemCreator systemCreator = new ExternalSystemCreator();
        Controller instance = new Controller(systemCreator);
        System.out.println("registerItem");
        
        int itemIdentifier = 0;
        int quantity = 1;
        
        try {
            SaleDTO expResult = null;
            SaleDTO result = instance.registerItem(itemIdentifier, quantity);
            assertEquals(expResult, result, "Could register nonexistent item.");
        } catch(DatabaseNotReachableException | UnknownItemIdentifierException exc) {
            assertTrue(exc.getMessage().contains("not be found") | exc.getMessage().contains("Could not register"), "Non-existing item was not found" + exc.getMessage());
        }
    }
}
