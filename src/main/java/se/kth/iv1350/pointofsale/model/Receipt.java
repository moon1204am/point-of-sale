package se.kth.iv1350.pointofsale.model;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Represents one receipt, which proves the payment for one sale.
 */
public class Receipt {
    private Amount totalCostInclVat;
    private Amount totalCostExclVat;
    private Amount amountPaid;
    private Amount change;
    private List<Item> boughtItems;
    private StoreInfo store = new StoreInfo();
    private Amount totalVatPrice;
    private Sale sale;
    StringBuilder sb = new StringBuilder();
    
   /**
    * Creates an instance of an receipt.
    * @param totalCostInclVat Total price of the entire sale.
    * @param amountPaid How much the customer paid.
    * @param change How much change the customer should receive.
    * @param boughtItems The list of all items that was purchased.
    * @param totalVatPrice The total VAT price.
    */
   Receipt(Amount totalCostInclVat, Amount amountPaid, Amount change, List<Item> boughtItems, Amount totalVatPrice){
        this.totalCostInclVat = totalCostInclVat;
        this.amountPaid = amountPaid;
        this.change = change;
        this.boughtItems = boughtItems;
        this.totalVatPrice = totalVatPrice;
        this.totalCostExclVat = totalCostInclVat.minus(totalVatPrice);
    }
    
    /**
     * Creates one receipt.
     * @return The receipt that was just created.
     */
    public StringBuilder buildReceipt() {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date currentDateAndTime = new Date();

        sb.append("RECEIPT\n");
        sb.append(store.getStoreName()).append("\n\n");
        appendBoughtItems();
        appendTotal();
        sb.append(df.format(currentDateAndTime)).append("\n");
        sb.append(store.getStoreAddress()).append("\n");
        sb.append("Thank you!\n");
        
        return sb;
    }
    
    private void appendBoughtItems(){
        for(Item item: boughtItems) {
            sb.append(item.getItemName()).append(" ");
            sb.append(item.getQuantity()).append(" x ");
            sb.append(item.getItemPriceVatIncluded()).append("\n");
        }
    }
    
    private void appendTotal() {
        sb.append("\nTotal price (excl. VAT): ");
        sb.append(totalCostExclVat).append("\n");
        
        sb.append("Total VAT: ");
        sb.append(totalVatPrice).append("\n");
        
        sb.append("Total price (incl. VAT): ");
        sb.append(totalCostInclVat).append("\n");
        
        sb.append("\nAmount paid: ");
        sb.append(amountPaid);
        
        sb.append("\nChange: ");
        sb.append(change).append("\n\n");
    }
}
    
