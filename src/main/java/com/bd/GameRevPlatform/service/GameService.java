package com.bd.GameRevPlatform.service;

import com.bd.GameRevPlatform.dao.GameDao;
import com.bd.GameRevPlatform.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Timofti Gabriel
 */

@Service
public class GameService {
    private GameDao gameDao;

    @Autowired
    public GameService(GameDao gameDao){
        this.gameDao = gameDao;
    }

    public List<Game> getAllGames() {
        List<Game> games = gameDao.getAllGames();

        // description is shortened to 80 chars
        for (Game game : games) {
            String description = game.getDescription();
            String short_description = description.substring(0, 80);
            short_description += " ...";
            game.setDescription(short_description);
        }

        return games;
    }
}
