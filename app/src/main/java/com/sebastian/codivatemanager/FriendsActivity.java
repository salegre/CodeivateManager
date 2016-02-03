package com.sebastian.codivatemanager;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FriendsActivity extends ListActivity {

    @Bind(R.id.listFriends)
    ListView mListView;

    private final String mFileName = "FriendsData";
    private User[] mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra("MAMBO");
        mUsers = Arrays.copyOf(parcelables, parcelables.length, User[].class);

        FriendAdapter adapter = new FriendAdapter(this, mUsers);
        setListAdapter(adapter);

    }

    @OnClick(R.id.buttonPlus)
    public void startPlus() {
        Intent intent = new Intent(this, AddFriendActivity.class);
        startActivity(intent);
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

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
