package com.bd.GameRevPlatform.service;

import com.bd.GameRevPlatform.dao.GenreGameDao;
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
    public GenreGameService(GenreGameDao genreGameDao) {
        this.genreGameDao = genreGameDao;
    }

    public List<Integer> getGenreId(int game_id){
        return genreGameDao.getGenreIds(game_id);
    }

    public int getGameId(int genre_id){
        return genreGameDao.getGameId(genre_id);
    }
}
