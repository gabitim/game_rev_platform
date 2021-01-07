package com.bd.GameRevPlatform.service;

import com.bd.GameRevPlatform.dao.ReviewDao;
import com.bd.GameRevPlatform.model.Review;
import com.bd.GameRevPlatform.service.review.DisplayedReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private UserrService userrService;

    public Review getReview(int review_id){
        return reviewDao.getReview(review_id);
    }

    public List<DisplayedReview> getReviewsByGameId(int game_id) {
        List<Review> reviews = reviewDao.getReviewsByGameId(game_id);

        return this.convertReviewToDisplayedReview(reviews);
    }

    public List<DisplayedReview> getReviewsByUserId(int user_id) {
        List<Review> reviews = reviewDao.getReviewsByUserId(userrService.decrypt(user_id));

        return this.convertReviewToDisplayedReview(reviews);
    }

    public List<DisplayedReview> getCommentsByUserId(int user_id) {
        List<Review> comments = reviewDao.getCommentsByUserId(userrService.decrypt(user_id));

        return this.convertReviewToDisplayedReview(comments);
    }

    public List<DisplayedReview> getComments(int parent_id){
        List<Review> comments = reviewDao.getCommentsByParentId(parent_id);

        return this.convertReviewToDisplayedReview(comments);
    }

    public void deleteReviewsByGameId(int game_id){
        reviewDao.deleteReviewsByGameId(game_id);
    }

    public void deleteCommentsByParentId(int parent_id) {
        reviewDao.deleteCommentsByParentId(parent_id);
    }

    public void deleteReview(int review_id) {
        this.deleteCommentsByParentId(review_id);
        reviewDao.deleteReview(review_id);
    }

    public void deleteComment(int comment_id) {
        reviewDao.deleteReview(comment_id);
    }

    public void saveReview(Review review, int user_id){
        review.setUser_id(userrService.decrypt(user_id));
        review.setPosted_date(new Date(System.currentTimeMillis()));
        reviewDao.insertReview(review);
    }

    public void saveComment(Review review, int user_id) {
        review.setUser_id(userrService.decrypt(user_id));
        review.setPosted_date(new Date(System.currentTimeMillis()));
        reviewDao.insertComment(review);
    }

    public void updateReview(Review review, int user_id) {
        review.setUser_id(userrService.decrypt(user_id));
        review.setPosted_date(new Date(System.currentTimeMillis()));
        reviewDao.updateReview(review);
    }

    public List<DisplayedReview> convertReviewToDisplayedReview(List<Review> reviews){
        ArrayList<DisplayedReview> displayedReviews = new ArrayList<>();

        for (Review review: reviews ) {
            DisplayedReview displayedReview = new DisplayedReview();
            displayedReview.setReview_id(review.getReview_id());
            displayedReview.setTitle(review.getTitle());
            displayedReview.setText_field(review.getText_field());
            displayedReview.setGame_id(review.getGame_id());
            displayedReview.setUser_id(review.getUser_id());
            displayedReview.setParent_id(review.getParent_id());
            displayedReview.setPosted_date(review.getPosted_date());
            displayedReview.setAuthor(userrService.getUserNameById(displayedReview.getUser_id()));

            displayedReviews.add(displayedReview);
        }


        return displayedReviews;
    }
}
