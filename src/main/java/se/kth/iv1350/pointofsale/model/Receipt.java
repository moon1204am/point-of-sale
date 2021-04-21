package se.kth.iv1350.pointofsale.model;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Represents one receipt, which proves the payment for one sale.
 */
public class Receipt {
    private Sale saleInfo;
    private Amount totalCostInclVat;
    private Amount totalCostExclVat;
    private Amount amountPaid;
    private Amount change;
    //private PriceInfo priceInfo;
    private List<Item> boughtItems;
    private StoreInfo store = new StoreInfo();
    private Amount totalVatPrice;
    StringBuilder sb = new StringBuilder();
    
   /**
    * Creates an instance of an receipt.
    * @param totalCost Total price of the entire sale.
    * @param amountPaid How much the customer paid.
    * @param change How much change the customer should receive.
    * @param boughtItems The list of all items that was purchased.
    * @param totalVatRate The total VAT rate.
    */
    Receipt(Amount totalCostInclVat, Amount amountPaid, Amount change, List<Item> boughtItems, Amount totalVatPrice){
        this.totalCostInclVat = totalCostInclVat;
        this.amountPaid = amountPaid;
        this.change = change;
        this.boughtItems = boughtItems;
        this.totalVatPrice = totalVatPrice;
        this.totalCostExclVat = totalCostInclVat.minus(totalVatPrice);
    }
    
    /*Receipt(PriceInfo priceInfo, List<Item> boughtItems, Amount totalVatPrice) {
        this.priceInfo = priceInfo;
        this.boughtItems = boughtItems;
        this.totalVatPrice = totalVatPrice;
        this.totalCostExclVat = totalCostInclVat.minus(totalVatPrice);
    }*/
    
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
        for(int i = 0; i < boughtItems.size(); i++) {
            sb.append(boughtItems.get(i).getItemName()).append(" ");
            sb.append(boughtItems.get(i).getQuantity()).append(" x ");
            sb.append(boughtItems.get(i).getItemPriceVatIncluded()).append("\n");
        }
    }
    
    private void appendTotal() {
        /*DecimalFormat df2 = new DecimalFormat("#.##");
        
        sb.append("\nTotal price (excl. VAT): ");
        sb.append(df2.format(priceInfo.getTotalVatPrice().minus(priceInfo.getTotalVatPrice()))).append("\n");
        
        sb.append("Total VAT: ");
        sb.append(df2.format(priceInfo.getTotalVatPrice())).append("\n");
        
        sb.append("Total price (incl. VAT): ");
        sb.append(df2.format(priceInfo.getTotalPriceVatIncluded())).append("\n");
        
        sb.append("\nAmount paid: ");
        sb.append(df2.format(priceInfo.getAmountPaid()));
        
        sb.append("\nChange: ");
        sb.append(df2.format(priceInfo.getChange())).append("\n\n");*/
        
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
    
