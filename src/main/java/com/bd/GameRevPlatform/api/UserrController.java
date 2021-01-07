package com.bd.GameRevPlatform.api;

import com.bd.GameRevPlatform.model.*;
import com.bd.GameRevPlatform.service.ReviewService;
import com.bd.GameRevPlatform.service.UserrService;
import com.bd.GameRevPlatform.service.review.DisplayedReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
        Userr user = new Userr();
        model.addAttribute( "userr", user );

        return "log_in/log_in_form";
    }

    @RequestMapping(value = "/checkIfUserExists", method = RequestMethod.POST)
    public String logInUserCredentials(@ModelAttribute("userr") Userr user, RedirectAttributes redirectAttributes) throws NoSuchAlgorithmException, IOException {
        boolean userExists = userrService.checkValidLogin( user );

        if (userExists) {
            int user_id = userrService.getUserId(user.getHashedPassword(), user.getEmail());

            redirectAttributes.addAttribute("user_id", user_id);
            return "redirect:/main_page/{user_id}";
        } else
            return "log_in/unsuccessful_log_in_form";
    }

    @RequestMapping("/sign_up")
    public String signUp(Model model) {
        Userr user = new Userr();
        model.addAttribute( "userr", user );

        return "log_in/register_account_form";
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

        return "log_in/password_reset_form";
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public String resetPassword(@ModelAttribute("setter") PasswordSetter setter) throws NoSuchAlgorithmException {
        boolean passwordChanged = userrService.resetPassword( setter );

        if (passwordChanged) {
            return "redirect:/";
        } else
            return "log_in/unsuccessful_password_reset";
    }

    @RequestMapping("/user_profile/{user_id}")
    public String viewUserPage(Model model, @PathVariable(name = "user_id")int user_id) {
        List<DisplayedReview> userReviews = reviewService.getReviewsByUserId(user_id);
        List<DisplayedReview> userComments = reviewService.getCommentsByUserId(user_id);
        Userr user = userrService.getUserById(user_id);

        model.addAttribute("reviews", userReviews);
        model.addAttribute("comments", userComments);
        model.addAttribute("user", user);

        return "user_profile";
    }
}
