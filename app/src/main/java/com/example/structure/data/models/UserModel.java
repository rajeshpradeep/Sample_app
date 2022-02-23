package com.example.structure.data.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Rajesh Pradeep G on 14-10-2019
 */
@Entity
public class UserModel {

    @PrimaryKey
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
