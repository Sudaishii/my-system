package GUI.config;

public class Session {
    private static Session instance;
    private int userId;  // Emp_id
    private String username;


    public Session() {}


    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }


    public void createSession(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }


    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}

