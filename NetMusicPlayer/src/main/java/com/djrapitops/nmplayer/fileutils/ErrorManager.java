/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.fileutils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Rsl1122
 */
public class ErrorManager {

    /**
     * Logs caugth Throwable to Errors.txt
     *
     * @param source Name of the source class
     * @param e Thrown error
     */
    public static void toLog(String source, Throwable e) throws IOException {
        // TODO Show error message to user.
        toLog(source + " Caught " + e);
        for (StackTraceElement x : e.getStackTrace()) {
            toLog("  " + x);
        }
        toLog("");
    }

    /**
     * Logs multiple caught Throwables to Errors.txt.
     *
     * @param source Class name the exception was caught in.
     * @param e Collection of Throwables, eg NullPointerException
     */
    public static void toLog(String source, Collection<Throwable> e) throws IOException {
        for (Throwable ex : e) {
            toLog(source, ex);
        }
    }

    /**
     * Logs a message to the Errors.txt with a timestamp.
     *
     * @param message Message to log to Errors.txt
     */
    public static void toLog(String message) throws IOException {
        File log = new File("Errors.txt");
        if (!log.exists()) {
            log.createNewFile();
        }
        FileWriter fw = new FileWriter(log, true);
        try (PrintWriter pw = new PrintWriter(fw)) {
            String timestamp = formatTimeStamp(new Date().getTime());
            pw.println("[" + timestamp + "] " + message);
            pw.flush();
        }
    }

    /**
     * Format a long value into human readable format of (month) (day)
     * (hh:mm:ss).
     *
     * @param ms ms since Epoch Date 1970
     * @return String of format (month) (day) (hh:mm:ss)
     */
    public static String formatTimeStamp(long ms) {
        Date sfd = new Date(ms);
        return ("" + sfd).substring(4, 19);
    }
}
