/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.messaging;

import com.djrapitops.nmplayer.ui.TextConsole;

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

    TextConsole con;

    /**
     * Creates a new MessageSender object.
     */
    public MessageSender() {
    }

    /**
     * Send a text to the user.
     * <p>
     * If a TextConsole has not been set, System.out will be used instead to
     * deliver the message.
     *
     * @param s Message to be sent.
     */
    public void send(String s) {
        if (con != null) {
            if (!con.getText().isEmpty()) {
                con.appendText(System.getProperty("line.separator"));
            }
            con.appendText(s);
            con.scrollTopProperty().set(Double.MAX_VALUE);
            con.scrollLeftProperty().set(Double.MAX_VALUE);
        } else {
            System.out.println(s);
        }
    }

    /**
     * Used to change the output of the MessageSender.
     * <p>
     * If given null as a parameter MessageSender will revert back to using
     * System.out as output.
     *
     * @param console TextConsole to output to.
     */
    public void setOutput(TextConsole console) {
        con = console;
    }

    /**
     * Used to Grab the MessageSender to access it's methods.
     *
     * @return INSTANCE of MessageSender created in
     * MessageSenderSingletonHolder.
     */
    public static MessageSender getInstance() {
        return MessageSenderSingletonHolder.INSTANCE;
    }

    private static class MessageSenderSingletonHolder {

        private static final MessageSender INSTANCE = new MessageSender();
    }
}
