package com.bd.GameRevPlatform;

import com.bd.GameRevPlatform.dao.ReviewDao;
import com.bd.GameRevPlatform.model.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Timofti Gabriel
 */

@SpringBootTest
public class ReviewTest {
    private ReviewDao reviewDao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("game_rev_db");
        dataSource.setPassword("bunica");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        reviewDao = new ReviewDao(new JdbcTemplate(dataSource));
    }

    @Test
    void testGetAllReviews() {
        List<Review> reviews = reviewDao.getAllReviews();

        assertFalse(reviews.isEmpty());
    }

    @Test
    void testGetReview() {
        int review_id = 2;
        Review review = reviewDao.getReview(review_id);

        assertNotNull(review);
    }

    @Test
    void testGetReviewByGameId() {
        int game_id = 3;
        List<Review> reviews = reviewDao.getReviewsByGameId(game_id);

        assertFalse(reviews.isEmpty());
    }
}
