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
    public String logInUserCredentials(@ModelAttribute("userr") Userr userr) throws NoSuchAlgorithmException {
        boolean userExists = userrService.checkValidLogin( userr );

        if (userExists) {
            // ! populate the userr object with the rest of the fields from the db (apart from email and password)
            return "redirect:/main_page";
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
        return "redirect:/main_page";
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

    @RequestMapping("/user_profile")
    public String viewUserPage(Model model) {
        //User user = userrService.getCurrentuser();
        List<Review> userReviews = reviewService.getReviewsByUserId(1);
        List<Review> userComments = reviewService.getCommentsByUserId(1);
        Userr user = new Userr();
        user.setFirstName("Gabi");
        user.setLastName("Tim");
        user.setUserr_id(1);

        model.addAttribute("reviews", userReviews);
        model.addAttribute("comments", userComments);
        model.addAttribute("user", user);

        return "user_profile";
    }
}
