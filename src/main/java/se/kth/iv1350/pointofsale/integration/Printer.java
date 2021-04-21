/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.integration;

import se.kth.iv1350.pointofsale.model.Receipt;

/**
 * The interface to the printer, used for all printouts initiated by this
 * program.
 */
public class Printer {
    /**
     * Prints the specified receipt. This dummy implementation prints to
     * <code>System.out</code> instead of a printer.
     *
     * @param receipt
     */
    public void printReceipt(Receipt receipt) {
        System.out.println(receipt.buildReceipt());
    }
}
