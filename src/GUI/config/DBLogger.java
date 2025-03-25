package GUI.config;

import GUI.config.dbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DBLogger {

    private int logId;
    private int userId;
    private String actionType;
    private String description;
    private Timestamp logDate;

    public DBLogger(int userId, String actionType, String description) {
        this.userId = userId;
        this.actionType = actionType;
        this.description = description;
    }

    
    public int getUserId() { return userId; }
    public String getActionType() { return actionType; }
    public String getDescription() { return description; }
}