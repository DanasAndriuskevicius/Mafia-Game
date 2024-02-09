package lt.codeacademy.javau8.MafiaGame2.controllers;

import lt.codeacademy.javau8.MafiaGame2.PlayerDTO;
import lt.codeacademy.javau8.MafiaGame2.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/players")
public class PlayersController {

    private static final Logger logger = LoggerFactory.getLogger(PlayersController.class);
    @Autowired
    PlayerService playerService;

    public PlayersController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/")
    public PlayerDTO createPlayer(@RequestBody PlayerDTO playerDTO){
        logger.info("Handling ADD endpoint");
        return playerService.addPlayer(playerDTO);
    }

    @GetMapping("/")
    public List<PlayerDTO> getAllPlayers(){
        return playerService.getAll();
    }

    @GetMapping("/{playerId}")
    public PlayerDTO getPlayerById(@PathVariable Long playerId) {
        return playerService.getPlayerById(playerId);
    }

    @PutMapping ("/{playerId}")
    public PlayerDTO updatePlayer(@PathVariable Long playerId, @RequestBody PlayerDTO updatedPlayerDTO) {
        logger.info("Handling UPDATE endpoint");
        return playerService.updatePlayer(playerId, updatedPlayerDTO);
    }

    @DeleteMapping ("/{playerId}")
    public String deletePlayer(@PathVariable Long playerId){
        logger.info("Handling DELETE endpoint");
        return playerService.deletePlayer(playerId);
    }
}

