package com.bd.GameRevPlatform.dao;

import com.bd.GameRevPlatform.model.Review;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Timofti Gabriel
 */

@Repository
public class ReviewDao {
    private JdbcTemplate jdbcTemplate;

    public ReviewDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Review> getAllReviews() {
        String sql = "SELECT * FROM Review";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Review.class));
    }

    public Review getReview(int review_id){
        String sql = "SELECT * FROM Review WHERE review_id = ?";
        Object[] args = {review_id};

        return jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Review.class));
    }

    public void deleteReview(int game_id) {
        String sql = "DELETE from Review WHERE game_id = ?";
        jdbcTemplate.update(sql, game_id);
    }
}
