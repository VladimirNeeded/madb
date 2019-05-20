package model;

import java.util.Objects;

public class Code {
    private String value;
    private int user_id;
    private int good_id;

    public Code(String value, int user_id, int good_id) {
        this.value = value;
        this.user_id = user_id;
        this.good_id = good_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGood_id() {
        return good_id;
    }

    public void setGood_id(int good_id) {
        this.good_id = good_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code code = (Code) o;
        return user_id == code.user_id &&
                good_id == code.good_id &&
                Objects.equals(value, code.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, user_id, good_id);
    }
}