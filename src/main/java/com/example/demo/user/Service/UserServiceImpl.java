package com.example.demo.user.Service;


import com.example.demo.user.DAO.UserDAO;
import com.example.demo.user.entity.Authority;
import com.example.demo.user.entity.User;
import com.example.demo.user.entity.UserActivation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;
    
    @Override
    @Transactional
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    @Transactional
    public void saveUser(User theUser) {
        userDAO.saveUser(theUser);
    }

    @Override
    @Transactional
    public void saveUser(User theUser, ArrayList<Authority> authorities) {
        userDAO.saveUser(theUser, authorities);
    }

    @Override
    @Transactional
    public User getUser(String username) {
        return userDAO.getUser(username);
    }
    
    @Override
    @Transactional
    public void deleteUser(String username) {
        userDAO.deleteUser(username);
    }

    @Override
    @Transactional
    public void updatePassword(String username, String password) {
        userDAO.updatePassword(username, password);
    }
    
    @Override
    @Transactional
    public void updateEmail(String username, String email) {
        userDAO.updateEmail(username, email);
    }

    @Override
    @Transactional
    public void enableUser(String username) {
        userDAO.enableUser(username);
    }

    @Override
    @Transactional
    public String getActivationCode(String username) {
        return userDAO.getActivationCode(username);
    }

    @Override
    @Transactional
    public UserActivation getActivation(String username) {
        return userDAO.getActivation(username);
    }

    @Override
    @Transactional
    public void updateActivation(UserActivation activation) {
        userDAO.updateActivation(activation);
    }
}
