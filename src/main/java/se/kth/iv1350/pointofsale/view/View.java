package se.kth.iv1350.pointofsale.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import se.kth.iv1350.pointofsale.controller.Controller;
import se.kth.iv1350.pointofsale.integration.DatabaseNotReachableException;
import se.kth.iv1350.pointofsale.integration.UnknownItemIdentifierException;
import se.kth.iv1350.pointofsale.model.Amount;
import se.kth.iv1350.pointofsale.model.TotalDTO;
import se.kth.iv1350.pointofsale.util.LogHandler;

/**
 * This is a placeholder for the real view. It contains a hardcoded execution with calls to all 
 * system operations in the controller.
 * @author Maya
 */
public class View {
    private Controller contr;
    private Amount amountPaid;
    private ErrorMessageHandler errorMsgHandler = new ErrorMessageHandler();
    private LogHandler logger;
    
    /**
     * Creates a new instance, that uses the specified controller for all calls to other layers.
     * 
     * @param contr The controller to use for all calls other layers.
     */
    public View(Controller contr) throws IOException {
        this.contr = contr;
        this.logger = new LogHandler();
    }
    
    /**
     * Performs a fake sale, by calling all system operations in the controller. 
     * Simulates a user input that generates calls to all system operations.
     */
    public void runFakeExecution() throws UnknownItemIdentifierException, SQLException {
        TotalDTO total;
        String itemDescription;
        Amount price;
        Amount runningTotal;
        
        contr.startSale();
        System.out.println("A new sale has been started.");
        
        try {
            System.out.println("Trying to connect to inventory should generate an error.");
            
            total = contr.registerItem(123, 2);
            price = total.getItemPrice();
            runningTotal = total.getRunningTotal();
            itemDescription = total.getItemDescription();
        
            System.out.println("Item: " + itemDescription + "\nPrice: " + price + "\nRunning Total: " + runningTotal);
            
            System.out.println("\n");
            
            total = contr.registerItem(124, 1);
            price = total.getItemPrice();
            runningTotal = total.getRunningTotal();
            itemDescription = total.getItemDescription();
        
            System.out.println("Item: " + itemDescription + "\nPrice: " + price + "\nRunning Total: " + runningTotal);
            
        }
        
        catch(DatabaseNotReachableException exc) {
            handleException("Correctly failed to register item.", exc);
        }
        
        try {
            total = contr.registerItem(140, 4);
            price = total.getItemPrice();
            runningTotal = total.getRunningTotal();
            itemDescription = total.getItemDescription();
            
            System.out.println("Item: " + itemDescription + "\nPrice: " + price + "\nRunning Total: " + runningTotal);
            
            System.out.println("\n");
        }
        
        catch(DatabaseNotReachableException | UnknownItemIdentifierException exc) {
            handleException("Could not find the item ID in the inventory.", exc);
        }
        
        Amount totalPrice = contr.endSale();
        System.out.println(totalPrice);
        
        contr.pay(new Amount(10.25));
        
        System.out.println("\n\n");
        
        contr.printReceipt();
        System.out.println("\n");
        
    }
    
    private void handleException(String uiMsg, Exception exc) {
        errorMsgHandler.showErrorMsg(uiMsg);
        logger.logException(exc);
    }
    
}
