package lt.codeacademy.javau8.MafiaGame2;

public class Product {
    Long id;
    String name;
    GameRole gameRole;

    public Product(){}

    public Product(Long id, String name, GameRole gameRole) {
        this.id = id;
        this.name = name;
        this.gameRole = gameRole;
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
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gameRole=" + gameRole +
                '}';
    }
}
