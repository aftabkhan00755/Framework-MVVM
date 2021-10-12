package com.example.mvvmwithretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mvvmwithretrofit.Model.Post;
import com.example.mvvmwithretrofit.ViewModel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    List<Post> postList;
    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.text_view_result);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        mainActivityViewModel = new ViewModelProvider(this, factory).get(MainActivityViewModel.class);
        mainActivityViewModel.init().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {

                if(posts != null){

                    for(Post post: posts){
                        String content = "";
                        content += "ID: " + post.getId() + "\n";
                        content += "User ID: " + post.getUserId() + "\n";
                        content += "Title: " + post.getTitle() + "\n";
                        content += "Body: " + post.getBody() + "\n\n";

                        textView.append(content);
                    }
                }

            }
        });

    }


}