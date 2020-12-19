package com.bd.GameRevPlatform.dao;

import com.bd.GameRevPlatform.model.Game;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
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
        String insertSql = "INSERT INTO Game (game_id, title, description, release_date, rating, news_column) VALUES (?, ?, ?, ?, ?, ?)";
        Object[] args = new Object[] {
                game.getGame_id(),
                game.getTitle(),
                game.getDescription(),
                game.getRelease_date(),
                game.getRating(),
                game.getNews_column()
        };

        int[] types = new int[] {Types.NULL, Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.NUMERIC, Types.VARCHAR};

        jdbcTemplate.update(insertSql, args, types);
    }

    public Game getGame(int game_id) {
        return null;
    }

    public void updateGame(Game game) {

    }

    public void deleteGame(int id) {

    }
}
