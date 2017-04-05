/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.messaging;

import com.djrapitops.nmplayer.ui.TextConsole;

/**
 *
 * @author Risto
 */
public class MessageSender {
    
    TextConsole con;

    public MessageSender() {
    }
    
    public void send(String s) {
        if (con != null) {
            if (!con.getText().isEmpty()) {
                con.appendText(System.getProperty("line.separator"));
            }
            con.appendText(s);
        } else {
            System.out.println(s);
        }
    }
    
    public static MessageSender getInstance() {
        return MessageSenderSingletonHolder.INSTANCE;
    }

    public void setOutput(TextConsole console) {
        con = console;
    }
    
    private static class MessageSenderSingletonHolder {
        private static final MessageSender INSTANCE = new MessageSender();
    }
}
