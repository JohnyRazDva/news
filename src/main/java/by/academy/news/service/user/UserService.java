package by.academy.news.service.user;

import by.academy.news.entity.user.User;
import by.academy.news.service.ServiceException;

import java.util.List;

/**
 * @author Eugene Petrov
 */
public interface UserService {
    void save(User user) throws ServiceException;
    List<User> getAll() throws ServiceException;
    User getUserByLogin(String login) throws ServiceException;
}
