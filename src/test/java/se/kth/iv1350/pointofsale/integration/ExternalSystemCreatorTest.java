/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExternalSystemCreatorTest {
    ExternalSystemCreator instance;
    
    @BeforeEach
    public void createInstanceOfExternalSystemCreator() {
        instance = new ExternalSystemCreator();
    }
    
    @AfterEach
    public void destoryInstanceOfExternalSystemCreator() {
        instance = null;
    }
    
    @Test
    public void testExternalSystemCreator() {
        ItemInventory result = instance.getItemInventory();
        assertTrue(result instanceof ItemInventory, "ExternalSystemCreator"
                + "did not create an instance of Inventory");
    }
    
}
