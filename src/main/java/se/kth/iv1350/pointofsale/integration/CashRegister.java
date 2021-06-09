/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.integration;

import se.kth.iv1350.pointofsale.model.Amount;
import se.kth.iv1350.pointofsale.model.CashPayment;

 /**
 * Represents a cash register. There shall be one instance of this class for
 * each register.
 */
public class CashRegister {
    private Amount balance = new Amount();
    private Amount money;

    public CashRegister(Amount money) {
        this.money = money;
    }
    
    public void addPayment(CashPayment payment) {
        balance = balance.plus(payment.getTotalPrice());
    }
}
