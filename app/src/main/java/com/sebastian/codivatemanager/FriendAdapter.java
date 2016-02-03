package com.sebastian.codivatemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Sebastian on 03/02/2016.
 */
public class FriendAdapter extends BaseAdapter {

    private Context mContext;
    private User[] mUsers;

    public FriendAdapter(Context context, User[] users) {
        mContext = context;
        mUsers = users;
    }

    @Override
    public int getCount() {
        return mUsers.length;
    }

    @Override
    public Object getItem(int position) {
        return mUsers[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            //brand new
            convertView = LayoutInflater.from(mContext).inflate(R.layout.user_overview, null);
            holder = new ViewHolder();
            holder.nameLabel = (TextView) convertView.findViewById(R.id.textViewName);
            holder.levelLabel = (TextView) convertView.findViewById(R.id.textViewLevel);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        User user = mUsers[position];

        holder.nameLabel.setText(user.getName());
        holder.levelLabel.setText(user.getLevel() + "");

        return null;
    }

    private static class ViewHolder {
        TextView nameLabel;
        TextView levelLabel;
    }
}
