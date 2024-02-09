package lt.codeacademy.javau8.MafiaGame2;

import lt.codeacademy.javau8.MafiaGame2.entities.Player;

public class PlayerDTO {
    private Long id;
    private String name;
    private String gameRole;
    private String imageUrl;

    public PlayerDTO() {
    }

    public PlayerDTO(Player player){
        this.id = player.getId();
        this.name = player.getName();
        this.gameRole = player.getGameRole().toString();
        this.imageUrl = player.getGameRole().getImageUrl();
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

    public String getGameRole() {
        return gameRole;
    }

    public void setGameRole(String gameRole) {
        this.gameRole = gameRole;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
