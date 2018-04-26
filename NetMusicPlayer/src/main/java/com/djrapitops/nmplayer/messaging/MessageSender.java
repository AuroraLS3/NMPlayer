/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.messaging;

import com.djrapitops.nmplayer.java.MethodRef;
import com.djrapitops.nmplayer.ui.TextConsole;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used to send information (text) to the user of the program.
 * <p>
 * THIS CLASS SHOULD NOT BE INITIALIZED WITH CLASS CONSTRUCTOR, MessageSender IS
 * A SINGLETON CLASS, use MessageSender.getInstance() instead.
 * <p>
 * JavaFx TextConsole can be set as output for the Messages. Old text will not
 * be overwritten in the TextConsole.
 *
 * @author Rsl1122
 * @see Phrase
 * @see TextConsole
 */
public class MessageSender {

    private MethodRef<String> output;

    private MessageSender() {
        this.output = msg -> Logger.getGlobal().log(Level.INFO, msg);
    }

    /**
     * Used to Grab the MessageSender to access it's methods.
     *
     * @return INSTANCE of MessageSender created in MessageSenderSingletonHolder.
     */
    public static MessageSender getInstance() {
        return MessageSenderSingletonHolder.INSTANCE;
    }

    /**
     * Send a text to the user.
     * <p>
     * If a TextConsole has not been set, System.out will be used instead to
     * deliver the message.
     *
     * @param message Message to be sent.
     */
    public void send(String message) {
        output.call(message);
    }

    /**
     * Used to change the output of the MessageSender.
     * <p>
     * If given null as a parameter MessageSender will revert back to using System.out as output.
     *
     * @param console TextConsole to output to.
     */
    public void setOutput(TextConsole console) {
        if (console != null) {
            output = message -> {
                console.appendText(message + System.getProperty("line.separator"));
                console.scrollTopProperty().set(Double.MAX_VALUE);
            };
        } else {
            output = msg -> Logger.getGlobal().log(Level.INFO, msg);
        }
    }

    public void setOutput(MethodRef<String> out) {
        output = out;
    }

    private static class MessageSenderSingletonHolder {

        private static final MessageSender INSTANCE = new MessageSender();
    }
}
