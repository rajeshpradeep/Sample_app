package com.example.structure.data.daos;

import com.example.structure.data.models.CreateAccountParamModel;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Created by Rajesh Pradeep G on 16-12-2019
 */
@Dao
public interface CreateAccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createAccount1(CreateAccountParamModel createAccountParamModel);

    @Query("UPDATE CreateAccountParamModel SET year= :year, make= :make, model= :model WHERE email = :email")
    void updateCreateAccount2(String year, String make, String model, String email);

    @Query("UPDATE CreateAccountParamModel SET is_fully_charged= :is_fully_charged, " +
            "is_unexpectedly_interrupted= :is_unexpectedly_interrupted, is_notify_to_plugin_mobile= :is_notify_to_plugin_mobile, " +
            "notify_plugin_time= :notify_plugin_time, user_datetime= :user_datetime, user_timezone= :user_timezone WHERE email = :email")
    void updateCreateAccount3(String is_fully_charged, String is_unexpectedly_interrupted, String is_notify_to_plugin_mobile,
            String notify_plugin_time, String user_datetime, String user_timezone, String email);

    @Query("SELECT * FROM CreateAccountParamModel")
    CreateAccountParamModel getCreateAccountDetails();

    @Query("DELETE FROM CreateAccountParamModel")
    void deleteCreateAccountDetails();
}
