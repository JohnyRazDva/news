package by.academy.news.service.user;

import by.academy.news.entity.user.User;
import by.academy.news.repository.RepositoryException;
import by.academy.news.repository.user.UserRepository;
import by.academy.news.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import java.util.List;

/**
 * @author Eugene Petrov
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public void save(User user) throws ServiceException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userRepository.save(user);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() throws ServiceException {
        try {
            return userRepository.getAll();
        } catch (RepositoryException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByLogin(String login) throws ServiceException {
        try {
            return userRepository.getUserByLogin(login);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

}
