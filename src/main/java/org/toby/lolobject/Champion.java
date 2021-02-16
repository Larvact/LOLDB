package org.toby.lolobject;

import java.util.List;

public class Champion implements Comparable<Champion> {

    private long id;
    private String name;
    private String title;
    private List<String> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public int compareTo(Champion o) {
        return this.name.compareTo(o.getName());
    }
}
