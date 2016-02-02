package com.sebastian.codivatemanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.textViewName)
    TextView name;
    @Bind(R.id.textViewLevelLabel)
    TextView levelLabel;
    @Bind(R.id.textViewLevel)
    TextView level;
    @Bind(R.id.buttonFriends)
    Button friends;

    private final String mFileName = "UserData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if(FirstRun.isFirstRun(this)) {
            Intent intent = new Intent(this, SetupActivity.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(!FirstRun.isFirstRun(this))
        updateDisplay();
    }

    @OnClick(R.id.buttonFriends)
    public void intentFriends() {
        Intent intent = new Intent(this, FriendsActivity.class);
        startActivity(intent);
    }

    //Checks for file existence
    public boolean fileNotExists(Context context, String filename) {
        File file = context.getFileStreamPath(filename);
        if(file == null || !file.exists()) {
            return true;
        }
        return false;
    }

    //Updates the UI
    public void updateDisplay() {
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput(mFileName),"UTF-8"));
            String inputString;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputString = inputReader.readLine()) != null) {
                stringBuffer.append(inputString + "\n");
            }

            String JData = stringBuffer.toString();
            JSONObject obj = new JSONObject(JData);
            name.setText(obj.getString("name"));
            level.setText(obj.getString("level"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
