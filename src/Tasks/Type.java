package Tasks;

public enum Type {
    WORK ("Рабочая"),
    PERSONAL ("Личная");
    public final String type;
    Type(String type) {
        this.type = type;
    }
}
