package lt.codeacademy.javau8.MafiaGame2.entities;

import jakarta.persistence.*;
import lt.codeacademy.javau8.MafiaGame2.GameRole;
import lt.codeacademy.javau8.MafiaGame2.PlayerDTO;
import lt.codeacademy.javau8.MafiaGame2.Utils;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private GameRole gameRole;


    public Player() {
    }

   public Player(Long id, String name, GameRole gameRole) {
        this.id = id;
        this.name = name;
        this.gameRole = gameRole;
    }

    public Player(PlayerDTO playerDTO) {
        this.id = playerDTO.getId();
        this.name = playerDTO.getName();
        this.gameRole = Utils.createGameRole(playerDTO.getGameRole());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameRole getGameRole() {
        return gameRole;
    }

    public void setGameRole(GameRole gameRole) {
        this.gameRole = gameRole;
    }

    @Override
    public String toString() {
        return
                id + " " + name + " " + gameRole;
    }


}
