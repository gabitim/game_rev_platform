package com.bd.GameRevPlatform.service;

import com.bd.GameRevPlatform.dao.ReviewDao;
import com.bd.GameRevPlatform.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Timofti Gabriel
 */

@Service
public class ReviewService {
    private ReviewDao reviewDao;

    @Autowired
    public ReviewService(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    public Review getReview(int review_id){
        return reviewDao.getReview(review_id);
    }

    public List<Review> getReviewsByGameId(int game_id) {
        return reviewDao.getReviewsByGameId(game_id);
    }

    public void deleteReviewsByGameId(int game_id){
        reviewDao.deleteReviewsByGameId(game_id);
    }

    public void deleteReview(int review_id) {
        reviewDao.deleteReview(review_id);
    }

    public void saveReview(Review review){
        // until we implement User feature all reviews have dummy user_id = 1
        review.setUser_id(1);
        reviewDao.insertReview(review);
    }

    public void updateReview(Review review) {
        reviewDao.updateReview(review);
    }
}
