package com.bd.GameRevPlatform.model;

/**
 * @author Timofti Gabriel
 */
public class GenreGame {
    private int genre_id;
    private int game_id;

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    @Override
    public String toString() {
        return "GenreGame{" +
                "genre_id=" + genre_id +
                ", game_id=" + game_id +
                '}';
    }
}
