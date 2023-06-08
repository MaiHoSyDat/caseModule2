package account;

public class Account {
    private String acc;
    private String pass;
    private String permission;

    public Account(String acc, String pass, String permission) {
        this.acc = acc;
        this.pass = pass;
        this.permission = permission;
    }

    public Account() {
    }

    public String getAcc() {
        return acc;
    }


    public String getPass() {
        return pass;
    }


    public String getPermission() {
        return permission;
    }


    @Override
    public String toString() {
        return acc + "," + pass + "," + permission;
    }
}
