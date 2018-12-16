package com.example.r3tr0.roomplayground.model.ropositories;

import android.app.Application;

import com.example.r3tr0.roomplayground.model.RoomLiveData;
import com.example.r3tr0.roomplayground.model.dao.UserDao;
import com.example.r3tr0.roomplayground.model.database.UsersDatabase;
import com.example.r3tr0.roomplayground.model.models.User;

import java.util.List;

public class UsersRepository {
    private UserDao dao;
    private RoomLiveData<List<User>> allUsers;
    private RoomLiveData<List<User>> usersByFirstName;

    public UsersRepository(Application application){
        UsersDatabase database = UsersDatabase.getInstance(application);
        dao = database.getDao();

    }

    public RoomLiveData<List<User>> getAllUsers() {
        if (allUsers == null)
            allUsers = new RoomLiveData<>();
        synchronized (UserDao.class) {
            allUsers.runAsync(() -> dao.getAllUsers());
        }
        return allUsers;
    }

    public RoomLiveData<List<User>> getUsersByFirstName(String firstName) {
        if (usersByFirstName == null){
            usersByFirstName = new RoomLiveData<>();
        }

        synchronized (UserDao.class){
            usersByFirstName.runAsync(() -> dao.getUsersByFirstName(firstName));
        }

        return usersByFirstName;
    }

    public void addUser(User user){
        synchronized (UserDao.class) {
            new RoomLiveData<>().runAsync(() -> {
                dao.insert(user);
                return null;
            });
        }
    }
}
