package lt.codeacademy.javau8.MafiaGame2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        System.out.println(product);
        //return new User(1L, "Danas", GameRole.CITIZEN);
        return product;
    }

}

