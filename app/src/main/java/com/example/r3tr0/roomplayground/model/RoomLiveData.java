package com.example.r3tr0.roomplayground.model;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Handler;

public class RoomLiveData<T> {
    private MutableLiveData<T> mutableLiveData;

    public RoomLiveData(){
        mutableLiveData = new MutableLiveData<>();
    }

    public void observe(LifecycleOwner owner, Observer<T> observer){
        mutableLiveData.observe(owner, observer);
    }

    public MutableLiveData<T> getLiveData() {
        return mutableLiveData;
    }

    public void runAsync(RoomMethod<T> method){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mutableLiveData.postValue(method.execute());
            }
        }).start();
    }

    public interface RoomMethod<T>{
        T execute();
    }
}
