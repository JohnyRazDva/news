package by.academy.news.controller;

import by.academy.news.entity.news.News;
import by.academy.news.entity.user.User;
import by.academy.news.service.ServiceException;
import by.academy.news.service.news.NewsService;
import by.academy.news.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Eugene Petrov
 */
@Controller()
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @GetMapping("/users-menu")
    public String usersMenu(Model model) {
        try {
            model.addAttribute("users", userService.getAll());
            model.addAttribute("content", "usersMenu");
        } catch (ServiceException e) {
            return "error";
        }
        return "basePage";
    }

    @PostMapping("/usersList")
    public String usersList(Model model, @RequestParam("login") String login) {
        try {
            model.addAttribute("content", "usersMenu");
            model.addAttribute("users", userService.getAll());
            model.addAttribute("user", userService.getUserByLogin(login));
        } catch (ServiceException e) {
            return "error";
        }
        return "basePage";
    }

    @GetMapping("/delete{id}")
    public String deleteNews(@PathVariable int id, Model model) {
        try {
            newsService.deleteNewsById(id);
        } catch (ServiceException e) {
            return "error";
        }
        return "redirect:/news-list";
    }

    @GetMapping("/edit{id}")
    public String editNews(@PathVariable int id, Model model) {
        try {
            model.addAttribute("content", "editNews");
            model.addAttribute("news", newsService.getNewsById(id));
        } catch (ServiceException e) {
            return "error";
        }
        return "basePage";
    }

    @PostMapping("/edit")
    public String editNews(@ModelAttribute("news") @Valid News news, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult);
            model.addAttribute("content", "editNews");
            return "basePage";
        } else {
            try {
                newsService.update(news);
                return "redirect:/news-list";
            } catch (ServiceException e) {
                return "error";
            }
        }
    }
}
