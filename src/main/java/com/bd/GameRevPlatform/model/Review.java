package com.bd.GameRevPlatform.model;

/**
 * @author Timofti Gabriel
 */

public class Review {
    private int review_id;
    private String title;
    private String text_field;
    private int game_id;
    private int user_id;
    private Integer parent_id;

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText_field() {
        return text_field;
    }

    public void setText_field(String text_field) {
        this.text_field = text_field;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    @Override
    public String toString() {
        return "Review{" +
                "review_id=" + review_id +
                ", title='" + title + '\'' +
                ", text_field='" + text_field + '\'' +
                ", game_id=" + game_id +
                ", user_id=" + user_id +
                '}';
    }
}
