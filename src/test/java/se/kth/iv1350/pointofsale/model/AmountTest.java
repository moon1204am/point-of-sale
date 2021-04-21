/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AmountTest {
    private Amount amtNoArgConstr;
    private Amount amtWithAmtThree;
    
    @BeforeEach
    public void setUp() {
        amtNoArgConstr = new Amount();
        amtWithAmtThree = new Amount(3);
    }
    
    @AfterEach
    public void tearDown() {
        amtNoArgConstr = null;
        amtWithAmtThree = null;
    }
    
    @Test
    public void testMinus() {
        double amountOfOperand1 = 10;
        double amountOfOperand2 = 3;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 - amountOfOperand2);
        Amount result = operand1.minus(operand2);
        assertEquals(expResult.toString(), result.toString(), "Wrong subtraction result");
    }

    @Test
    public void testMinusNegativeResult() {
        double amountOfOperand1 = 2;
        double amountOfOperand2 = 5;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 - amountOfOperand2);
        Amount result = operand1.minus(operand2);
        assertEquals(expResult.toString(), result.toString(), "Wrong subtraction result");
    }
    
    @Test
    public void testMinusZeroResultNegativeOperand() {
        double amountOfOperand1 = -5;
        double amountOfOperand2 = -5;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 - amountOfOperand2);
        Amount result = operand1.minus(operand2);
        assertEquals(expResult.toString(), result.toString(), "Wrong subtraction result");
    }
    
    @Test
    public void testPlus() {
        double amountOfOperand1 = 5;
        double amountOfOperand2 = 2;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 + amountOfOperand2);
        Amount result = operand1.plus(operand2);
        assertEquals(expResult.toString(), result.toString(), "Wrong addition result");
    }

    @Test
    public void testPlusNegeativeResult() {
        double amountOfOperand1 = 2;
        double amountOfOperand2 = -5;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 + amountOfOperand2);
        Amount result = operand1.plus(operand2);
        assertEquals(expResult.toString(), result.toString(), "Wrong addition result");
    }
    
    @Test
    public void testPlusZeroResultNegativeOperand() {
        double amountOfOperand1 = -2;
        double amountOfOperand2 = 2;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 + amountOfOperand2);
        Amount result = operand1.plus(operand2);
        assertEquals(expResult.toString(), result.toString(), "Wrong addition result");
    }
    
    @Test
    public void testMultiply() {
        double amountOfOperand1 = 5;
        double amountOfOperand2 = 2;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 * amountOfOperand2);
        Amount result = operand1.multiply(operand2);
        assertEquals(expResult.toString(), result.toString(), "Wrong multiplication result");
    }

    @Test
    public void toStringPosAmt() {
        double representedAmt = 10;
        Amount amount = new Amount(representedAmt);
        String expResult = Double.toString(representedAmt);
        String result = amount.toString();
        assertEquals(expResult, result.toString(), "Wrong string returned by toString");
    }

    @Test
    public void toStringNegAmt() {
        double representedAmt = -10;
        Amount amount = new Amount(representedAmt);
        String expResult = Double.toString(representedAmt);
        String result = amount.toString();
        assertEquals(expResult, result.toString(), "Wrong string returned by toString");
    }

    @Test
    public void toStringZeroAmt() {
        double representedAmt = 0;
        Amount amount = new Amount(representedAmt);
        String expResult = Double.toString(representedAmt);
        String result = amount.toString();
        assertEquals(expResult, result.toString(), "Wrong string returned by toString");
    }
    
}