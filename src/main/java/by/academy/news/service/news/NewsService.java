package by.academy.news.service.news;

import by.academy.news.entity.news.News;
import by.academy.news.entity.user.User;
import by.academy.news.service.ServiceException;

import java.util.List;

/**
 * @author Eugene Petrov
 */
public interface NewsService {
    List<News> getLatestNews() throws ServiceException;
    void addNews(News news, String user) throws ServiceException;
    News getNewsById(int id) throws ServiceException;
    List<News> getAll() throws ServiceException;
    void deleteNewsById(int id) throws ServiceException;
    void update(News news) throws ServiceException;
}
