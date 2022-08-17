package me.khjk.domain;

public class Member {
    public long Id;
    public String Email;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
