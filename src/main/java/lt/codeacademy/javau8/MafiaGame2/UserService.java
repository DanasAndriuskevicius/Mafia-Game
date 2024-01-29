package lt.codeacademy.javau8.MafiaGame2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
     private List<User> users;

     public UserService(){
         users = new ArrayList<>();
         users.add(new User(1L,"Tadas", GameRole.CITIZEN));
         users.add(new User(2L,"Martynas", GameRole.MAFIA));

     }

  public List<User> findAll(){
        return users;
    }

     public User addUser(User user){
         users.add(user);
         return user;
     }

}
