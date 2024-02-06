package lt.codeacademy.javau8.MafiaGame2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class PlayerService {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    final private List<Player> players;


     public PlayerService(){
         players = new ArrayList<>();

         // dummy data
        players.add(new Player(1L, "Tomas",GameRole.CITIZEN));
         players.add(new Player(2L, "ALgis",GameRole.MAFIA));
         players.add(new Player(3L, "Egle",GameRole.MAFIABOSS));
         players.add(new Player(4L, "Martynas",GameRole.SHERIFF));
         players.add(new Player(5L, "Tautvydas",GameRole.ADMIN));
          /*users.add(new User(6L, "Tomas",GameRole.CITIZEN));
         users.add(new User(7L, "ALgis",GameRole.MAFIA));
         users.add(new User(8L, "Egle",GameRole.CITIZEN));
         users.add(new User(9L, "Martynas",GameRole.CITIZEN));
         users.add(new User(10L, "Tautvydas",GameRole.CITIZEN));*/
     }

    public List<PlayerDTO> findAll() {
        List<PlayerDTO> playerDTOS = new ArrayList<>();
        for (Player player : players) {
            playerDTOS.add(new PlayerDTO(player));
        }
        return playerDTOS;
    }

    //add su random gameRole
    public PlayerDTO addPlayer(PlayerDTO playerDTO) {
        if (players.size() < 11) {
            int mafiaCount = 0;
            boolean hasSheriff = false;
            boolean hasMafiaBoss = false;
            boolean hasAdmin = false;

            for (Player player : players) {
                if (player.getId().equals(playerDTO.getId())) {
                    return null; // Jei rastas vartotojas su tuo pačiu id
                }
                if (player.getGameRole() == GameRole.SHERIFF) {
                    hasSheriff = true;
                } else if (player.getGameRole() == GameRole.MAFIABOSS) {
                    hasMafiaBoss = true;
                } else if (player.getGameRole() == GameRole.ADMIN) {
                    hasAdmin = true;
                } else if (player.getGameRole() == GameRole.MAFIA) {
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
            playerDTO.setGameRole(randomRole.toString());

            // Pridedame naują vartotoją
            Player player = new Player(playerDTO);
            players.add(player);
            return new PlayerDTO(player);
        } else {
            return null;
        }
    }


    //update metodas, kuris NEmodifikuoja userId
    public PlayerDTO updatePlayer(Long playerId, PlayerDTO updatedPlayerDTO) {
        for (Player player : players) {
            if (player.getId().equals(playerId)) {
                player.setName(updatedPlayerDTO.getName());//pakeicia varda
                player.setGameRole(Utils.createGameRole(updatedPlayerDTO.getGameRole()));//pakeicia gameRole
                updatedPlayerDTO.setId(playerId);
                updatedPlayerDTO.setImageUrl( Utils.createGameRole(updatedPlayerDTO.getGameRole()).getImageUrl());
                return updatedPlayerDTO;
            }
        }
        return null;
    }

    public String deletePlayer(Long playerId) {
        for (Iterator<Player> iterator = players.iterator();
             iterator.hasNext();) {
            Player player = iterator.next();
            if (player.getId().equals(playerId)) {
                iterator.remove();
                return "Player " + "'" + player + "'" + " was successfully deleted!";
            }
        }
        return "There is no such player with ID " + playerId;
    }

}
