package domain;

public class User {
    private int id;
    private String user;
    private String passw;

    public User(int id, String user, String passw) {
        this.id = id;
        this.user = user;
        this.passw = passw;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", passw='" + passw + '\'' +
                '}';
    }
}
