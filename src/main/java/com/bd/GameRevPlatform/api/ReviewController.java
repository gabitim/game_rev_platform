package com.bd.GameRevPlatform.api;

import com.bd.GameRevPlatform.model.Review;
import com.bd.GameRevPlatform.model.Userr;
import com.bd.GameRevPlatform.service.GameService;
import com.bd.GameRevPlatform.service.ReviewService;
import com.bd.GameRevPlatform.service.UserrService;
import com.bd.GameRevPlatform.service.game.FrontPageGame;
import com.bd.GameRevPlatform.service.review.DisplayedReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Timofti Gabriel
 */

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private GameService gameService;

    @Autowired
    private UserrService userrService;


    @RequestMapping("/{user_id}/game/{game_id}/newReview")
    public ModelAndView viewEnterReviewPage(@PathVariable(name = "game_id")int game_id,
                                            @PathVariable(name = "user_id")int user_id) {
        ModelAndView modelAndView = new ModelAndView("new_review_form");
        Review review = new Review();
        review.setGame_id(game_id);
        FrontPageGame game = gameService.getGameFrontPage(game_id); //for displaying game title
        Userr user = userrService.getUserById(user_id);

        modelAndView.addObject("review", review);
        modelAndView.addObject("game", game);
        modelAndView.addObject("user", user);

        return modelAndView;
    }


    @RequestMapping(value = "/{user_id}/saveReview", method = RequestMethod.POST)
    public String saveReview(@ModelAttribute("review") Review review,
                             @PathVariable(name = "user_id")int user_id,
                             RedirectAttributes redirectAttributes) {
        reviewService.saveReview(review, user_id);

        redirectAttributes.addAttribute("game_id", review.getGame_id());
        redirectAttributes.addAttribute("user_id", user_id);
        return "redirect:/{user_id}/game/{game_id}";
    }

    @RequestMapping("/{user_id}/game/{game_id}/edit/{review_id}")
    public ModelAndView viewEditReviewPage(@PathVariable(name = "review_id")int review_id,
                                           @PathVariable(name = "user_id")int user_id) {
        ModelAndView modelAndView = new ModelAndView("edit_review_form");
        Review review = reviewService.getReview(review_id);
        FrontPageGame game = gameService.getGameFrontPage(review.getGame_id()); //for displaying game title
        Userr user = userrService.getUserById(user_id);

        modelAndView.addObject("review", review);
        modelAndView.addObject("game", game);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/{user_id}/updateReview", method = RequestMethod.POST)
    public String updateReview(@ModelAttribute("review") Review review,
                               @PathVariable(name = "user_id")int user_id,
                               RedirectAttributes redirectAttributes) {
        reviewService.updateReview(review, user_id);

        redirectAttributes.addAttribute("game_id", review.getGame_id());
        redirectAttributes.addAttribute("user_id", user_id);
        return "redirect:/{user_id}/game/{game_id}";
    }

    @RequestMapping("/{user_id}/game/{game_id}/delete/{review_id}")
    public String deleteReview(@PathVariable(name="game_id")int game_id,
                               @PathVariable(name = "review_id")int review_id,
                               @PathVariable(name = "user_id")int user_id,
                               RedirectAttributes redirectAttributes) throws SQLException {
        reviewService.deleteReview(review_id);

        redirectAttributes.addAttribute("game_id", game_id);
        redirectAttributes.addAttribute("user_id", user_id);
        return "redirect:/{user_id}/game/{game_id}";
    }

    @RequestMapping("/{user_id}/game/{game_id}/review/{parent_id}")
    public ModelAndView viewReview(@PathVariable(name="game_id")int game_id,
                                   @PathVariable(name="parent_id")int parent_id,
                                   @PathVariable(name = "user_id")int user_id){
        ModelAndView modelAndView = new ModelAndView("review_form");
        List<DisplayedReview> comments = reviewService.getComments(parent_id);
        FrontPageGame frontPageGame = gameService.getGameFrontPage(game_id); //for displaying game title
        Review review = reviewService.getReview(parent_id); // for displaying review title
        Userr user = userrService.getUserById(user_id);

        modelAndView.addObject("comments", comments);
        modelAndView.addObject("game", frontPageGame);
        modelAndView.addObject("review", review);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping("/{user_id}/game/{game_id}/review/{parent_id}/newComment")
    public ModelAndView viewEnterCommentPage(@PathVariable(name = "game_id")int game_id,
                                             @PathVariable(name="parent_id")int parent_id,
                                             @PathVariable(name="user_id")int user_id) {
        ModelAndView modelAndView = new ModelAndView("new_comment_form");
        Review comment = new Review();
        comment.setGame_id(game_id);
        comment.setParent_id(parent_id);
        FrontPageGame game = gameService.getGameFrontPage(game_id); //for displaying game title
        Review review = reviewService.getReview(parent_id); // for displaying review title
        Userr user = userrService.getUserById(user_id);

        modelAndView.addObject("comment", comment);
        modelAndView.addObject("review", review);
        modelAndView.addObject("game", game);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/{user_id}/saveComment", method = RequestMethod.POST)
    public String saveComment(@ModelAttribute("comment") Review comment,
                              @PathVariable(name="user_id")int user_id,
                              RedirectAttributes redirectAttributes) {
        reviewService.saveComment(comment, user_id);

        redirectAttributes.addAttribute("game_id", comment.getGame_id());
        redirectAttributes.addAttribute("parent_id", comment.getParent_id());
        redirectAttributes.addAttribute("user_id", user_id);
        return "redirect:/{user_id}/game/{game_id}/review/{parent_id}";
    }

    @RequestMapping("/{user_id}/game/{game_id}/review/{review_id}/edit/{comment_id}")
    public ModelAndView viewEditCommentPage(@PathVariable(name = "comment_id")int comment_id,
                                            @PathVariable(name = "user_id")int user_id) {
        ModelAndView modelAndView = new ModelAndView("edit_comment_form");
        Review comment = reviewService.getReview(comment_id);
        FrontPageGame game = gameService.getGameFrontPage(comment.getGame_id()); //for displaying game title
        Review review = reviewService.getReview(comment.getParent_id()); // for displaying review title
        Userr user = userrService.getUserById(user_id);

        modelAndView.addObject("comment", comment);
        modelAndView.addObject("review", review);
        modelAndView.addObject("game", game);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/{user_id}/updateComment", method = RequestMethod.POST)
    public String updateComment(@ModelAttribute("comment") Review comment,
                                @PathVariable(name = "user_id")int user_id,
                                RedirectAttributes redirectAttributes) {
        reviewService.updateReview(comment, user_id);

        redirectAttributes.addAttribute("game_id", comment.getGame_id());
        redirectAttributes.addAttribute("parent_id", comment.getParent_id());
        redirectAttributes.addAttribute("user_id", user_id);
        return "redirect:/{user_id}/game/{game_id}/review/{parent_id}";
    }

    @RequestMapping("/{user_id}/game/{game_id}/review/{review_id}/delete/{comment_id}")
    public String deleteComment(@PathVariable(name="game_id")int game_id,
                                @PathVariable(name = "review_id")int review_id,
                                @PathVariable(name = "comment_id")int comment_id,
                                @PathVariable(name = "user_id")int user_id,
                               RedirectAttributes redirectAttributes) throws SQLException {
        reviewService.deleteComment(comment_id);

        redirectAttributes.addAttribute("game_id", game_id);
        redirectAttributes.addAttribute("review_id", review_id);
        redirectAttributes.addAttribute("user_id", user_id);
        return "redirect:/{user_id}/game/{game_id}/review/{review_id}";
    }
}
