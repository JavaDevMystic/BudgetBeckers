package com.example.demo.user.Service;



import com.example.demo.user.entity.Authority;
import com.example.demo.user.entity.User;
import com.example.demo.user.entity.UserActivation;

import java.util.ArrayList;
import java.util.List;


public interface UserService {

    public List<User> getUsers();
    
    public void saveUser(User theUser);
    
    public void saveUser(User theUser, ArrayList<Authority> authorities);
    
    public User getUser(String username);
    
    public void deleteUser(String username);
    
    public void updatePassword(String username, String password);
    
    public void updateEmail(String username, String email);
    
    public void enableUser(String username);
    
    public String getActivationCode(String username);
    
    public UserActivation getActivation(String username);
    
    public void updateActivation(UserActivation activation);
}
