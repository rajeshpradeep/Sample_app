package com.example.structure.data.database;

import com.example.structure.data.daos.CreateAccountDao;
import com.example.structure.data.models.CreateAccountParamModel;
import com.example.structure.data.models.UserModel;
import com.example.structure.utils.Converters;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


/**
 * Created by Rajesh Pradeep G on 14-10-2019
 */
@Database(entities = {UserModel.class, CreateAccountParamModel.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class LocalDatabase extends RoomDatabase {
    public abstract CreateAccountDao getCreateAccountDao();
}
