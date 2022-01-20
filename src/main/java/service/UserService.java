package service;

import model.User;
import repository.UserRepository;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    private CustomerService customerService = new CustomerService();
    private ClerkService clerkService = new ClerkService();

    public User login(String userName,String password)
    {
        User user = userRepository.findByUserName(userName);
        if(user.getPassWord().equals(password)){
            return user;
        }
        return null;
    }


    public int add(String firstName, String lastName, String nationalCode, String phoneNumber, String address, String userName, String passWord, String role){
        int roleId = Integer.valueOf(role);
        User user = new User(firstName,lastName,nationalCode,phoneNumber,address,userName,passWord,roleId);
        int id = userRepository.add(user);
        if(roleId == 1){
         return 0;
        }else if(roleId == 2){
          return  customerService.add(id);
        }else if(roleId == 3){
          return clerkService.add(id);
        }
        return 0;
    }
}
