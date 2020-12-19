package com.bd.GameRevPlatform.service;

import com.bd.GameRevPlatform.dao.GameDao;
import com.bd.GameRevPlatform.model.Game;
import com.bd.GameRevPlatform.service.game.FrontPageGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Timofti Gabriel
 */

@Service
public class GameService {
    private GameDao gameDao;

    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreGameService genreGameService;

    @Autowired
    public GameService(GameDao gameDao){
        this.gameDao = gameDao;
    }

    public List<FrontPageGame> getAllGamesFrontPage() {
        List<Game> games = gameDao.getAllGames();

        // description is shortened to 80 chars
        for (Game game : games) {
            String description = game.getDescription();
            String short_description = description.substring(0, 80);
            short_description += " ...";
            game.setDescription(short_description);
        }

        ArrayList<FrontPageGame> frontPageGames = new ArrayList();

        //add genre for display
        for (Game game: games){
            FrontPageGame frontPageGame = new FrontPageGame();

            frontPageGame.setTitle(game.getTitle());
            frontPageGame.setDescription(game.getDescription());
            frontPageGame.setRelease_date(game.getRelease_date());
            frontPageGame.setRating(game.getRating());
            frontPageGame.setGenre(
                    genreService.getGenreDescription(genreGameService.getGenreId(game.getGame_id()))
            );

            frontPageGames.add(frontPageGame);
        }

        return frontPageGames;
    }
}
