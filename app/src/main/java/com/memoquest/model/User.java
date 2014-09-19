package com.memoquest.model;

/**
 * Created by fdemarle on 18/09/2014.
 */
public class User {

    private Integer idAi;
    private Integer id;
    private String email;
    private String password;

    public User(){}

    public User(Integer idAi, String email, String password) {
        this.idAi = idAi;
        this.email = email;
        this.password = password;
    }

    public Integer getIdAi() {
        return idAi;
    }

    public void setIdAi(Integer idAi) {
        this.idAi = idAi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

