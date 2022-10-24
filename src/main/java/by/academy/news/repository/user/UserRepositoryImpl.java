package by.academy.news.repository.user;

import by.academy.news.entity.news.News;
import by.academy.news.entity.user.User;
import by.academy.news.repository.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * @author Eugene Petrov
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(User user) throws RepositoryException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(user);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }

    }

    @Override
    public User getUserByLogin(String userLogin) throws RepositoryException {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(User.class, userLogin);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<User> getAll() throws RepositoryException {
        try {
            Session session = sessionFactory.getCurrentSession();
            return loadAllData(User.class, session);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }


    private static List<User> loadAllData(Class<User> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(type);
        criteria.from(type);
        List<User> data = session.createQuery(criteria).getResultList();
        return data;
    }
}
