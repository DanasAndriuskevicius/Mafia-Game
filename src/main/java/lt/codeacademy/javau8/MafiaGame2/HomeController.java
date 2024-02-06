package lt.codeacademy.javau8.MafiaGame2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    PlayerService playerService;

    public HomeController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/add")
    public PlayerDTO addPlayer(@RequestBody PlayerDTO playerDTO){
        logger.info("Handling ADD endpoint");
        return playerService.addPlayer(playerDTO);
    }

    @GetMapping("/all")
    public List<PlayerDTO> getAllPlayers(){
        return playerService.findAll();
    }

    @PutMapping ("/update/{playerId}")
    public PlayerDTO updatePlayer(@PathVariable Long playerId, @RequestBody PlayerDTO updatedPlayerDTO) {
        logger.info("Handling UPDATE endpoint");
        return playerService.updatePlayer(playerId, updatedPlayerDTO);
    }

    @DeleteMapping ("/delete/{userId}")
    public String deletePlayer(@PathVariable Long userId){
        logger.info("Handling DELETE endpoint");
        return playerService.deletePlayer(userId);
    }
}

