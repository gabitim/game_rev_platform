package com.bd.GameRevPlatform.service;

import com.bd.GameRevPlatform.dao.ReviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public void deleteReview(int game_id){
        reviewDao.deleteReview(game_id);
    }
}
