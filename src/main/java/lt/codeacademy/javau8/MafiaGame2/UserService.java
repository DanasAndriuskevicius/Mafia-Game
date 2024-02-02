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

         // dummy data
         users.add(new User(1L, "Tomas",GameRole.CITIZEN));
         users.add(new User(2L, "ALgis",GameRole.MAFIA));
         users.add(new User(3L, "Egle",GameRole.MAFIABOSS));
         users.add(new User(4L, "Martynas",GameRole.SHERIFF));
         users.add(new User(5L, "Tautvydas",GameRole.ADMIN));
         users.add(new User(6L, "Tomas",GameRole.CITIZEN));
         users.add(new User(7L, "ALgis",GameRole.MAFIA));
         users.add(new User(8L, "Egle",GameRole.CITIZEN));
         users.add(new User(9L, "Martynas",GameRole.CITIZEN));
         users.add(new User(10L, "Tautvydas",GameRole.CITIZEN));
     }

    public List<UserDTO> findAll() {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(new UserDTO(user));
        }
        return userDTOs;
    }
    //modifikuotas add metodas

    public UserDTO addUser(UserDTO userDTO) {
        if (users.size() < 11) {
            int mafiaCount = 0;
            boolean hasSheriff = false;
            boolean hasMafiaBoss = false;
            boolean hasAdmin= false;

            for (User user : users) {
                if (user.getId().equals(userDTO.getId())) {
                    return null; // Jei rastas vartotojas su tuo paciu id
                }
                if (user.getGameRole() == GameRole.SHERIFF) {
                    hasSheriff = true;
                } else if (user.getGameRole() == GameRole.MAFIABOSS) {
                    hasMafiaBoss = true;
                } else if (user.getGameRole() == GameRole.ADMIN) {
                    hasAdmin = true;
                } else if (user.getGameRole() == GameRole.MAFIA) {
                    mafiaCount++;
                }
            }
            if ((!hasSheriff && userDTO.getGameRole().equals("SHERIFF")) ||
                    (!hasMafiaBoss && userDTO.getGameRole().equals("MAFIABOSS")) ||
                    (!hasAdmin && userDTO.getGameRole().equals("ADMIN")) ||
                    (mafiaCount < 2 && userDTO.getGameRole().equals("MAFIA")) ||
                    (userDTO.getGameRole().equals("CITIZEN"))) {
                User user = new User(userDTO);
                users.add(user);
                return new UserDTO(user);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

        //senas add metodas
    /* public UserDTO addUser(UserDTO userDTO){
         if(users.size() < 11) {
             User user = new User(userDTO);
             users.add(user);
             return new UserDTO(user);
         }else {
             return null;
         }
     }*/


    //update metodas, kuris nemodifikuoja userId
    public String updateUser(Long userId, UserDTO updatedUserDTO) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getId().equals(userId)) {
                user.setName(updatedUserDTO.getName());//pakeiciu varda
                user.setGameRole(Utils.createGameRole(updatedUserDTO.getGameRole()));//pakeiciu gameRole

                return "Player: " +
                        userId +
                        " was successfully updated to: " + user;
            }
        }
        return "There is no such player with ID " + userId;
    }

    public String deleteUser(Long userId) {
        for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
            User user = iterator.next();
            if (user.getId().equals(userId)) {
                iterator.remove();
                return "Player " + "'" + user + "'" + " was successfully deleted!";
            }
        }
        return "There is no such player with ID " + userId;
    }

}
