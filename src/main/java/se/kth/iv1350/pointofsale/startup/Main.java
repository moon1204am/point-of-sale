/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.startup;

import java.io.IOException;
import java.sql.SQLException;
import se.kth.iv1350.pointofsale.controller.Controller;
import se.kth.iv1350.pointofsale.integration.ExternalSystemCreator;
import se.kth.iv1350.pointofsale.integration.UnknownItemIdentifierException;
import se.kth.iv1350.pointofsale.view.TotalRevenueView;
import se.kth.iv1350.pointofsale.view.View;

/**
 * Starts the entire application. Contains the main method used to start the application.
 * @author Maya
 */
public class Main {
    /**
     * The main method used to start the entire application.
     * @param args The application does not take any command line parameters.
     */
    public static void main (String[] args) throws Exception {
        ExternalSystemCreator systmCreator = new ExternalSystemCreator();
        Controller contrl = new Controller(systmCreator);
        
        View view = new View(contrl);
        for(int i = 0; i < 3; i++) {
            view.runFakeExecution();
        }
        //view.runFakeExecution();
    }
    
}
