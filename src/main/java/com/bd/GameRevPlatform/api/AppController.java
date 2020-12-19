package com.bd.GameRevPlatform.api;

import com.bd.GameRevPlatform.model.Game;
import com.bd.GameRevPlatform.service.GameService;
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
    private GameService gameService;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Game> games = gameService.getAllGames();

        model.addAttribute("games", games);

        return "index";
    }
}
