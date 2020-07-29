package com.tuoniao.sijibao.model;

import java.io.Serializable;

/**
 * @author zhangshuai
 */
public class UserDataBean implements Serializable {
    private String firstName;
    private String lastName;

    public UserDataBean() {
    }

    public UserDataBean(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
