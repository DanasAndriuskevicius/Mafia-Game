package lt.codeacademy.javau8.MafiaGame2;

import lt.codeacademy.javau8.MafiaGame2.entities.Player;
import lt.codeacademy.javau8.MafiaGame2.repositories.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class DataLoader implements CommandLineRunner {

    PlayerRepository playerRepository;

    public DataLoader(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;

    }

    @Override
    public void run(String... args) throws Exception {

        if (playerRepository.count() == 0){
            Player player1 = new Player(1L,"DemoPlayer1",GameRole.CITIZEN);
            Player player2 = new Player(2L,"DemoPlayer2",GameRole.MAFIABOSS);
            Player player3 = new Player(3L,"DemoPlayer3",GameRole.MAFIA);
            Player player4 = new Player(4L,"DemoPlayer4",GameRole.SHERIFF);
            Player player5 = new Player(5L,"DemoPlayer5",GameRole.ADMIN);

            playerRepository.save(player1);
            playerRepository.save(player2);
            playerRepository.save(player3);
            playerRepository.save(player4);
            playerRepository.save(player5);

        }
    }
}
