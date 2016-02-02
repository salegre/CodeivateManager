package com.sebastian.codivatemanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetupActivity extends AppCompatActivity {

    @Bind(R.id.textViewTitle)
    TextView title;
    @Bind(R.id.textViewHeading)
    TextView heading;
    @Bind(R.id.editTextUsername)
    TextView username;
    @Bind(R.id.buttonStart)
    Button start;

    private final String mFileName = "UserData";

    HandleUser uHandler = new HandleUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonStart)
    public void Start() {
        String user = username.getText().toString();
        try {
            if(isNetworkAvailable()) {
                uHandler.setJsonData(user, mFileName);
                while(uHandler == null){
                }
                addToFile();
            } else {
                Toast.makeText(this, "Network Unavailable", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add a file in the Internal Storage
    private void addToFile() {
        String fileName = mFileName;
        FileOutputStream outputStream = null;
        try {
            outputStream = openFileOutput(fileName, MODE_PRIVATE);
            outputStream.write(uHandler.getJsonData().getBytes());
            outputStream.close();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Looks for network connection
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }
}