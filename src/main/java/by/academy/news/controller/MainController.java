package by.academy.news.controller;


import by.academy.news.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Eugene Petrov
 */
@Controller
public class MainController {

    @Autowired
    NewsService newsService;

    @GetMapping("/")
    public String basePage(Model model, Authentication authentication){
        try {
            if (authentication != null) {
                model.addAttribute("newsList", newsService.getAll());
            }else {
                model.addAttribute("newsList", newsService.getLatestNews());
           }
        } catch (Exception e) {
            return "error";
        }
        return "basePage";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "accessDenied";
    }

}
