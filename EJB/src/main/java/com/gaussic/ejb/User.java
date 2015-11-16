package com.gaussic.ejb;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Cole Fu on 2015/11/16.
 */
@Entity
@Table(name = "DB_USER")
public class User implements Serializable{
    private int C_ID;
    private String C_NAME;
    private String C_PASSWORD;

    @Id
    @Column(name = "C_ID", nullable = false)
    public int getId() {
        return C_ID;
    }

    public void setId(int C_ID) {
        this.C_ID = C_ID;
    }

    @Basic
    @Column(name = "C_NAME", nullable = true, length = 45)
    public String getName() {
        return C_NAME;
    }

    public void setName(String C_NAME) {
        this.C_NAME = C_NAME;
    }

    @Basic
    @Column(name = "C_PASSWORD", nullable = true, length = 45)
    public String getPwd() {
        return C_PASSWORD;
    }

    public void setPwd(String C_PASSWORD) {
        this.C_PASSWORD = C_PASSWORD;
    }

}