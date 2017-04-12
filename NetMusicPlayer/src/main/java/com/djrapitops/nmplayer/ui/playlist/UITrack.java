
package com.djrapitops.nmplayer.ui.playlist;

import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.functionality.Track;
import com.djrapitops.nmplayer.ui.Updateable;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 *
 * @author ristolah
 */
public class UITrack extends HBox implements Updateable{

    private final Track track;
    private final Updateable u;

    public UITrack(Track track, Updateable u) {
        this.u = u;
        this.track = track;
        super.setAlignment(Pos.CENTER_LEFT);
        super.alignmentProperty().isBound();
        super.setSpacing(5);        
        super.setStyle("-fx-background-color: Lightgrey");
        super.getChildren().add(new SelectButton(this));
        super.getChildren().add(new RemoveButton(this));
    }

    public Track getTrack() {
        return track;
    }

    @Override
    public void update() {
        u.update();
    }
}
