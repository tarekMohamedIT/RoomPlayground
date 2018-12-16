package com.example.r3tr0.roomplayground.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.r3tr0.roomplayground.model.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Query("SELECT * FROM users WHERE firstName= :firstName")
    List<User> getUsersByFirstName(String firstName);

    @Query("SELECT * FROM users WHERE lastName= :lastName")
    List<User> getUsersByLastName(String lastName);

    @Query("SELECT * FROM users WHERE age BETWEEN :minAge AND :maxAge")
    List<User> getUsersInAgeRange(int minAge, int maxAge);

    @Delete
    void delete(User user);

}
