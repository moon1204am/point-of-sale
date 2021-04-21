package se.kth.iv1350.pointofsale.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.pointofsale.integration.ItemDTO;
import se.kth.iv1350.pointofsale.integration.Printer;

/**
 * One single sale made by one single costumer and payed with one payment.
 */
public class Sale {
    private LocalTime saleTime;
    //private Receipt receipt;
    
    private Amount runningTotal = new Amount(0.0);
    private List<Item> scannedItems = new ArrayList<>();
    Amount totalVatPrice = new Amount(0);
    private Amount totalPriceVatIncluded = new Amount(0.0);
    private Amount totalVatRate;
    private Amount itemVatPrice;
    private PriceInfo priceInfo;
        
    /**
     * Creates a new instance and saves the time of the sale.
     */
    public Sale() {
        saleTime = LocalTime.now();
    }
    
    /**
     * Provides information for every item being registered. Used for every item.
     * @param currentRegisteredItem The registered item, registered in the item inventory.
     * @param quantity The quantity of one registered item.
     * @return Returns an object with item price, running total and item description.
     */
    public TotalDTO currentRegisteredItemInfo(ItemDTO currentRegisteredItem, int quantity) {
        Amount itemPrice = currentRegisteredItem.getPrice();
        String itemDesc = currentRegisteredItem.getItemDescription();
        
        runningTotal = updateRunningTotal(currentRegisteredItem, quantity);
        scannedItems = addItemToScannedItems(currentRegisteredItem, quantity);
       
        TotalDTO total = new TotalDTO(itemPrice, runningTotal, itemDesc);
        return total;
    }
    
    /**
     * Checks if the item has already been scanned. If it has it returns that item.
     * @param itemIdentifier The specified ID used for searching for a same ID.
     * @return Returns the item that matched with the same ID.
     */
    private Item checkItemId(int itemIdentifier) {
        for(Item currentIterationItem: scannedItems) {
            if(currentIterationItem.getItemIdentifier() == itemIdentifier) {
                return currentIterationItem;
            }
        } return null;
    }
    
    /**
     * Increases the quantity of an item by how much the cashier chooses.
     * @param addedQuantity how much quantity that should be added.
     */
    private int increaseItemQuantity(int currentQuantity, int addedQuantity){
        System.out.println("quantity was increased");
        return currentQuantity + addedQuantity;
    }
    
    private List<Item> addItemToScannedItems(ItemDTO item, int quantity){
        Item currentIterationItem;
        int itemId = item.getItemIdentifier();
        boolean itemAlreadyRegistered = false;
        int updatedQuantity;

        if(!scannedItems.isEmpty()){
            for(int i = 0; i < scannedItems.size(); i++){
                currentIterationItem = scannedItems.get(i);
                if(currentIterationItem.getItemIdentifier() == itemId) {
                    itemAlreadyRegistered = true;

                    updatedQuantity = increaseItemQuantity(currentIterationItem.getQuantity(), quantity);
                    scannedItems.set(i, new Item(item, updatedQuantity));
                    System.out.println("Quantity of item was increased");
                } 
            }
            if(!itemAlreadyRegistered)
                addItem(new Item(item), quantity);
        } 

        if(scannedItems.isEmpty())
            addItem(new Item (item), quantity);

        return scannedItems;
    }
    
    /**
     * Adds one item to the scanned item list.
     * @param item The item being added.
     */
    private void addItem(Item item, int quantity) {
        System.out.println("item was added");
        item.setQuantity(quantity);
        scannedItems.add(item);
    }
    
    /**
     * Updates one sales running total.
     * @param item Each item that is purchased.
     * @param quantity How many of every item is purchased.
     * @return The price the customer will pay for their items.
     */
    private Amount updateRunningTotal(ItemDTO item, int quantity) {
        Amount itemPrice = item.getPrice();
        Amount itemVatRate = item.getVatRate();
        
        itemVatPrice = itemVatRate.multiply(itemPrice);
        Amount itemPriceVatIncluded = itemVatPrice.plus(itemPrice);
        
        for(int i = 0; i < quantity; i++) {
            runningTotal = runningTotal.plus(itemPriceVatIncluded);
            totalVatPrice = totalVatPrice.plus(itemVatPrice);
        }
        totalPriceVatIncluded = runningTotal;
        
        return runningTotal;
    }
    
    /*public Amount calculateTotalVatPrice(Amount itemPrice, Amount itemVatRate) {
        
        
        totalVatPrice = totalPriceVatIncluded.minus(totalPriceVatExcluded);
        totalVatPrice = totalVatPrice.divide(totalPriceVatIncluded);
        
        return totalVatPrice;
    }*/
    
    public void printReceipt(Amount totalCost, Amount amountPaid, Amount change) {
        Receipt receipt = new Receipt(totalPriceVatIncluded, amountPaid, change, scannedItems, totalVatPrice);
        //priceInfo = new PriceInfo(totalCost, totalVatRate, amountPaid, change); 
        //Receipt receipt = new Receipt(priceInfo, scannedItems, totalVatPrice);
        Printer printer = new Printer();
        printer.printReceipt(receipt);
    }
    
    public Amount getTotalPriceVatIncluded() {
        return totalPriceVatIncluded;
    }
    
    public Amount getTotalVatRate() {
        return totalVatRate;
    }
    
    List<Item> getscannedItems() {
        return scannedItems;
    }
}
