package by.academy.news.service.news;

import by.academy.news.entity.news.News;

import by.academy.news.repository.RepositoryException;
import by.academy.news.repository.news.NewsRepository;
import by.academy.news.repository.user.UserRepository;
import by.academy.news.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Eugene Petrov
 */
@Service
public class NewsServiceImpl implements NewsService {
    private static final int LATEST_NEWS_COUNT = 3;
    @Autowired
    NewsRepository newsRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<News> getLatestNews() throws ServiceException {
        try {
            return newsRepository.getNewsByCount(LATEST_NEWS_COUNT);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
    public void addNews(News news, String userLogin) throws ServiceException {
        try {
            news.setUser(userRepository.getUserByLogin(userLogin));
            news.setDate(LocalDate.now());
            newsRepository.save(news);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public News getNewsById(int id) throws ServiceException {
        try {
            return newsRepository.getNewsById(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<News> getAll() throws ServiceException {
        try {
            return newsRepository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
    public void deleteNewsById(int id) throws ServiceException {
        try {
            newsRepository.deleteById(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
    public void update(News news) throws ServiceException {
        try {
            newsRepository.update(news);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
