package cn.oneseek.passport.domain.model;

public class UserSession {
    String userId;
    String userName;
    String sessionId;

    public static UserSession of(String userName) {
        UserSession userSession = new UserSession();
        {
            userSession.userName = userName;
        }
        return userSession;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
