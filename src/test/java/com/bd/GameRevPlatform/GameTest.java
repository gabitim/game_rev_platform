package com.bd.GameRevPlatform;

import com.bd.GameRevPlatform.dao.GameDao;
import com.bd.GameRevPlatform.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Timofti Gabriel
 */

@SpringBootTest
public class GameTest {
    private GameDao gameDao;

    @BeforeEach
    void setUp() throws Exception{
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("game_rev_db");
        dataSource.setPassword("bunica");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        gameDao = new GameDao(new JdbcTemplate(dataSource));
    }

    @Test
    void testGetAllGames() {
        List<Game> games  = gameDao.getAllGames();

        assertFalse(games.isEmpty());
    }

    @Test
    void testGetGame() {
        int game_id = 27;
        Game game = gameDao.getGame(game_id);

        assertNotNull(game);
    }

    @Test
    void testInsertGame() {
        Game game = new Game( "Fifa 21", "ok game", new Date(2020, Calendar.DECEMBER, 18), 4.9);
        gameDao.insertGame(game);
    }

    @Test
    void testUpdateGame() {
        Game game = new Game();
        game.setGame_id(32);
        game.setTitle("asgasgsd");
        game.setDescription("ajbjfnadsjknsdalgnasdg");
        game.setRating(2.2);
        game.setRelease_date(new Date(2018, Calendar.APRIL, 14));

        gameDao.updateGame(game);
    }

    @Test
    void testDeleteGame() {
        fail();
    }
}
