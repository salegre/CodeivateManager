package com.sebastian.codivatemanager;

/**
 * Created by Sebastian on 29/01/2016.
 */
public class User {
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
}
