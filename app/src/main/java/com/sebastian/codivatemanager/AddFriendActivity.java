package com.sebastian.codivatemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;

public class AddFriendActivity extends AppCompatActivity {

    @Bind(R.id.textViewAddFriend)
    TextView title;
    @Bind(R.id.editTextFriendUsername)
    TextView friendUsername;
    @Bind(R.id.buttonAdd)
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
    }

    @OnClick(R.id.buttonAdd)
    public void addFriend() {

    }
}
