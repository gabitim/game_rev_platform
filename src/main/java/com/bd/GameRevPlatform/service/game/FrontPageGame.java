package com.bd.GameRevPlatform.service.game;

import com.bd.GameRevPlatform.model.Game;

import java.util.Date;

/**
 * @author Timofti Gabriel
 */
public class FrontPageGame extends Game {

    private String genre;

    public FrontPageGame() {}

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "FrontPageGame{" +
                super.toString() +
                "genre='" + genre + '\'' +
                '}';
    }
}
