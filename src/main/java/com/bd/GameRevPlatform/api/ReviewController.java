package com.bd.GameRevPlatform.api;

import com.bd.GameRevPlatform.model.Review;
import com.bd.GameRevPlatform.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Timofti Gabriel
 */

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @RequestMapping("/game/{game_id}/newReview")
    public ModelAndView viewEnterReviewPage(@PathVariable(name = "game_id")int game_id) {
        ModelAndView modelAndView = new ModelAndView("new_review_form");
        Review review = new Review();
        review.setGame_id(game_id);

        modelAndView.addObject("review", review);

        return modelAndView;
    }


    @RequestMapping(value = "/saveReview", method = RequestMethod.POST)
    public String saveReview(@ModelAttribute("review") Review review, RedirectAttributes redirectAttributes) {
        reviewService.saveReview(review);

        redirectAttributes.addAttribute("game_id", review.getGame_id());
        return "redirect:/game/{game_id}";
    }
}
