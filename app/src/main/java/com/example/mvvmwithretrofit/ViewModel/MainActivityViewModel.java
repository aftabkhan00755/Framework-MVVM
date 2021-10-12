package com.example.mvvmwithretrofit.ViewModel;


import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmwithretrofit.InterfaceApi.JsonPlaceHolderApi;
import com.example.mvvmwithretrofit.InterfaceApi.RetrofitInstance;
import com.example.mvvmwithretrofit.Model.Post;
import com.example.mvvmwithretrofit.Repository.AppRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivityViewModel extends ViewModel {

    public MutableLiveData<List<Post>>  postList;
    private AppRepository appRepository;

    public MutableLiveData<List<Post>> init(){

        appRepository = AppRepository.getInstance();
        postList = appRepository.getPosts();
        return postList;


    }

}
