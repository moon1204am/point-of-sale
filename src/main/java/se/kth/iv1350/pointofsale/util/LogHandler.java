/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.pointofsale.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for the log.
 */
public class LogHandler {
    private static final String LOG_FILE_NAME = "pointofsale-log.txt";
    private PrintWriter logFile;
    
    public LogHandler() throws IOException {
        logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME), true);
    }
    
    /**
     * Writes a log entry describing a thrown exception.
     * 
     * @param exception The exception that shall be logged.
     */
    public void logException(Exception exception) {
        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append(createTime());
        logMsgBuilder.append(", Exception was thrown: ");
        logMsgBuilder.append(exception.getMessage());
        if (exception.getMessage().contains("Could not register")) {
            logFile.println(logMsgBuilder);
        }
        System.out.println(logMsgBuilder);
        exception.printStackTrace(logFile);
    }

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}
