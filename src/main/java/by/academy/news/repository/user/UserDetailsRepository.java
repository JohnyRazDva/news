package by.academy.news.repository.user;

import by.academy.news.entity.user.User;
import by.academy.news.repository.RepositoryException;

/**
 * @author Eugene Petrov
 */
public interface UserDetailsRepository {
    User findUserByUsername(String username);
}
