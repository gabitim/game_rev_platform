package com.bd.GameRevPlatform.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Timofti Gabriel
 */

@Repository
public class ReviewDao {
    private JdbcTemplate jdbcTemplate;

    public ReviewDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void deleteReview(int game_id) {
        String sql = "DELETE from Review WHERE game_id = ?";
        jdbcTemplate.update(sql, game_id);
    }
}
