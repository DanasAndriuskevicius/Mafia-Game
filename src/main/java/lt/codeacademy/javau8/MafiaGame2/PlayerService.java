package lt.codeacademy.javau8.MafiaGame2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerService {

    private static final Logger logger = LoggerFactory.getLogger(PlayersController.class);
    @Autowired
    PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    static private List<Player> players;
     public PlayerService(){
         players = new ArrayList<>();

         // dummy data
        players.add(new Player(1L, "Tomas",GameRole.CITIZEN));
        players.add(new Player(2L, "ALgis",GameRole.MAFIA));
         //players.add(new Player(3L, "Egle",GameRole.MAFIABOSS));
         //players.add(new Player(4L, "Martynas",GameRole.SHERIFF));
         //players.add(new Player(5L, "Tautvydas",GameRole.ADMIN));

     }

    // get all players metodas NAUJAS!!!
    public List<PlayerDTO> getAll() {
        List<PlayerDTO> playerDTOS = new ArrayList<>();
        for (Player player : playerRepository.findAll()) {
            playerDTOS.add(new PlayerDTO(player));
        }
        return playerDTOS;
    }
    // arba galima uzrasyti get All metoda taip:
   /* public List<PlayerDTO> getAll() {
        return playerRepository.findAll().stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }*/


    //get one player metodas
    public PlayerDTO getPlayerById(Long playerId) {
         Player player = playerRepository.findById(playerId).orElse(null);
             if (player != null){
                 return new PlayerDTO(player);
             }
        return null;
    }

    //add player su random gameRole
    public PlayerDTO addPlayer(PlayerDTO playerDTO) {
        List<Player> allPlayers = playerRepository.findAll();
        if (allPlayers.size() < 11) {
            int mafiaCount = 0;
            boolean hasSheriff = false;
            boolean hasMafiaBoss = false;
            boolean hasAdmin = false;

            for (Player player : allPlayers) {
               /* if (player.getId().equals(playerDTO.getId())) {
                    return null; // Jei rastas vartotojas su tuo pačiu id
                }*/
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
            playerRepository.save(player);
            return new PlayerDTO(player);
        } else {
            return null;
        }
    }

    //update metodas, kuris NEmodifikuoja userId
    public PlayerDTO updatePlayer(Long playerId, PlayerDTO updatedPlayerDTO) {
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            player.setName(updatedPlayerDTO.getName());//pakeicia varda
            player.setGameRole(Utils.createGameRole(updatedPlayerDTO.getGameRole()));//pakeicia gameRole
            //updatedPlayerDTO.setId(playerId);
            //updatedPlayerDTO.setImageUrl( Utils.createGameRole(updatedPlayerDTO.getGameRole()).getImageUrl());

            Player updatedPlayer = playerRepository.save(player);
            return new PlayerDTO(updatedPlayer);
        }
        return null;
    }

    //delete metodas
    public String deletePlayer(Long playerId) {
        Optional<Player> player = playerRepository.findById(playerId);
        if (player.isPresent()) {
            playerRepository.deleteById(playerId);
            return "Player with ID " + playerId + " was successfully deleted!";
        } else {
            return "There is no such player with ID " + playerId;
        }
    }

}
