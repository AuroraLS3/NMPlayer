/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.ui;

import com.djrapitops.nmplayer.messaging.MessageSender;
import javafx.scene.control.TextArea;

/**
 *
 * @author ristolah
 */
public class TextConsole extends TextArea {

    public TextConsole() {
        super.setEditable(false);
        super.setMouseTransparent(true);
        super.setFocusTraversable(false);
        super.maxHeight(3);
        MessageSender.getInstance().setOutput(this);
    }

}
