package service;

import model.User;
import service.UserService;

public class ApplicationService {
    private UserService userService = new UserService();

    public User login(String userName, String password){
        return   userService.login(userName,password);
    }
}
