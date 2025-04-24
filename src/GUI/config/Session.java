package GUI.config;

public class Session {
    private static Session instance;
    private int userId;  // Emp_id
    private String username;

    // Private constructor to prevent instantiation
    public Session() {}

    // Singleton instance
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    // Create a session with employee ID and username
    public void createSession(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    // Getters for session data
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}

