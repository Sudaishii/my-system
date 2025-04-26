package GUI.config;

public class ResetSession {
    private static ResetSession instance;
    private String email;

    public ResetSession() {}

    public static ResetSession getInstance() {
        if (instance == null) {
            instance = new ResetSession();
        }
        return instance;
    }

    public void createSession(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void clearSession() {
        email = null;
    }
}
