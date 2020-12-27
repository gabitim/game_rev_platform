package com.bd.GameRevPlatform.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Timofti Gabriel
 */

@Repository
public class GameSessionDao {
    private JdbcTemplate jdbcTemplate;

    public GameSessionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void deleteGameSession(int game_id) {
        String sql = "DELETE from GameSession WHERE game_id = ?";
        jdbcTemplate.update(sql, game_id);
    }
}
