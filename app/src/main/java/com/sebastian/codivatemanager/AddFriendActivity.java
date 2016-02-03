package com.sebastian.codivatemanager;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AddFriendActivity extends AppCompatActivity {

    @Bind(R.id.textViewAddFriend)
    TextView title;
    @Bind(R.id.editTextFriendUsername)
    TextView friendUsername;

    private final String mFileName = "FriendsData";
    private final OkHttpClient client = new OkHttpClient();
    private String mJsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
    }

    @OnClick(R.id.buttonAdd)
    public void addFriend() {
        String user = friendUsername.getText().toString();
        try {
            if(isNetworkAvailable()) {
                setJsonData(user, mFileName);
                while(mJsonData == null){
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
            Log.i("SetupActivity", "Tries adding to file");
            outputStream = openFileOutput(fileName, MODE_PRIVATE);
            outputStream.write(mJsonData.getBytes());
            outputStream.close();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
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

    //Request JSONData from website
    public void setJsonData(String user, final String fileName) throws Exception {
        Request request = new Request.Builder()
                .url("http://codeivate.com/users/" + user + ".json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }
                mJsonData = response.body().string();
                Log.i("SetupActivity", "Set JSon Data");
            }
        });
    }
}
