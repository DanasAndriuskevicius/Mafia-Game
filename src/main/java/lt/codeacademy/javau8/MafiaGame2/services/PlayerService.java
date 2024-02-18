package lt.codeacademy.javau8.MafiaGame2.services;

import lt.codeacademy.javau8.MafiaGame2.GameRole;
import lt.codeacademy.javau8.MafiaGame2.PlayerDTO;
import lt.codeacademy.javau8.MafiaGame2.controllers.PlayersController;
import lt.codeacademy.javau8.MafiaGame2.entities.Player;
import lt.codeacademy.javau8.MafiaGame2.repositories.PlayerRepository;
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


    public List<PlayerDTO> getAll() {
        List<PlayerDTO> playerDTOS = new ArrayList<>();
        for (Player player : playerRepository.findAll()) {
            playerDTOS.add(new PlayerDTO(player));
        }
        return playerDTOS;
    }

    public PlayerDTO getPlayerById(Long id) {
         Player player = playerRepository.findById(id).orElse(null);
             if (player != null){
                 return new PlayerDTO(player);
             }
        return null;
    }

    public PlayerDTO addPlayer(PlayerDTO playerDTO) {

        if (playerDTO.getName() == null || playerDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be empty");
        }

        List<Player> allPlayers = playerRepository.findAll();
        if (allPlayers.size() < 11) {
            int mafiaCount = 0;
            boolean hasSheriff = false;
            boolean hasMafiaBoss = false;
            boolean hasAdmin = false;

            for (Player player : allPlayers) {
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

            List<GameRole> availableRoles = new ArrayList<>();
            if (!hasSheriff) availableRoles.add(GameRole.SHERIFF);
            if (!hasMafiaBoss) availableRoles.add(GameRole.MAFIABOSS);
            if (!hasAdmin) availableRoles.add(GameRole.ADMIN);
            if (mafiaCount < 2) availableRoles.add(GameRole.MAFIA);
            availableRoles.add(GameRole.CITIZEN);

            Random random = new Random();
            GameRole randomRole = availableRoles.get(random.nextInt(availableRoles.size()));
            playerDTO.setGameRole(randomRole.toString());

            Player player = new Player(playerDTO);
            playerRepository.save(player);
            return new PlayerDTO(player);
        } else {
            return null;
        }
    }

    public PlayerDTO updatePlayer(Long id, PlayerDTO updatedPlayerDTO) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            player.setName(updatedPlayerDTO.getName());

            Player updatedPlayer = playerRepository.save(player);
            return new PlayerDTO(updatedPlayer);
        }
        return null;
    }

    public String deletePlayer(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        if (player.isPresent()) {
            playerRepository.deleteById(id);
            return "Player with ID " + id + " was successfully deleted!";
        } else {
            return "There is no such player with ID " + id;
        }
    }

}
