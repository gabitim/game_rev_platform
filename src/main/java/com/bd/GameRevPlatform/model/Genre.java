package com.bd.GameRevPlatform.model;

/**
 * @author Timofti Gabriel
 */
public class Genre {
    public int genre_id;
    public String category_label;
    public String description;

    public int getGenre_id() {
        return genre_id;
    }

    public String getCategory_label() {
        return category_label;
    }

    public String getDescription() {
        return description;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public void setCategory_label(String category_label) {
        this.category_label = category_label;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genre_id=" + genre_id +
                ", category_label='" + category_label + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
