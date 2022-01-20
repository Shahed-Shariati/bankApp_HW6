package service;

import model.Clerk;
import repository.ClerkRepository;

import java.util.List;

public class ClerkService {
    private ClerkRepository clerkRepository = new ClerkRepository();

    public int add(int id){
        return clerkRepository.add(id);
    }
    public void delete(String id){
        int idInt = Integer.parseInt(id);
        clerkRepository.delete(idInt);
    }
    public void showClerks(){
        List<Clerk> clerks = clerkRepository.showClerks();
        for (Clerk clerk:clerks) {
            System.out.println(clerk);

        }
    }
}
