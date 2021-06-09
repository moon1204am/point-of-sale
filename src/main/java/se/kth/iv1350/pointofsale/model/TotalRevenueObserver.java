/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.model;

public interface TotalRevenueObserver {
    /**
     * Invoked when a payment has been paid.
     * @param totalAmountPaid The payment that has been paid.
     */
    void newPayment(Amount totalAmountPaid);
}
