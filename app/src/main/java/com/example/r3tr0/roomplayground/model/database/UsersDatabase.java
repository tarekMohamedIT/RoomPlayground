package com.example.r3tr0.roomplayground.model.database;

import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.r3tr0.roomplayground.model.dao.UserDao;
import com.example.r3tr0.roomplayground.model.models.User;

@Database(entities = {User.class},
        version = 1,
        exportSchema = false)
public abstract class UsersDatabase extends RoomDatabase {
    private static UsersDatabase INSTANCE;

    public static UsersDatabase getInstance(Context context){
        if (INSTANCE == null) {
            synchronized (User.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UsersDatabase.class, "users_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract UserDao getDao();
}
