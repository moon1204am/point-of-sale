package se.kth.iv1350.pointofsale.controller;

import se.kth.iv1350.pointofsale.integration.*;
import se.kth.iv1350.pointofsale.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the application's only controller. All calls to the model pass through this class. 
 */
public class Controller {
    private Sale sale;
    private ItemInventory inventory;
    private AccountingSystem accounting;
    private Amount change;
    private Amount totalCost;
    private Amount totalVatRate;
    private CashPayment payment;
    private CashRegister cashRegister;
    private List<TotalRevenueObserver> revenueObservers = new ArrayList<>();

    public Controller() {
        
    }
    
    public Controller(ExternalSystemCreator systemCreator) {
        this.accounting = systemCreator.getAccountingSystem();
        this.inventory = systemCreator.getItemInventory();
        this.cashRegister = new CashRegister(new Amount(1000));
    }
    
    /**
     * Starts a new sale. This method must be called before doing anything else during a sale.
     */
    public void startSale() {
        sale = new Sale();
        sale.addObserver(revenueObservers);
    }

    public SaleDTO registerItem(int itemIdentifier) throws SQLException, UnknownItemIdentifierException {
        return registerItem(itemIdentifier, 1);
    }
    
    /**
     * Registers every item the costumer will purchase.
     * @param itemIdentifier is every item's ID.
     * @param quantity represents how many of each item is purchased.
     * @return An <code>TotalDTO</code> total will return the whole cost and information about the sale.
     * @throws UnknownItemIdentifierException if the ID does not exist in the inventory.
     */
    public SaleDTO registerItem(int itemIdentifier, int quantity) throws UnknownItemIdentifierException, SQLException {
        boolean itemFound = inventory.itemExists(itemIdentifier);
        if(!itemFound) {
            throw new UnknownItemIdentifierException(itemIdentifier);
        }
        
        ItemDTO registeredItem = inventory.fetchItemFromInventory(itemIdentifier);
        
        SaleDTO total = sale.currentRegisteredItemInfo(registeredItem, quantity);
        return total;
    }
    
    public void pay(Amount amountPaid) throws Exception {
        payment.setAmountPaid(amountPaid);
        change = payment.getChange();
        cashRegister = new CashRegister(amountPaid);
        if(change.getDouble() == -1) {
            Amount left = payment.leftToPay();;
            throw new Exception(String.valueOf(left));
        }
        cashRegister.addPayment(payment);
    }
    
    public Amount endSale() {
        totalCost = sale.getTotalPriceVatIncluded();
        payment = new CashPayment(totalCost);
        
        totalVatRate = sale.getTotalVatRate();
        
        return totalCost;
    }
    
    public void printReceipt() {
        sale.printReceipt(payment.getAmountPaid(), change);
    }

    public void sendSaleInformation(SaleDTO totalDTO) {
        inventory.updateInventory(totalDTO);
        accounting.updateAccountingSystem(totalDTO);
    }

    public void addObserver(TotalRevenueObserver observer) {
        revenueObservers.add(observer);
    }
}
