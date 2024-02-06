package lt.codeacademy.javau8.MafiaGame2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class UserService {
     final private List<User> users;


     public UserService(){
         users = new ArrayList<>();

         // dummy data
        users.add(new User(1L, "Tomas",GameRole.CITIZEN));
         users.add(new User(2L, "ALgis",GameRole.MAFIA));
         users.add(new User(3L, "Egle",GameRole.MAFIABOSS));
         users.add(new User(4L, "Martynas",GameRole.SHERIFF));
         users.add(new User(5L, "Tautvydas",GameRole.ADMIN));
          /*users.add(new User(6L, "Tomas",GameRole.CITIZEN));
         users.add(new User(7L, "ALgis",GameRole.MAFIA));
         users.add(new User(8L, "Egle",GameRole.CITIZEN));
         users.add(new User(9L, "Martynas",GameRole.CITIZEN));
         users.add(new User(10L, "Tautvydas",GameRole.CITIZEN));*/
     }

    public List<UserDTO> findAll() {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(new UserDTO(user));
        }
        return userDTOs;
    }

    //add su random gameRole
    public UserDTO addUser(UserDTO userDTO) {
        if (users.size() < 11) {
            int mafiaCount = 0;
            boolean hasSheriff = false;
            boolean hasMafiaBoss = false;
            boolean hasAdmin = false;

            for (User user : users) {
                if (user.getId().equals(userDTO.getId())) {
                    return null; // Jei rastas vartotojas su tuo pačiu id
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

            // Sukuriame sąrašą su visais vaidmenimis, išskyrus jau priskirtus
            List<GameRole> availableRoles = new ArrayList<>();
            if (!hasSheriff) availableRoles.add(GameRole.SHERIFF);
            if (!hasMafiaBoss) availableRoles.add(GameRole.MAFIABOSS);
            if (!hasAdmin) availableRoles.add(GameRole.ADMIN);
            if (mafiaCount < 2) availableRoles.add(GameRole.MAFIA);
            availableRoles.add(GameRole.CITIZEN);

            // Priskiriame atsitiktinį vaidmenį
            Random random = new Random();
            GameRole randomRole = availableRoles.get(random.nextInt(availableRoles.size()));
            userDTO.setGameRole(randomRole.toString());

            // Pridedame naują vartotoją
            User user = new User(userDTO);
            users.add(user);
            return new UserDTO(user);
        } else {
            return null;
        }
    }


    //update metodas, kuris NEmodifikuoja userId
    public UserDTO updateUser(Long userId, UserDTO updatedUserDTO) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                user.setName(updatedUserDTO.getName());//pakeiciu varda
                user.setGameRole(Utils.createGameRole(updatedUserDTO.getGameRole()));//pakeiciu gameRole
                updatedUserDTO.setId(userId);
                updatedUserDTO.setImageUrl( Utils.createGameRole(updatedUserDTO.getGameRole()).getImageUrl());
                return updatedUserDTO;
            }
        }
        return null;
    }

    public String deleteUser(Long userId) {
        for (Iterator<User> iterator = users.iterator();
             iterator.hasNext();) {
            User user = iterator.next();
            if (user.getId().equals(userId)) {
                iterator.remove();
                return "Player " + "'" + user + "'" + " was successfully deleted!";
            }
        }
        return "There is no such player with ID " + userId;
    }

}
