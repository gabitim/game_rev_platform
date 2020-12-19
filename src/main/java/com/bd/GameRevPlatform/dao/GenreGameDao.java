package com.bd.GameRevPlatform.dao;

import com.bd.GameRevPlatform.model.GenreGame;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Objects;

/**
 * @author Timofti Gabriel
 */
public class GenreGameDao {
    private JdbcTemplate jdbcTemplate;

    public GenreGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getGenreId(int game_id) {
        String sql = "SELECT genre_id FROM GenreGame WHERE game_id = ?";
        Object[] args = {game_id};


        return Objects.requireNonNull(jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(GenreGame.class))).getGenre_id();
    }

    public int getGameId(int genre_id) {
        String sql = "SELECT game_id FROM GenreGame WHERE genre_id = ?";
        Object[] args = {genre_id};


        return Objects.requireNonNull(jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(GenreGame.class))).getGame_id();
    }
}
