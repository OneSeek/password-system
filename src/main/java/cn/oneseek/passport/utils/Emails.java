package cn.oneseek.passport.utils;

import java.util.regex.Pattern;

public class Emails {
    private Emails() {
    }

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );

    /** A simply email address format validation function. */
    public static boolean isValidEmailAddress(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
