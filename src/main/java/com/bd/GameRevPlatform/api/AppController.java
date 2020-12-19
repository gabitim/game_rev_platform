package com.bd.GameRevPlatform.api;

import com.bd.GameRevPlatform.model.Game;
import com.bd.GameRevPlatform.service.GameService;
import com.bd.GameRevPlatform.service.game.FrontPageGame;
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
        List<FrontPageGame> games = gameService.getAllGamesFrontPage();

        model.addAttribute("games", games);

        return "index";
    }

    @RequestMapping("/newGame")
    public String viewEnterGamePage(Model model) {
        FrontPageGame game = new FrontPageGame();
        model.addAttribute("game", game);

        return "new_game_form";
    }
}
