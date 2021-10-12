package com.example.mvvmwithretrofit.InterfaceApi;

import com.example.mvvmwithretrofit.Model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {


    @GET("posts")
    Call<List<Post>> getPosts();

    @POST("/posts")
    @FormUrlEncoded
    Call<Post> savePost(@Field("title") String title,
                        @Field("body") String body,
                        @Field("userId") long userId);

    @POST("/posts")
    Call<Post> createPost(@Body Post post);

}
