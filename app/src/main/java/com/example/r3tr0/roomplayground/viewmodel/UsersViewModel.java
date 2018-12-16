package com.example.r3tr0.roomplayground.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.r3tr0.roomplayground.model.dao.UserDao;
import com.example.r3tr0.roomplayground.model.database.UsersDatabase;
import com.example.r3tr0.roomplayground.model.ropositories.UsersRepository;

public class UsersViewModel extends AndroidViewModel {
    private UsersRepository repository;

    public UsersViewModel(@NonNull Application application) {
        super(application);
        UsersDatabase database = UsersDatabase.getInstance(application);
        repository = new UsersRepository(application);
    }

    public UsersRepository getRepository() {
        return repository;
    }
}
