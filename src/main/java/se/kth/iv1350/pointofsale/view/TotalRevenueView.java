/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.view;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.pointofsale.model.Amount;
import se.kth.iv1350.pointofsale.model.CashPayment;
import se.kth.iv1350.pointofsale.model.TotalRevenueObserver;

public class TotalRevenueView implements TotalRevenueObserver {
    Amount total;
    
    /**
     * Creates a new instance, with all purchases set to zero.
     */
    public TotalRevenueView() {
        total = new Amount(0);
    }

    @Override
    public void newPayment(Amount totalAmountPaid) {
        addNewPayment(totalAmountPaid);
        printCurrentTotalRevenue();
    }
    
    private void addNewPayment(Amount amount) {
        total = total.plus(amount);
    }
    
    /**
     * Shows the total revenue since the program started.
     */
    private void printCurrentTotalRevenue() {
        System.out.println("\n");
        System.out.println("--------------------------");
        System.out.println("Total revenue is " + total);
        System.out.println("--------------------------");
        System.out.println("\n");
    }
}
