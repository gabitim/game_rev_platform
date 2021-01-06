package com.bd.GameRevPlatform.api;

import com.bd.GameRevPlatform.model.*;
import com.bd.GameRevPlatform.service.GameService;
import com.bd.GameRevPlatform.service.GenreService;
import com.bd.GameRevPlatform.service.ReviewService;
import com.bd.GameRevPlatform.service.UserrService;
import com.bd.GameRevPlatform.service.game.FrontPageGame;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

/**
 * @author Timofti Gabriel
 */

@Controller
public class UserrController {

    @Autowired
    private UserrService userrService;

    @Autowired
    private ReviewService reviewService;

    @RequestMapping("/")
    public String logIn(Model model) {
        Userr userr1 = new Userr();
        model.addAttribute( "userr", userr1 );

        return "log_in_form";
    }

    @RequestMapping(value = "/checkIfUserExists", method = RequestMethod.POST)
    public String logInUserCredentials(@ModelAttribute("userr") Userr userr, RedirectAttributes redirectAttributes) throws NoSuchAlgorithmException {
        boolean userExists = userrService.checkValidLogin( userr );

        if (userExists) {
            int user_id = userrService.getUserId(userr.getHashedPassword(), userr.getEmail());

            redirectAttributes.addAttribute("user_id", user_id);
            return "redirect:/main_page/{user_id}";
        } else
            return "unsuccessful_log_in_form";
    }

    @RequestMapping("/sign_up")
    public String signUp(Model model) {
        Userr userr1 = new Userr();
        model.addAttribute( "userr", userr1 );

        return "register_account_form";
    }

    @RequestMapping(value = "/saveUserr", method = RequestMethod.POST)
    public String saveUserr(@ModelAttribute("userr") Userr userr) {
        try {
            userrService.saveUserr( userr );
        } catch ( NoSuchAlgorithmException e ) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @RequestMapping("/guest_log_in")
    public String logInGuest() {
        return "redirect:/main_page/0";
    }


    @RequestMapping("/reset_password")
    public String resetPasswordForm(Model model) {
        PasswordSetter setter = new PasswordSetter();
        model.addAttribute( "setter", setter );

        return "password_reset_form";
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public String resetPassword(@ModelAttribute("setter") PasswordSetter setter) throws NoSuchAlgorithmException {
        boolean passwordChanged = userrService.resetPassword( setter );

        if (passwordChanged) {
            return "redirect:/";
        } else
            return "unsuccessful_password_reset";
    }

    @RequestMapping("/user_profile/{user_id}")
    public String viewUserPage(Model model, @PathVariable(name = "user_id")int user_id) {
        List<Review> userReviews = reviewService.getReviewsByUserId(user_id);
        List<Review> userComments = reviewService.getCommentsByUserId(user_id);
        Userr user = userrService.getUserById(user_id);

        model.addAttribute("reviews", userReviews);
        model.addAttribute("comments", userComments);
        model.addAttribute("user", user);

        return "user_profile";
    }
}
