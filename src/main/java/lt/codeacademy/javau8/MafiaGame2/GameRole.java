package lt.codeacademy.javau8.MafiaGame2;

public enum GameRole {
    CITIZEN("/images/citizen.png"),
    MAFIA("/images/mafia.png"),
    MAFIABOSS("/images/mafiaboss.png"),
    SHERIFF("/images/sheriff.png"),
    ADMIN("/images/admin.png");

    private final String imageUrl;

    GameRole(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
