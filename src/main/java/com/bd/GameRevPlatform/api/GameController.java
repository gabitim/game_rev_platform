package com.bd.GameRevPlatform.api;

import com.bd.GameRevPlatform.model.Genre;
import com.bd.GameRevPlatform.model.Review;
import com.bd.GameRevPlatform.model.Userr;
import com.bd.GameRevPlatform.service.GameService;
import com.bd.GameRevPlatform.service.GenreService;
import com.bd.GameRevPlatform.service.ReviewService;
import com.bd.GameRevPlatform.service.UserrService;
import com.bd.GameRevPlatform.service.game.FrontPageGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.List;

/**
 * @author Timofti Gabriel
 */

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserrService userrService;

    @RequestMapping("/main_page/{user_id}")
    public String viewHomePage(Model model, @PathVariable(name = "user_id")int user_id) throws ParseException {
        List<FrontPageGame> games = gameService.getAllGamesFrontPage();
        Userr userr = userrService.getUserById(user_id);

        model.addAttribute("games", games);
        model.addAttribute("user", userr);

        return "index";
    }

    @RequestMapping("/{user_id}/newGame")
    public String viewEnterGamePage(Model model, @PathVariable(name = "user_id")int user_id) {
        FrontPageGame game = new FrontPageGame();
        List<Genre> genres = genreService.getAllGenres();
        Userr userr = userrService.getUserById(user_id);

        model.addAttribute("game", game);
        model.addAttribute("genres", genres);
        model.addAttribute("user", userr);

        return "new_game_form";
    }

    @RequestMapping(value = "/{user_id}/saveGame", method = RequestMethod.POST)
    public String saveGame(@ModelAttribute("game") FrontPageGame game,
                           @PathVariable(name = "user_id")int user_id,
                           RedirectAttributes redirectAttributes) {
        gameService.saveGame(game);

        redirectAttributes.addAttribute("user_id", user_id);
        return "redirect:/main_page/{user_id}";
    }

    @RequestMapping("/{user_id}/edit/{game_id}")
    public ModelAndView viewEditGamePage(@PathVariable(name = "game_id")int game_id,
                                         @PathVariable(name = "user_id")int user_id) {
        ModelAndView modelAndView = new ModelAndView("edit_game_form");
        FrontPageGame frontPageGame = gameService.getGameFrontPage(game_id);
        List<Genre> genres = genreService.getAllGenres();
        Userr userr = userrService.getUserById(user_id);

        modelAndView.addObject("game", frontPageGame);
        modelAndView.addObject("genres", genres);
        modelAndView.addObject("user", userr);

        return modelAndView;
    }

    @RequestMapping(value = "/{user_id}/updateGame", method = RequestMethod.POST)
    public String updateGame(@ModelAttribute("game") FrontPageGame game,
                             @PathVariable(name = "user_id")int user_id,
                             RedirectAttributes redirectAttributes) {
        gameService.updateGame(game);

        redirectAttributes.addAttribute("user_id", user_id);
        return "redirect:/main_page/{user_id}";
    }

    @RequestMapping("/{user_id}/delete/{game_id}")
    public String deleteGame(@PathVariable(name = "game_id")int game_id,
                             @PathVariable(name = "user_id")int user_id,
                             RedirectAttributes redirectAttributes) {
        gameService.deleteGame(game_id);

        redirectAttributes.addAttribute("user_id", user_id);
        return "redirect:/main_page/{user_id}";
    }

    @RequestMapping("/{user_id}/game/{game_id}")
    public ModelAndView viewGame(@PathVariable(name = "game_id")int game_id,
                                 @PathVariable(name = "user_id")int user_id) {
        ModelAndView modelAndView = new ModelAndView("game_form");
        List<Review> reviews = reviewService.getReviewsByGameId(game_id);
        FrontPageGame frontPageGame = gameService.getGameFrontPage(game_id); //for displaying game title
        Userr userr = userrService.getUserById(user_id);

        modelAndView.addObject("reviews", reviews);
        modelAndView.addObject("game", frontPageGame);
        modelAndView.addObject("user", userr);

        return modelAndView;
    }

}
