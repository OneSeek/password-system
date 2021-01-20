package cn.oneseek.passport.domain.model;

import java.util.Objects;

public class Email {
    public String address;

    public static Email of(String address) {
        Email email = new Email();
        {
            email.address = address;
        }
        return email;
    }

    @Override public String toString() {
        return address;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(address, email.address);
    }

    @Override public int hashCode() {
        return Objects.hash(address);
    }
}

