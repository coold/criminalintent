package ru.coold.criminalintent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by rz on 08.02.2015.
 */
public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    @Override
    public String toString() {
        return mTitle;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public String getSimpleDate(){
        return dateFormat.format(mDate);
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
