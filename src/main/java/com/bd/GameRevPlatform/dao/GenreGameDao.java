package com.bd.GameRevPlatform.dao;

import com.bd.GameRevPlatform.model.GenreGame;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * @author Timofti Gabriel
 */
@Repository
public class GenreGameDao {
    private JdbcTemplate jdbcTemplate;

    public GenreGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Integer> getGenreIds(int game_id) {
        String sql = "SELECT genre_id FROM GenreGame WHERE game_id = ?";
        Object[] args = {game_id};

        return Objects.requireNonNull(jdbcTemplate.queryForList(sql, args, Integer.class));
    }

    public int getGameId(int genre_id) {
        String sql = "SELECT game_id FROM GenreGame WHERE genre_id = ?";
        Object[] args = {genre_id};

        return Objects.requireNonNull(jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(GenreGame.class))).getGame_id();
    }
}
