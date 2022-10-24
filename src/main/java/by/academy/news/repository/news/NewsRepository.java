package by.academy.news.repository.news;

import by.academy.news.entity.news.News;
import by.academy.news.repository.RepositoryException;

import java.util.List;

/**
 * @author Eugene Petrov
 */
public interface NewsRepository {
    List<News> getNewsByCount(int count) throws RepositoryException;
    void save(News news) throws RepositoryException;
    News getNewsById(int id) throws RepositoryException;
    List<News> getAll() throws RepositoryException;
    void deleteById(int id) throws RepositoryException;
    void update(News news) throws RepositoryException;

}
