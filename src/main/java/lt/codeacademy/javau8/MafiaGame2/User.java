package lt.codeacademy.javau8.MafiaGame2;

class User {
    Long id;
    String name;
    GameRole gameRole;


    public User() {
    }

   public User(Long id, String name, GameRole gameRole) {
        this.id = id;
        this.name = name;
        this.gameRole = gameRole;
    }

    public User(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.name = userDTO.getName();
        this.gameRole = Utils.createGameRole(userDTO.getGameRole());
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
