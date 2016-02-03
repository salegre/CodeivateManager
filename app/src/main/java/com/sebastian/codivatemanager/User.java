package com.sebastian.codivatemanager;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sebastian on 29/01/2016.
 */
public class User implements Parcelable{
    private String mName;
    private double mLevel;
    private double mFocusLevel;
    private int mFocusPoints;
    private int mMaxStreak;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public double getLevel() {
        return mLevel;
    }

    public void setLevel(double level) {
        mLevel = level;
    }

    public double getFocusLevel() {
        return mFocusLevel;
    }

    public void setFocusLevel(double focusLevel) {
        mFocusLevel = focusLevel;
    }

    public int getFocusPoints() {
        return mFocusPoints;
    }

    public void setFocusPoints(int focusPoints) {
        mFocusPoints = focusPoints;
    }

    public int getMaxStreak() {
        return mMaxStreak;
    }

    public void setMaxStreak(int maxStreak) {
        mMaxStreak = maxStreak;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeDouble(mLevel);
        dest.writeDouble(mFocusLevel);
        dest.writeInt(mFocusPoints);
        dest.writeInt(mMaxStreak);
    }

    private User(Parcel in) {
        mName = in.readString();
        mLevel = in.readDouble();
        mFocusLevel = in.readDouble();
        mFocusPoints = in.readInt();
        mMaxStreak = in.readInt();
    }

    public User() { }

    private static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
