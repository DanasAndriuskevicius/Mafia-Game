package lt.codeacademy.javau8.MafiaGame2;

public class Utils {
    public static GameRole createGameRole(String role) {
        return switch (role) {
            case "CITIZEN" -> GameRole.CITIZEN;
            case "MAFIA" -> GameRole.MAFIA;
            case "MAFIABOSS" -> GameRole.MAFIABOSS;
            case "SHERIFF" -> GameRole.SHERIFF;
            case "ADMIN" -> GameRole.ADMIN;
            default -> throw new IllegalArgumentException("Unknown role: " + role);
        };
    }
}
