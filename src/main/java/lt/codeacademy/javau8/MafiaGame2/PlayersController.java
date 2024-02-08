package lt.codeacademy.javau8.MafiaGame2;

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

    @PostMapping("/add")
    public PlayerDTO createPlayer(@RequestBody PlayerDTO playerDTO){
        logger.info("Handling ADD endpoint");
        return playerService.addPlayer(playerDTO);
    }

    @GetMapping("/all")
    public List<PlayerDTO> getAllPlayers(){
        return playerService.getAll();
    }

    @GetMapping("/getPlayerById/{playerId}")
    public PlayerDTO getPlayerById(@PathVariable Long playerId) {
        return playerService.getPlayerById(playerId);
    }

    @PutMapping ("/update/{playerId}")
    public PlayerDTO updatePlayer(@PathVariable Long playerId, @RequestBody PlayerDTO updatedPlayerDTO) {
        logger.info("Handling UPDATE endpoint");
        return playerService.updatePlayer(playerId, updatedPlayerDTO);
    }

    @DeleteMapping ("/delete/{playerId}")
    public String deletePlayer(@PathVariable Long playerId){
        logger.info("Handling DELETE endpoint");
        return playerService.deletePlayer(playerId);
    }
}

