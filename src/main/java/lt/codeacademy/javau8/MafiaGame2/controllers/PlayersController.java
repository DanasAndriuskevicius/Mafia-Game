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

    @GetMapping("/")
    public List<PlayerDTO> getAllPlayers(){
        return playerService.getAll();
    }

    @GetMapping("/{id}")
    public PlayerDTO getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping("/")
    public PlayerDTO createPlayer(@RequestBody PlayerDTO playerDTO){
        logger.info("Handling ADD endpoint");
        return playerService.addPlayer(playerDTO);
    }

    @PutMapping ("/{id}")
    public PlayerDTO updatePlayer(@PathVariable ("id") Long id, @RequestBody PlayerDTO updatedPlayerDTO) {
        logger.info("Handling UPDATE endpoint");
        return playerService.updatePlayer(id, updatedPlayerDTO);
    }

    @DeleteMapping ("/{id}")
    public String deletePlayer(@PathVariable Long id){
        logger.info("Handling DELETE endpoint");
        return playerService.deletePlayer(id);
    }
}

