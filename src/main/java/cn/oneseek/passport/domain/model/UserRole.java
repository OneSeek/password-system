package cn.oneseek.passport.domain.model;

public enum UserRole {
    USER("User"),

    EMAIL_ONLY("EmailOnlyUser"),

    OTHERS("others");

    public static UserRole judge(String role) {
        if (USER.get().equalsIgnoreCase(role)) {
            return USER;
        } else if (EMAIL_ONLY.get().equalsIgnoreCase(role)) {
            return EMAIL_ONLY;
        } else {
            return OTHERS;
        }
    }

    final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String get() {
        return role;
    }

    public boolean is(UserRole other) {
        return this == other;
    }

    public boolean is(String role) {
        return is(UserRole.judge(role));
    }
}
