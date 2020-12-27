package com.bd.GameRevPlatform.service;

import com.bd.GameRevPlatform.dao.GameDao;
import com.bd.GameRevPlatform.model.Game;
import com.bd.GameRevPlatform.service.game.FrontPageGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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

    public List<FrontPageGame> getAllGamesFrontPage() throws ParseException {
        List<Game> games = gameDao.getAllGames();

        // description is shortened to 80 chars
        for (Game game : games) {
            String description = game.getDescription();
            if (description.length() > 80) {
                String short_description = description.substring(0, 80);
                short_description += " ...";
                game.setDescription(short_description);
            }
        }

        ArrayList<FrontPageGame> frontPageGames = new ArrayList<>();

        //add genre for display
        for (Game game: games){
            FrontPageGame frontPageGame = new FrontPageGame();
            frontPageGame.setGame_id(game.getGame_id());
            frontPageGame.setTitle(game.getTitle());
            frontPageGame.setDescription(game.getDescription());
            frontPageGame.setRelease_date_string(game.getRelease_date());
            frontPageGame.setRating(game.getRating());
            frontPageGame.setGenre(
                    genreService.getGenreDescription(genreGameService.getGenreId(game.getGame_id()))
            );

            frontPageGames.add(frontPageGame);
        }

        return frontPageGames;
    }

    public void saveGame(FrontPageGame frontPageGame){
        gameDao.insertGame(frontPageGame);
        genreGameService.saveGenreGame(frontPageGame.getGame_id(), frontPageGame.getGenre());
    }

    public FrontPageGame getGameFrontPage(int game_id){
        Game game =  gameDao.getGame(game_id);

        FrontPageGame frontPageGame = new FrontPageGame();
        frontPageGame.setGame_id(game.getGame_id());
        frontPageGame.setTitle(game.getTitle());
        frontPageGame.setDescription(game.getDescription());
        frontPageGame.setRelease_date_string(game.getRelease_date());
        frontPageGame.setRating(game.getRating());
        frontPageGame.setGenre(genreService.getGenreDescription(genreGameService.getGenreId(game.getGame_id())));

        return frontPageGame;
    }

    public void updateGame(FrontPageGame frontPageGame){
        gameDao.updateGame(frontPageGame);
        genreGameService.updateGenreGame(frontPageGame.getGame_id(), frontPageGame.getGenre());
    }
}
