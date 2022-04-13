package Classes;

public class CurentUser {


    public static String username;
    public static String user_email;
    public static boolean offlineMode;
    public static int currentActivity;
    private CurentUser(){}

    public static void SetInstance(String Username,String user_email,boolean offlineMode){
        CurentUser.username=username;
        CurentUser.user_email=user_email;
        CurentUser.offlineMode=offlineMode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public boolean isOfflineMode() {
        return offlineMode;
    }

    public void setOfflineMode(boolean offlineMode) {
        this.offlineMode = offlineMode;
    }
}
