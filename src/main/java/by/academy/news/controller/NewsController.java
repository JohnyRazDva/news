package by.academy.news.controller;

import by.academy.news.entity.news.News;
import by.academy.news.service.ServiceException;
import by.academy.news.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Eugene Petrov
 */
@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/add-news")
    public String addNews(Model model) {
        model.addAttribute("content", "addNews");
        News news = new News();
        model.addAttribute("news", news);
        return "basePage";
    }

    @PostMapping("/add-news")
    public String addNews(@ModelAttribute("news") @Valid News news, BindingResult bindingResult, Model model, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("content", "addNews");
            model.addAttribute("errors", bindingResult);
        } else {
            try {
                newsService.addNews(news, authentication.getName());
                model.addAttribute("content", "addNewsDone");
            } catch (Exception e) {
                return "error";
            }
        }
        return "basePage";
    }

    @GetMapping("/view{id}")
    public String viewNews(@PathVariable int id, Model model){
        try {
            model.addAttribute("content","singleNews");
            News news = newsService.getNewsById(id);
            model.addAttribute("news", news);
        }catch (ServiceException e){
            return "error";
        }
        return "basePage";
    }

    @GetMapping("/news-list")
    public String newsList(Model model){
        try {
            model.addAttribute("content","newsList");
            model.addAttribute("newsList", newsService.getAll());
        }catch (ServiceException e){
            return "error";
        }
        return "basePage";
    }
}
