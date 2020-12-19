package com.bd.GameRevPlatform;

import com.bd.GameRevPlatform.dao.GameDao;
import com.bd.GameRevPlatform.model.Game;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author Timofti Gabriel
 */

@SpringBootTest
public class GameTest {
    private GameDao gameDao;

    public GameTest(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Test
    void testGetAllGames() {
        List<Game> games  = gameDao.getAllGames();

        assertFalse(games.isEmpty());
    }

    @Test
    void testGetGame() {
    }

    @Test
    void testInsertGame() {
    }

    @Test
    void testUpdateGame() {
    }

    @Test
    void testDeleteGame() {
    }
}
