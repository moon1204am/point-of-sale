/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.model;

import java.text.DecimalFormat;

public class Amount {
    final double amount;
    
    /**
     * Creates a new instance, representing the amount 0.
     */
    public Amount() {
        this(0);
    }
    
    /**
     * Creates new instance, representing the specified amount.
     * @param amount The amount represented by the newly created instance.
     */
    public Amount(double amount) {
        this.amount = amount;
    }
    
    /**
     * Subtracts the specified (@link Amount) from this
     * object and and returns an (@link Amount)
     * instance with the result.
     * @param other The <code>Amount</code> to subtract.
     * @return The result of the subtraction.
     */
    public Amount minus(Amount other) {
        return new Amount(amount - other.amount);
    }
    
    /**
     * Adds the specified amount to the object and returns the new amount.
     * @param other The amount to add.
     * @return The result of the new amount.
     */
    public Amount plus(Amount other) {
        return new Amount(amount + other.amount);
    }
    
    /**
     * Multiplies the specified amount to the object and returns the new amount.
     * @param other The amount to multiply with.
     * @return The result of the new amount.
     */
    public Amount multiply(Amount other) {
        return new Amount(amount * other.amount);
    }
    
    /**
     * Used for calculating total VAT rate. Divides the specified amount, the total VAT price for the entire sale, with the total price VAT included.
     * @param other
     * @return 
     */
    public Amount divide(Amount other) {
        return new Amount(amount/other.amount);
    }
    
    public double getDouble() {
        return this.amount;
    }
            
    /*@Override
    public String toString () {
        String s = "";
        s += amount;

        return s;
    }*/
    
    @Override
    public String toString () {
        DecimalFormat df2 = new DecimalFormat("#.##");
    String s = "";
        s += df2.format(amount);

    return s;
    }
}
