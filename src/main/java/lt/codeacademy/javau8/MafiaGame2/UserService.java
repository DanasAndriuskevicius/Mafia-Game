package lt.codeacademy.javau8.MafiaGame2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {
     private List<User> users;

     public UserService(){
         users = new ArrayList<>();
         users.add(new User(1L,"Tadas", GameRole.CITIZEN));
         users.add(new User(2L,"Martynas", GameRole.MAFIA));
         users.add(new User(3L,"Sarunas", GameRole.MAFIABOSS));
         users.add(new User(4L,"Egle", GameRole.SHERIFF));
         users.add(new User(5L,"Tautvydas", GameRole.ADMIN));

     }

  public List<User> findAll(){
        return users;
    }

     public User addUser(User user){
         users.add(user);
         return user;
     }

    public String updateUser(Long userId, User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getId().equals(userId)) {
                users.set(i, updatedUser);
                return "Player with ID " +
                        userId +
                        " updated to:\n" +
                        updatedUser;
            }
        }
        return "There is no such player with ID " + userId;
    }

    public String deleteUser(Long userId) {
        for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
            User user = iterator.next();
            if (user.getId().equals(userId)) {
                iterator.remove();
                return "Player " + "'" + user + "'" + " was deleted!";
            }
        }
        return "There is no such player with ID " + userId;
    }

}
