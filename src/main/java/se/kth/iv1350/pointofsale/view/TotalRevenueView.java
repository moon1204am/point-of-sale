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
    private List<CashPayment> totalRevenue = new ArrayList<>();
    Amount total;
    
    /**
     * Creates a new instance, with all purchases set to zero.
     */
    public TotalRevenueView() {
        total = new Amount(0);
    }

    @Override
    public void newPayment(CashPayment totalAmountPaid) {
        addNewPayment(totalAmountPaid);
        printCurrentTotalRevenue();
    }
    
    private void addNewPayment(CashPayment totalAmountPaid) {
        totalRevenue.add(totalAmountPaid);
    }
    
    /**
     * Shows the total revenue since the program started.
     */
    private void printCurrentTotalRevenue() {
        CashPayment sale = null;
        total = new Amount(0);
        
        for(int i = 0; i < totalRevenue.size(); i++) {
            sale = totalRevenue.get(i);
            total = total.plus(sale.getAmountPaid());
        }
        System.out.println("Total revenue is " + total);
    }
}
