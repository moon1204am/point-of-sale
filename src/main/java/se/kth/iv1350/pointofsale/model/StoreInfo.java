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
public class StoreInfo {
    private String storeName;
    private String storeAddress;
    
    StoreInfo() {
        storeName = "Maya's Super Market";
        storeAddress = "4-15 Nipponbashi, 530-000, Osaka";
    }
    
    public String getStoreName() {
        return storeName;
    }
    
    public String getStoreAddress() {
        return storeAddress;
    }
}
