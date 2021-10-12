package com.example.mvvmwithretrofit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvvmwithretrofit.Model.Post;
import com.example.mvvmwithretrofit.Repository.AppRepository;
import com.example.mvvmwithretrofit.StaticFields.ResponseData;
import com.example.mvvmwithretrofit.ViewModel.MainActivityViewModel;
import com.example.mvvmwithretrofit.ViewModel.SendDataViewModel;

public class SendDataActivity extends AppCompatActivity {

    private EditText titleEdt, textEdt;
    private Button postDataBtn,getDataBtn;
    private TextView responseTV;
    Post post;
    SendDataViewModel sendDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);

        titleEdt = findViewById(R.id.idEdtTitle);
        textEdt = findViewById(R.id.idEdtText);
        postDataBtn = findViewById(R.id.idBtnPost);
        responseTV = findViewById(R.id.idTVResponse);
        getDataBtn = findViewById(R.id.idBtnGet);


        if(!isNetworkAvailable()){
            //Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Closing the App")
                    .setMessage("No Internet Connection,check your settings")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }

                    })
                    .show();
        }
/*        initVeiwModel();*/




        getDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SendDataActivity.this,MainActivity.class));
            }
        });


        postDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (titleEdt.getText().toString().isEmpty() && textEdt.getText().toString().isEmpty()) {
                    Toast.makeText(SendDataActivity.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to post the data and passing our name and job.
                postData(titleEdt.getText().toString(), textEdt.getText().toString());
            }
        });


    }

    private void postData(String title, String body) {
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        sendDataViewModel = new ViewModelProvider(this, factory).get(SendDataViewModel.class);
        Post postnew = new Post(1,title,body);
        //sendDataViewModel.init(postnew);
        sendDataViewModel.init(postnew).observe(this, new Observer<Post>()
        {
            @Override
            public void onChanged(Post post) {
                if(post!=null){
                    Toast.makeText(SendDataActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();


                    titleEdt.setText("");
                    textEdt.setText("");
                    String responseString = "Response Code : " + AppRepository.RESPONSE_CODE + "\nTitle : " + post.getTitle() + "\n" + "Body : " + post.getBody();

                    // below line we are setting our
                    // string to our text view.
                    responseTV.setText(responseString);
                }else{
                    titleEdt.setText("");
                    textEdt.setText("");
                    responseTV.setText(AppRepository.RESPONSE_ERROR);
                }
            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}