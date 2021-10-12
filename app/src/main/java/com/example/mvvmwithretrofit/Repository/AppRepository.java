package com.example.mvvmwithretrofit.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmwithretrofit.InterfaceApi.JsonPlaceHolderApi;
import com.example.mvvmwithretrofit.InterfaceApi.RetrofitInstance;
import com.example.mvvmwithretrofit.Model.Post;
import com.example.mvvmwithretrofit.StaticFields.ResponseData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRepository {

    public static String RESPONSE_ERROR;
    public static int RESPONSE_CODE;

    private static AppRepository appRepository;

    public static AppRepository getInstance(){
        if(appRepository == null){
            appRepository = new AppRepository();
        }
        return appRepository;
    }

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    public AppRepository(){
        jsonPlaceHolderApi = RetrofitInstance.getRetroClient().create(JsonPlaceHolderApi.class);

    }

   public MutableLiveData<List<Post>> getPosts(){

        MutableLiveData<List<Post>> posts = new MutableLiveData<>();
       Call<List<Post>> posts1 = jsonPlaceHolderApi.getPosts();
       posts1.enqueue(new Callback<List<Post>>() {
           @Override
           public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
               if(response.isSuccessful()){
                   posts.setValue(response.body());
               }
           }

           @Override
           public void onFailure(Call<List<Post>> call, Throwable t) {
               posts.setValue(null);
           }
       });
       return posts;


   }



    public MutableLiveData<Post> setPosts(Post post){

        MutableLiveData<Post> posts = new MutableLiveData<>();
        Call<Post> post1 = jsonPlaceHolderApi.createPost(post);
        post1.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                posts.setValue(response.body());
                Log.d("TAG", "onResponse: "+response.code());
                RESPONSE_CODE = response.code();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                RESPONSE_ERROR = t.getMessage();
                posts.setValue(null);

            }
        });

        return posts;

    }


}
