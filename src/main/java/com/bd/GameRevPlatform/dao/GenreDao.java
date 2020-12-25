package com.bd.GameRevPlatform.dao;

import com.bd.GameRevPlatform.model.Game;
import com.bd.GameRevPlatform.model.Genre;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * @author Timofti Gabriel
 */

@Repository
public class GenreDao {
    private JdbcTemplate jdbcTemplate;

    public GenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Genre> getAllGenres() {
        String sql = "SELECT * FROM genre";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Genre.class));
    }

    public void insertGenre(Genre genre) {

    }

    public Genre getGenre(int genre_id) {
        String sql = "SELECT * FROM genre WHERE genre_id = ?";
        Object[] args = {genre_id};

        return jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Genre.class));
    }

    public Integer getGenreId(String genreDescription) {
        String sql = "SELECT genre_id FROM Genre WHERE description = ?";
        Object[] args = {genreDescription};

        return Objects.requireNonNull(jdbcTemplate.queryForObject(sql, args, Integer.class));
    }

    public void updateGame(Game game) {

    }

    public void deleteGame(int id) {

    }
}
