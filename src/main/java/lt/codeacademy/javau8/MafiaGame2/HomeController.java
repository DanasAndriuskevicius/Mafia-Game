package lt.codeacademy.javau8.MafiaGame2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public UserDTO addUser(@RequestBody UserDTO userDTO){
        logger.info("Handling ADD endpoint");
        return userService.addUser(userDTO);
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers(){
        return userService.findAll();
    }

    @PutMapping ("/update/{userId}")
    public UserDTO updateUser(@PathVariable Long userId, @RequestBody UserDTO updatedUserDTO) {
        logger.info("Handling UPDATE endpoint");
        return userService.updateUser(userId, updatedUserDTO);
    }

    @DeleteMapping ("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId){
        logger.info("Handling DELETE endpoint");
        String message = userService.deleteUser(userId);
        return message;
    }
}

