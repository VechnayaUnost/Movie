package com.example.zdzitavetskaya_darya.movie.extensions;

import android.annotation.SuppressLint;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

    public static String getFormatDate(String date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (date != null) {
            try {
                Date newDate = dateFormat.parse(date);
                return DateFormat.format("d MMMM y", newDate).toString();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return date;
    }
}
