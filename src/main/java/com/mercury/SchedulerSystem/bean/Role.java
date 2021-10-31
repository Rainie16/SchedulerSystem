package com.mercury.SchedulerSystem.bean;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "Scheduler_Role")
public class Role implements GrantedAuthority {

    @Id
    private int id;

    @Column
    private String type;

    public Role() {
    }

    public Role(int id) {
        this.id = id;
    }

    public Role(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return type;
    }

    public void setRole(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + type + '\'' +
                '}';
    }

    @Override
    public String getAuthority() {
        return type;
    }
}
