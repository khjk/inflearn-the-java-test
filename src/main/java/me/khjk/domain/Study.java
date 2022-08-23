package me.khjk.domain;

import me.khjk.StudyStatus;

import java.time.LocalDateTime;
import java.util.Optional;

public class Study {
    public Member owner;
    private Long id;
    private StudyStatus status = StudyStatus.DRAFT;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudyStatus getStatus() {
        return status;
    }

    public void setStatus(StudyStatus status) {
        this.status = status;
    }

    public int getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(int limitCount) {
        this.limitCount = limitCount;
    }

    public LocalDateTime getOpenedDateTime() {
        return openedDateTime;
    }

    public void setOpenedDateTime(LocalDateTime openedDateTime) {
        this.openedDateTime = openedDateTime;
    }

    public int limitCount;
    public String name;
    private LocalDateTime openedDateTime;

    public Study(int limit, String name) {
        this.limitCount = limit;
        this.name = name;
    }

    public Study(int limit) {
        if(limit < 0) {
            throw new IllegalArgumentException("Limit은 0보다 커야한다.");
        }
        this.limitCount = limit;
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


    public void open() {
        this.openedDateTime = LocalDateTime.now();
        this.status = StudyStatus.DRAFT;
    }
}
