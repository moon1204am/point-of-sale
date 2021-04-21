package se.kth.iv1350.pointofsale.controller;

import java.sql.SQLException;
import se.kth.iv1350.pointofsale.integration.ExternalSystemCreator;
import se.kth.iv1350.pointofsale.integration.ItemDTO;
import se.kth.iv1350.pointofsale.integration.ItemInventory;
import se.kth.iv1350.pointofsale.integration.UnknownItemIdentifierException;
import se.kth.iv1350.pointofsale.model.Amount;
import se.kth.iv1350.pointofsale.model.CashPayment;
import se.kth.iv1350.pointofsale.model.PriceInfo;
import se.kth.iv1350.pointofsale.model.Sale;
import se.kth.iv1350.pointofsale.model.TotalDTO;
import se.kth.iv1350.pointofsale.model.TotalRevenueObserver;
import se.kth.iv1350.pointofsale.view.TotalRevenueView;

/**
 * This is the application's only controller. All calls to the model pass through this class. 
 */
public class Controller {
    private Sale sale;
    public ItemInventory inventory;
    private ExternalSystemCreator systemCreator;
    private Amount change;
    private Amount totalCost;
    private Amount totalVatRate;
    private CashPayment payment;
    private PriceInfo price;
    private TotalRevenueObserver obs = new TotalRevenueView();
    
    public Controller() {
        
    }
    
    public Controller(ExternalSystemCreator systemCreator) {
        this.systemCreator = systemCreator;
        this.inventory = this.systemCreator.getItemInventory();
    }
    
    /**
     * Starts a new sale. This method must be called before doing anything else during a sale.
     */
    public void startSale() {
        sale = new Sale();
    }
    
    /**
     * Registers every item the costumer will purchase.
     * @param itemIdentifier is every item's ID.
     * @param quantity represents how many of each item is purchased.
     * @return total will return the whole cost and information about the sale.
     * @throws UnknownItemIdentifierException if the ID does not exist in the inventory.
     */
    public TotalDTO registerItem(int itemIdentifier, int quantity) throws UnknownItemIdentifierException, SQLException {
        
        if(quantity == 0) {
            quantity = 1;
        }
        
        boolean itemFound = inventory.findItem(itemIdentifier);
        //System.out.println("item was found " + itemFound);
        if(!itemFound) {
            throw new UnknownItemIdentifierException(itemIdentifier);
        }
        
        ItemDTO registeredItem = inventory.fetchItemFromInventory(itemIdentifier);
        
        TotalDTO total = sale.currentRegisteredItemInfo(registeredItem, quantity);
        return total;
    }
    
    public Amount pay(Amount amountPaid) {
        payment.setAmountPaid(amountPaid);
        change = payment.getChange();
        obs.newPayment(payment);
        return change;
    }
    
    public Amount endSale() {
        totalCost = sale.getTotalPriceVatIncluded();
        payment = new CashPayment(totalCost);
        
        totalVatRate = sale.getTotalVatRate();
        
        return totalCost;
    }
    
    public void printReceipt() {
        sale.printReceipt(totalCost, payment.getAmountPaid(), change);
    }
}
