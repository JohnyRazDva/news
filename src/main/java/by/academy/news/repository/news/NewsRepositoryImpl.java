package by.academy.news.repository.news;

import by.academy.news.entity.news.News;
import by.academy.news.repository.RepositoryException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene Petrov
 */
@Repository
public class NewsRepositoryImpl implements NewsRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<News> getNewsByCount(int count) throws RepositoryException {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<News> theQuery = session.createQuery("from News order by date desc", News.class);
            theQuery.setMaxResults(count);
            return theQuery.getResultList();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Transactional
    @Override
    public void save(News news) throws RepositoryException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(news);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public News getNewsById(int id) throws RepositoryException {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(News.class, id);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<News> getAll() throws RepositoryException {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<News> theQuery = session.createQuery("from News order by date desc", News.class);
            return theQuery.getResultList();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void deleteById(int id) throws RepositoryException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(getNewsById(id));
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void update(News news) throws RepositoryException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(news);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }
}
