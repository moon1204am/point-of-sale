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
    private Amount runningTotal = new Amount(0.0);
    private List<Item> scannedItems = new ArrayList<>();
    Amount totalVatPrice = new Amount(0);
    private Amount totalPriceVatIncluded = new Amount(0.0);
    //private Amount totalVatRate;
    private Amount itemVatPrice;
    private int quantity;
    private List<TotalRevenueObserver> revenueObservers = new ArrayList<>();
        
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
    public SaleDTO currentRegisteredItemInfo(ItemDTO currentRegisteredItem, int quantity) {
        Amount itemPrice = currentRegisteredItem.getPrice();
        String itemDesc = currentRegisteredItem.getItemDescription();
        
        runningTotal = updateRunningTotal(currentRegisteredItem, quantity);
        scannedItems = addItemToScannedItems(currentRegisteredItem, quantity);
       
        SaleDTO total = new SaleDTO(itemPrice, runningTotal, itemDesc, quantity, currentRegisteredItem.getItemIdentifier());
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
    private int increaseItemQuantity(int currentQuantity, int addedQuantity) {
        return currentQuantity + addedQuantity;
    }
    
    private List<Item> addItemToScannedItems(ItemDTO item, int quantity) {
        Item currentIterationItem;
        int itemId = item.getItemIdentifier();
        int updatedQuantity;

        if(!scannedItems.isEmpty()) {
            if(itemAlreadyScanned(itemId)) {
                int i = getItemIndex(itemId);
                currentIterationItem = scannedItems.get(i);
                updatedQuantity = increaseItemQuantity(currentIterationItem.getQuantity(), quantity);
                scannedItems.set(i, new Item(item, updatedQuantity));
            } else {
                addItem(new Item(item), quantity);
            }
        }
        else if(scannedItems.isEmpty()) {
            addItem(new Item(item), quantity);
        }
        return scannedItems;
    }

    private boolean itemAlreadyScanned(int itemId) {
        boolean itemAlreadyRegistered = false;

        if(getItemIndex(itemId) > -1) {
            itemAlreadyRegistered = true;
            return itemAlreadyRegistered;
        }
        return itemAlreadyRegistered;
    }

    private int getItemIndex(int itemId) {
        Item currentIterationItem;

        for(int i = 0; i < scannedItems.size(); i++){
            currentIterationItem = scannedItems.get(i);
            if(currentIterationItem.getItemIdentifier() == itemId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds one item to the scanned item list.
     * @param item The item being added.
     */
    private void addItem(Item item, int quantity) {
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
        Amount vatPriceOnly = itemVatPrice.minus(itemPrice);
        
        for(int i = 0; i < quantity; i++) {
            runningTotal = runningTotal.plus(itemVatPrice);
            totalVatPrice = vatPriceOnly.plus(totalVatPrice);
        }

        totalPriceVatIncluded = runningTotal;
        return runningTotal;
    }
    
    public void printReceipt(Amount amountPaid, Amount change) {
        Receipt receipt = new Receipt(totalPriceVatIncluded, amountPaid, change, scannedItems, totalVatPrice);
        Printer printer = new Printer();
        printer.printReceipt(receipt);
    }

    public void addObserver(TotalRevenueObserver observer) {
        revenueObservers.add(observer);
    }

    public void addObserver(List<TotalRevenueObserver> observers) {
        revenueObservers.addAll(observers);
    }

    public void notifyObservers() {
        for (TotalRevenueObserver observer: revenueObservers) {
            observer.newPayment(runningTotal);
            //observer.newPayment(totalPriceVatIncluded);
        }
    }
    
    public Amount getTotalPriceVatIncluded() {
        notifyObservers();
        return totalPriceVatIncluded;
    }
    
    public Amount getTotalVatRate() {
        //return totalVatRate;
        return totalVatPrice;
    }
    
    List<Item> getscannedItems() {
        return scannedItems;
    }

    public int getQuantity() {
        return quantity;
    }
}
