package se.kth.iv1350.pointofsale.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointofsale.controller.Controller;
import se.kth.iv1350.pointofsale.integration.ExternalSystemCreator;
import se.kth.iv1350.pointofsale.integration.Printer;

public class ViewTest {
    ExternalSystemCreator creator;
    Printer printer;
    private View instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    
    @BeforeEach
    public void setUp() throws IOException {
        creator = new ExternalSystemCreator();
        printer = new Printer();
        Controller contr = new Controller();
        instanceToTest = new View(contr);
        
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }
    
    @AfterEach
    public void tearDown() {
        creator = null;
        printer = null;
        instanceToTest = null;
        
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }
    
    @Test
    public void testRunFakeExecution() {
        try {
            instanceToTest.runFakeExecution();
            } catch(Exception e) {
                e.printStackTrace();
              }
        //instanceToTest.runFakeExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "started";
        assertTrue(printout.contains(expectedOutput), "UI did not start correctly.");
    }
}
