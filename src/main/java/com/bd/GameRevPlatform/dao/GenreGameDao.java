package com.bd.GameRevPlatform.dao;

import com.bd.GameRevPlatform.model.GenreGame;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
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

    public List<Integer> getGameIds(int genre_id) {
        String sql = "SELECT game_id FROM GenreGame WHERE genre_id = ?";
        Object[] args = {genre_id};

        return Objects.requireNonNull(jdbcTemplate.queryForList(sql, args, Integer.class));
    }

    public void insertGenreGame(GenreGame genreGame){
        String insertSql = "INSERT INTO GenreGame (genre_id, game_id) VALUES (?, ?)";
        Object[] args = new Object[] {
                genreGame.getGenre_id(),
                genreGame.getGame_id()
        };

        int[] types = new int[] {Types.NUMERIC, Types.NUMERIC};

        jdbcTemplate.update(insertSql, args, types);
    }

    public void deleteGenreGame(int game_id) {
        String sql = "DELETE from GenreGame WHERE game_id = ?";
        jdbcTemplate.update(sql, game_id);
    }
}
