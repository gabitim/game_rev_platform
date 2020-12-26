package com.bd.GameRevPlatform.service;

import com.bd.GameRevPlatform.dao.GenreDao;
import com.bd.GameRevPlatform.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Timofti Gabriel
 */

@Service
public class GenreService {
    private GenreDao genreDao;

    @Autowired
    public GenreService(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public Genre getGenre(int genre_id){
        return genreDao.getGenre(genre_id);
    }

    public List<Genre> getAllGenres(){
        return genreDao.getAllGenres();
    }

    public int getGenreId(String genreDescription){
        return genreDao.getGenreId(genreDescription);
    }

    public String getGenreDescription(List<Integer> genre_ids){
        StringBuilder descriptions = new StringBuilder();

        for (Integer genre_id: genre_ids) {
            descriptions.append(this.getGenre(genre_id).getDescription()).append(" ");
        }

        return descriptions.toString();
    }


}
