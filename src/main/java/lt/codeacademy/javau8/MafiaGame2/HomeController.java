package lt.codeacademy.javau8.MafiaGame2;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class HomeController {

    UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public UserDTO addUser(@RequestBody UserDTO userDTO){
        System.out.println(userDTO);
        return userService.addUser(userDTO);
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers(){
        return userService.findAll();
    }

    @PostMapping("/update/{userId}")
    public String updateUser(@PathVariable Long userId, @RequestBody UserDTO updatedUserDTO) {
        String message = userService.updateUser(userId, updatedUserDTO);
        return message;
    }

    @PostMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId){
        String message = userService.deleteUser(userId);
        return message;
    }
}

