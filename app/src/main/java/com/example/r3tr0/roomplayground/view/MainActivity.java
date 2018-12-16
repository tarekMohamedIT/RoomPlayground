package com.example.r3tr0.roomplayground.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.r3tr0.roomplayground.R;
import com.example.r3tr0.roomplayground.model.models.User;
import com.example.r3tr0.roomplayground.viewmodel.UsersViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    UsersViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(UsersViewModel.class);

        LiveData<List<User>> liveData = viewModel.getRepository().getUsersByFirstName("Tarek").getLiveData();

        liveData.observe(this, users ->{
            if (users != null && users.size() > 0)
                Log.e("TEST", "Name of the first user : " + users.get(0).getFirstName());

            else
                Log.e("TEST", "The list is empty");

        });
    }

    public void onClick(View v){
        if (v.getId() == R.id.button)
            viewModel.getRepository().getUsersByFirstName("Ayman");

        else
            viewModel.getRepository().getUsersByFirstName("Tarek");

    }
}
