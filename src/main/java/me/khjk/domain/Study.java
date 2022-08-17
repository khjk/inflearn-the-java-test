package me.khjk.domain;

public class Study {
    public Member owner;
    public int limit;
    public String name;

    public Study(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }
}
