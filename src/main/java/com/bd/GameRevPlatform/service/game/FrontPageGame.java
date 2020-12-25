package com.bd.GameRevPlatform.service.game;

import com.bd.GameRevPlatform.model.Game;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author Timofti Gabriel
 */
public class FrontPageGame extends Game {

    private String genre;
    private String release_date_string;

    public FrontPageGame() {}

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRelease_date_string() {
        return release_date_string;
    }

    public void setRelease_date_string(Date release_date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.release_date_string = format.format(release_date);
    }

    @Override
    public String toString() {
        return "FrontPageGame{" +
                super.toString() +
                "genre='" + genre + '\'' +
                '}';
    }
}
