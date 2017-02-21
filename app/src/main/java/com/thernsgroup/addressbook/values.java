package com.thernsgroup.addressbook;

public class values {
    private long id;
    private String name;
    private String mobile;
    private String email;

    public values() {
        super();
    }

    public values(String name,String mobile,String email) {
        super();
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }

    public values(long id, String name,String mobile,String email) {
        super();
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setValue(String name,String mobile,String email) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }
}
