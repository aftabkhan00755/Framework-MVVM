package com.example.mvvmwithretrofit.ViewModel;

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

public class SendDataViewModel extends ViewModel {

    private MutableLiveData<Post> postList;

    private AppRepository appRepository;

    public LiveData<Post> init(Post post){
/*        if(postList!=null){
            return;
        }else {
            appRepository = AppRepository.getInstance();
            postList = appRepository.setPosts(post);
        }*/

        appRepository = AppRepository.getInstance();
        postList = appRepository.setPosts(post);
        return postList;
    }

//    public LiveData<Post> SetPostRepository(){
//        return postList;
//    }



/*    public static int RESPONSE_CODE;
    public static String ERROR_MESSAGE;

   public SendDataViewModel(){
       postList = new MutableLiveData<>();
   }

    public MutableLiveData<Post> getMoviesListObserver(){
        return postList;
    }

    public void MakeApiCall(Post post){

        JsonPlaceHolderApi jsonPlaceHolderApi = RetrofitInstance.getRetroClient().create(JsonPlaceHolderApi.class);
        Call<Post> post1 = jsonPlaceHolderApi.createPost(post);
        post1.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                postList.postValue(response.body());
                RESPONSE_CODE = response.code();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                postList.postValue(null);
                ERROR_MESSAGE = t.getMessage();
            }
        });

    }*/

}
