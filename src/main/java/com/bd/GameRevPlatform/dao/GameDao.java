package com.bd.GameRevPlatform.dao;

import com.bd.GameRevPlatform.model.Game;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
        // when inserting a new game we must know the autoincrement id.

        String insertSql = "INSERT INTO Game (title, description, release_date, rating, news_column) " +
                "VALUES (?, ?, ?, ?, ?)";
        int game_id = 0;

        // use KeyHolder and PreparedStatement to get the autoincrement id from oracle db
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(insertSql, new String[] { "game_id" });
            ps.setString(1, game.getTitle());
            ps.setString(2, game.getDescription());
            ps.setDate(3,  new java.sql.Date(game.getRelease_date().getTime()));
            ps.setDouble(4, game.getRating());
            ps.setString(5, game.getNews_column());
            return ps;
        }, keyHolder);
        game_id = keyHolder.getKey().intValue();

        game.setGame_id(game_id);
    }

    public Game getGame(int game_id) {
        String sql = "SELECT * FROM game WHERE game_id = ?";
        Object[] args = {game_id};

        return jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Game.class));
    }

    public void updateGame(Game game) {
        String sql = "UPDATE game SET title=:title, description=:description, release_date=:release_date, " +
                "rating=:rating WHERE game_id=:game_id";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(game);
        NamedParameterJdbcTemplate temp = new NamedParameterJdbcTemplate(jdbcTemplate);

        temp.update(sql, param);
    }

    public void deleteGame(int id) {

    }
}
