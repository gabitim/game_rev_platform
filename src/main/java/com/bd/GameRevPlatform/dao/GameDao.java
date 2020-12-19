package com.bd.GameRevPlatform.dao;

import com.bd.GameRevPlatform.model.Game;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Timofti Gabriel
 */

@Repository
public class GameDao {
    private JdbcTemplate jdbcTemplate;

    public GameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Game> getAllGames() {
        String sql = "SELECT * FROM game";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Game.class));
    }

    public void insertGame(Game game) {

    }

    public Game getGame(int game_id) {
        return null;
    }

    public void updateGame(Game game) {

    }

    public void deleteGame(int id) {

    }
}