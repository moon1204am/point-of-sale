/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maya
 */
public class oldSaleDTO {
    private Amount runningTotal;
    private int quantity;
    private List<Item> scannedItems;
    Amount totalVatPrice;
    private Amount totalPriceVatIncluded;
    private Amount itemVatPrice;
    private Amount totalVatRate;

    public oldSaleDTO(Amount runningTotal, int quantity, List<Item> scannedItems, Amount totalVatPrice, Amount totalPriceVatIncluded, Amount itemVatPrice, Amount totalVatRate) {
        this.runningTotal = runningTotal;
        this.quantity = quantity;
        this.scannedItems = scannedItems;
        this.totalVatPrice = totalVatPrice;
        this.totalPriceVatIncluded = totalPriceVatIncluded;
        this.itemVatPrice = itemVatPrice;
        this.totalVatRate = totalVatRate;
    }

    /*private Amount totalPrice;
    private int totalQuantity;
    private Sale saleInfo;
    
    public SaleDTO(Amount runningTotal, int totalQuantity, Sale saleInfo) {
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
    }*/

    public Amount getTotalPriceVatIncluded() {
        return totalPriceVatIncluded;
    }

    public Amount getTotalVatRate() {
        return totalVatRate;
    }

    List<Item> getscannedItems() {
        return scannedItems;
    }

    public int getQuantity() {
        return quantity;
    }
}
