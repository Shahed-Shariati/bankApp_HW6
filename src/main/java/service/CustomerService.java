package service;

import repository.CustomerRepository;

public class CustomerService {
    private CustomerRepository customerRepository = new CustomerRepository();

    public int add(int userId)
    {
       return customerRepository.add(userId);
    }
}
