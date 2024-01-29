package lt.codeacademy.javau8.MafiaGame2;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        System.out.println(user);
        return userService.addUser(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @PostMapping("/update/{userId}")
    public String updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        String message = userService.updateUser(userId, updatedUser);
        return message;
    }

    @PostMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId){
        String message = userService.deleteUser(userId);
        return message;
    }
}

