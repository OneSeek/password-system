package cn.oneseek.passport.domain.model;

public enum UPN {


    /**
     * 专用于 user 的 upn 设置。
     */
    USER("#_user_c237bc37-fd30-42f7-9f16-dd24f330ab84_#"),

    /**
     * 专用于 邮箱的简单临时认证的 upn 设置。
     */
    EMAIL_ONLY("#_emailOnly_3fa7cca0-3550-4710-929b-790d9599ed93_#"), // 专用于 邮箱的简单临时认证

    /**
     * 其他一般类型的 upn，例如用户名。
     */
    OTHERS("others");

    /** Try to judge which kind of upn it is. */
    public static UPN judge(String upn) {
        if (USER.get().equalsIgnoreCase(upn)) {
            return USER;
        } else if (EMAIL_ONLY.get().equalsIgnoreCase(upn)) {
            return EMAIL_ONLY;
        } else {
            return OTHERS;
        }
    }

    private final String upn;

    UPN(String upn) {
        this.upn = upn;
    }

    public String get() {
        return upn;
    }

    public boolean is(UPN other) {
        return this == other;
    }

    public boolean is(String upn) {
        return is(UPN.judge(upn));
    }
}

