/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.model;

/**
 *
 * @author Maya
 */
public class SaleDTO {
    private Amount totalPrice;
    private int totalQuantity;
    private Sale saleInfo;
    
    public SaleDTO(Amount totalPrice, int totalQuantity, Sale saleInfo) {
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.saleInfo = saleInfo;
    }
    
    public Amount getTotalPrice() {
        return totalPrice;
    }
    
    public int getTotalQuantity() {
        return totalQuantity;
    }
    
    public Sale getSaleInfo() {
        return saleInfo;
    }
}
