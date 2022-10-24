package by.academy.news.repository.user;

import by.academy.news.entity.user.User;
import by.academy.news.repository.RepositoryException;

import java.util.List;

/**
 * @author Eugene Petrov
 */
public interface UserRepository {
    void save(User user) throws RepositoryException;
    User getUserByLogin(String userLogin) throws RepositoryException;
    List<User> getAll() throws RepositoryException;
}
