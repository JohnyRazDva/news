package by.academy.news.controller;

import by.academy.news.entity.user.User;
import by.academy.news.service.ServiceException;
import by.academy.news.service.news.NewsService;
import by.academy.news.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author Eugene Petrov
 */
@Controller
@RequestMapping(value = "/guest")
public class GuestController {
    @Autowired
    NewsService newsService;

    @Autowired
    UserService userService;

    @GetMapping("/loginFail")
    public String loginFail(Model model) {
        try {
            model.addAttribute("newsList", newsService.getLatestNews());
        } catch (ServiceException e) {
            return "error";
        }
        model.addAttribute("loginFail", "wrong login or password");
        return "basePage";
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("registration", "true");
        User user = new User();
        model.addAttribute("user", user);
        return "basePage";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registration", "true");
            model.addAttribute("errors", bindingResult);
        } else {
            try {
                userService.save(user);
            } catch (ServiceException e) {
                return "error";
            }
            model.addAttribute("registration", "done");
        }
        return "basePage";
    }
}
