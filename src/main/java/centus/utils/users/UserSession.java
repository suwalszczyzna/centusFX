package centus.utils.users;

public final class UserSession {

    private static UserSession instance;

    private String userId;

    private UserSession(String userId) {
        this.userId = userId;
    }

    public static UserSession getInstance(String userId) {
        if(instance == null) {
            instance = new UserSession(userId);
        }
        return instance;
    }

    public String getUserId() {
        return userId;
    }

    public void cleanUserSession() {
        userId = null;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userId + '\'' +
                '}';
    }
}