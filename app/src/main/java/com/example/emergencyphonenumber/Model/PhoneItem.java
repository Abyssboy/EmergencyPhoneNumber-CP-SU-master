package com.example.emergencyphonenumber.Model;

import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by poome on 11/26/2017.
 */

public class PhoneItem {
    public final int Id;
    public final String Title;
    public final String Number;
    public final String Pic;

    public PhoneItem(int id, String title, String number, String pic) {
        Id = id;
        Title = title;
        Number = number;
        Pic = pic;
    }

    @Override
    public String toString() { //control+o
        return Title;
    }
}
