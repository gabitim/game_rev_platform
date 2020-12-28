package com.bd.GameRevPlatform.dao;

import com.bd.GameRevPlatform.model.Review;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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

    public List<Review> getReviewsByGameId(int game_id){
        String sql = "SELECT * FROM Review WHERE game_id = ?";
        Object[] args = {game_id};

        return jdbcTemplate.query(sql, args, BeanPropertyRowMapper.newInstance(Review.class));
    }

    public void insertReview(Review review) {
        String insertSql = "INSERT INTO Review (title, text_field, game_id, user_id) " +
                "VALUES (?, ?, ?, ?)";
        int review_id = 0;

        // use KeyHolder and PreparedStatement to get the autoincrement id from oracle db
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(insertSql, new String[] { "review_id" });
            ps.setString(1, review.getTitle());
            ps.setString(2, review.getText_field());
            ps.setInt(3, review.getGame_id());
            ps.setInt(4, review.getUser_id());
            return ps;
        }, keyHolder);
        review_id = keyHolder.getKey().intValue();

        review.setReview_id(review_id);
    }

    public void deleteReview(int game_id) {
        String sql = "DELETE from Review WHERE game_id = ?";
        jdbcTemplate.update(sql, game_id);
    }
}
