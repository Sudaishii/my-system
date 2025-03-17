package GUI.config;

public class Session {
    private static Session instance;
    private int userId;
    private String uname;

    // Private constructor to enforce singleton pattern
    private Session() {}

    // Singleton instance method
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    // Create session
    public void createSession(int userId, String uname) {
        this.userId = userId;
        this.uname = uname;
    }

    // Retrieve username
    public String getUname() {
        return uname;
    }

    // Clear session
    public void clearSession() {
        userId = 0;
        uname = null;
    }
}
