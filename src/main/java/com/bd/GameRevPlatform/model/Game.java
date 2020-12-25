package com.bd.GameRevPlatform.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Timofti Gabriel
 */
public class Game {
    private int game_id;
    private String title;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd") // for Thymeleaf
    private Date release_date;
    private double rating;
    private String news_column;

    public Game(){}

    public Game(String title, String description, Date release_date, double rating) {
        this.title = title;
        this.description = description;
        this.release_date = release_date;
        this.rating = rating;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date){
        this.release_date = release_date;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getNews_column() {
        return news_column;
    }

    public void setNews_column(String news_column) {
        this.news_column = news_column;
    }

    @Override
    public String toString() {
        return
                "game_id=" + game_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", release_date=" + release_date +
                ", rating=" + rating +
                ", news_column='" + news_column + '\'' +
                '}';
    }
}
