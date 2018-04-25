/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.fileutils;

import com.djrapitops.nmplayer.messaging.MessageSender;
import com.djrapitops.nmplayer.messaging.Phrase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class is used to write caught <tt>Throwable</tt> objects to Errors.txt
 * in the folder the .jar resides in.
 * <p>
 * All of the methods are static.
 *
 * @author Rsl1122
 */
public class ErrorManager {

    /**
     * Logs caught Throwable to Errors.txt.
     *
     * @param source    Class that caught the exception
     * @param throwable Throwable that was caught by the program.
     */
    public static void toLog(Class source, Throwable throwable) {
        MessageSender.getInstance().send(Phrase.ERROR + "");
        List<String> stackTrace = new ArrayList<>();
        stackTrace.add(source.getName() + " Caught " + throwable);
        appendStackTrace(throwable, stackTrace);
        Throwable cause = throwable.getCause();
        while (cause != null) {
            stackTrace.add("caused by " + cause);
            appendStackTrace(cause, stackTrace);
            cause = cause.getCause();
        }
        stackTrace.add("");

        toLog(stackTrace);
    }

    private static void appendStackTrace(Throwable throwable, List<String> stackTrace) {
        for (StackTraceElement x : throwable.getStackTrace()) {
            stackTrace.add("  " + x);
        }
    }

    /**
     * Logs a message to the Errors.txt with a timestamp.
     *
     * @param message A line that will be written to the Errors.txt as a new
     *                line.
     */
    public static void toLog(String message) {
        String timestamp = formatTimeStamp(new Date().getTime());
        toLog(Collections.singletonList("[" + timestamp + "] " + message));
    }

    private static void toLog(List<String> toWrite) {
        File log = new File("Errors.txt");
        FileWriter.appendToFile(toWrite, log);
    }

    /**
     * Format a long value into readable format of (month) (day) (hh:mm:ss).
     *
     * @param ms ms since Epoch Date 1970
     * @return String of format (month) (day) (hh:mm:ss)
     */
    public static String formatTimeStamp(long ms) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd HH:mm:ss", Locale.ENGLISH);
        return formatter.format(ms);
    }
}
