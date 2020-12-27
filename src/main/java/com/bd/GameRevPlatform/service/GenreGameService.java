package com.bd.GameRevPlatform.service;

import com.bd.GameRevPlatform.dao.GenreGameDao;
import com.bd.GameRevPlatform.model.GenreGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Timofti Gabriel
 */

@Service
public class GenreGameService {
    private GenreGameDao genreGameDao;

    @Autowired
    private GenreService genreService;

    @Autowired
    public GenreGameService(GenreGameDao genreGameDao) {
        this.genreGameDao = genreGameDao;
    }

    public List<Integer> getGenreId(int game_id){
        return genreGameDao.getGenreIds(game_id);
    }

    public List<Integer> getGameId(int genre_id){
        return genreGameDao.getGameIds(genre_id);
    }

    public void saveGenreGame(int game_id, String genres){

        String[] genresList = genres.split(",");

        for(String genre : genresList){
            GenreGame newGenreGame = new GenreGame();
            newGenreGame.setGenre_id(genreService.getGenreId(genre));
            newGenreGame.setGame_id(game_id);

            genreGameDao.insertGenreGame(newGenreGame);
        }
    }

    public void updateGenreGame(int game_id, String genres) {
        genreGameDao.deleteGenreGame(game_id);
        this.saveGenreGame(game_id, genres);
    }
}
