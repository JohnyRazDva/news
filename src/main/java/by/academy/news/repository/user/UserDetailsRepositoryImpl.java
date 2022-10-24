package by.academy.news.repository.user;

import by.academy.news.entity.user.User;
import by.academy.news.repository.RepositoryException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Eugene Petrov
 */
@Repository
public class UserDetailsRepositoryImpl implements UserDetailsRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUserByUsername(String username) {
        try {
            return sessionFactory.getCurrentSession().get(User.class, username);
        }catch (Exception e){
            return null;
        }
    }
}
