public class SettingsModel {
    String ConnectionUrl;
    String UserName;
    String Password;

    public SettingsModel(String ConnectionUrl, String DatabaseName, String UserName, String Password) {
        ConnectionUrl = this.ConnectionUrl;
        UserName = this.UserName;
        Password = this.Password;
    }

    public String getConnectionUrl() {
        return ConnectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        ConnectionUrl = connectionUrl;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
