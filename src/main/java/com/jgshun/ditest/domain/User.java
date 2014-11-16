/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jgshun.ditest.domain;

/**
 *
 * @author jgshun
 */
public class User {
    
    private int id;
    private String username;
    private String nickname;
    private int type;
    private String password;

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", nickname=" + nickname + ", type=" + type + '}';
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
