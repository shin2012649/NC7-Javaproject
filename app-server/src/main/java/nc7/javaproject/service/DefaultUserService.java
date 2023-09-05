package nc7.javaproject.service;


import nc7.javaproject.dao.UserDao;
import nc7.javaproject.vo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultUserService implements UserService {

    UserDao userDao;

    public DefaultUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public int add(User user) throws Exception {
        return userDao.insert(user);
    }

    @Override
    public List<User> list() throws Exception {
        return userDao.findAll();
    }

    @Override
    public User get(int userNo) throws Exception {
        return userDao.findBy(userNo);
    }

    @Override
    public User get(String email, String password) throws Exception {
        return userDao.findByEmailAndPassword(email, password);
    }

    @Transactional
    @Override
    public int update(User user) throws Exception {
        return userDao.update(user);
    }

    @Transactional
    @Override
    public int delete(int userNo) throws Exception {
        return userDao.delete(userNo);
    }
}
