/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.model;

public class CashPayment {
    Amount amountPaid;
    Amount totalPrice;
    Amount change;
    
    /**
     * Creates a new instance.The specified amount to pay.
     *
     * @param totalPrice
     */
    public CashPayment(Amount totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public void setAmountPaid(Amount amountPaid){
        this.amountPaid = amountPaid;
    }
    
    /**
     * Calculates the total cost of the specified sale.
     * 
     * @param paidSale The sale for which the customer is paying.
     */
    /*void calculateTotalPrice(Sale paidSale) {
        totalPrice = paidSale.getTotalPriceVatIncluded();
    }*/
    
    public Amount getTotalPrice() {
        return totalPrice;
    }
    
    public Amount getAmountPaid() {
        return amountPaid;
    }
    
    /*public double getAmount() {
        return amountPaid.getDouble();
    }*/
    
    /**
     * @return The amount of change the customer shall have.
     */
    public Amount getChange() {
        return amountPaid.minus(totalPrice);
    }
}
