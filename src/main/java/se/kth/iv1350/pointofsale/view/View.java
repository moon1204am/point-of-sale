package se.kth.iv1350.pointofsale.view;

import java.io.IOException;

import se.kth.iv1350.pointofsale.controller.Controller;
import se.kth.iv1350.pointofsale.integration.DatabaseNotReachableException;
import se.kth.iv1350.pointofsale.integration.UnknownItemIdentifierException;
import se.kth.iv1350.pointofsale.model.Amount;
import se.kth.iv1350.pointofsale.model.SaleDTO;
import se.kth.iv1350.pointofsale.model.TotalRevenueObserver;
import se.kth.iv1350.pointofsale.util.LogHandler;

/**
 * This is a placeholder for the real view. It contains a hardcoded execution with calls to all 
 * system operations in the controller.
 * @author Maya
 */
public class View {
    private Controller contr;
    //private Amount amountPaid;
    private ErrorMessageHandler errorMsgHandler = new ErrorMessageHandler();
    private LogHandler logger;
    private SaleDTO saleInfo;
    
    /**
     * Creates a new instance, that uses the specified controller for all calls to other layers. Also creates log handler.
     * 
     * @param contr The controller to use for all calls other layers.
     */
    public View(Controller contr) throws IOException {
        this.contr = contr;
        this.logger = new LogHandler();
        contr.addObserver(new TotalRevenueView());
    }
    
    /**
     * Performs a fake sale, by calling all system operations in the controller. 
     * Simulates a user input that generates calls to all system operations.
     */
    public void runFakeExecution() throws Exception {
        SaleDTO totalDTO;
        Amount price;
        Amount runningTotal;
        String itemDescription;
        int quantity;
        
        contr.startSale();
        System.out.println("A new sale has been started.");
        
        try {
            System.out.println("Trying to connect to inventory should generate an error.");
            
            totalDTO = contr.registerItem(123, 2);
            price = totalDTO.getItemPrice();
            runningTotal = totalDTO.getRunningTotal();
            itemDescription = totalDTO.getItemDescription();
            quantity = totalDTO.getQuantity();

            System.out.println("Registered item " + totalDTO.getItemIdentifier());
        
            System.out.println("Item: " + itemDescription + "\nQuantity: " + quantity + "\nPrice: " + price + "\nRunning Total: " + runningTotal + "\n");
            
            totalDTO = contr.registerItem(124);
            price = totalDTO.getItemPrice();
            runningTotal = totalDTO.getRunningTotal();
            itemDescription = totalDTO.getItemDescription();

            System.out.println("Registered item " + totalDTO.getItemIdentifier());
        
            System.out.println("Item: " + itemDescription + "\nQuantity: " + quantity + "\nPrice: " + price + "\nRunning Total: " + runningTotal + "\n");

            saleInfo = totalDTO;
        } catch(DatabaseNotReachableException exc) {
            handleException("Correctly failed to register item.", exc);
        }
        
        try {
            totalDTO = contr.registerItem(140, 4);
            price = totalDTO.getItemPrice();
            runningTotal = totalDTO.getRunningTotal();
            itemDescription = totalDTO.getItemDescription();
            quantity = totalDTO.getQuantity();

            System.out.println("Registered item " + totalDTO.getItemIdentifier());
            
            System.out.println("Item: " + itemDescription + "\nQuantity: " + quantity + "\nPrice: " + price + "\nRunning Total: " + runningTotal);
            
            System.out.println("\n");
        } catch(UnknownItemIdentifierException exc) {
            handleException("Could not find the item ID " + exc.getItemIdThatWasNotFound() + " in the inventory.", exc);
        } catch(DatabaseNotReachableException exc) {
            handleException(exc.getMessage(), exc);
        }

        try {
            Amount totalPrice = contr.endSale();
            Amount paidAmount = new Amount(20.25);
            System.out.println("To pay: " + totalPrice);
            System.out.println("Customer paid: " + paidAmount.getDouble() + "\n");
            contr.pay(paidAmount);
            contr.printReceipt();
            contr.sendSaleInformation(saleInfo);
        } catch (Exception e) {
            System.out.println("Could not complete payment, not enough money, " + e.getMessage() + " is missing.");
        }
    }
    
    private void handleException(String uiMsg, Exception exc) {
        errorMsgHandler.showErrorMsg(uiMsg);
        logger.logException(exc);
    }
    
}
