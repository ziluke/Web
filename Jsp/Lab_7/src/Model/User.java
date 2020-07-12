package Model;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Objects;
import java.util.Set;

/**
 * Created by forest on 16.12.2014.
 */
public class User {

    private String username;
    private int PlayerNo;

    public User(String username, int PlayerNo){
        this.username = username;
        this.PlayerNo = PlayerNo;
    }

    public User()
    {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return PlayerNo == user.PlayerNo &&
                Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, PlayerNo);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", PlayerNo=" + PlayerNo +
                '}';
    }

    public int getPlayerNo() {
        return PlayerNo;
    }

    public void setPlayerNo(int playerNo) {
        PlayerNo = playerNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



}