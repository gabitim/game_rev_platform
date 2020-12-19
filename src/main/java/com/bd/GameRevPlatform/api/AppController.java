package com.bd.GameRevPlatform.api;

import com.bd.GameRevPlatform.dao.GameDao;
import com.bd.GameRevPlatform.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Timofti Gabriel
 */

@Controller
public class AppController {

    @Autowired
    private GameDao gameDao;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Game> games = gameDao.getAllGames();

        for (Game game : games) {
            String description = game.getDescription();
            String short_description = description.substring(0, 80);
            short_description += " ...";
            game.setDescription(short_description);
        }

        model.addAttribute("games", games);

        return "index";
    }
}
