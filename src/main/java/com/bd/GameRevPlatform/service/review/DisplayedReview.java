package com.bd.GameRevPlatform.service.review;

import com.bd.GameRevPlatform.model.Review;

/**
 * @author Timofti Gabriel
 */
public class DisplayedReview extends Review {
    private String author;

    public DisplayedReview() {}

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "DisplayedReview{" +
                super.toString() +
                "author='" + author + '\'' +
                '}';
    }
}
