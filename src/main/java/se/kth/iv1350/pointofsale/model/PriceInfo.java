/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.model;

public class PriceInfo {
    private Amount totalPriceVatIncluded;
    private Amount totalVatPrice;
    private Amount amountPaid;
    private Amount change;
    
    public PriceInfo(Amount totalPriceVatIncluded, Amount totalVatPrice, Amount amountPaid, Amount change) {
        this.totalPriceVatIncluded = totalPriceVatIncluded;
        this.totalVatPrice = totalVatPrice;
        this.amountPaid = amountPaid;
        this.change = change;
    }
    
    public Amount getTotalPriceVatIncluded() {
        return totalPriceVatIncluded;
    }
    
    public Amount getTotalVatPrice() {
        return totalVatPrice;
    }
    
    public Amount getAmountPaid() {
        return amountPaid;
    }
    
    public Amount getChange() {
        return change;
    }
    
}
