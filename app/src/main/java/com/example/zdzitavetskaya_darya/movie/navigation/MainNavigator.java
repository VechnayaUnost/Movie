package com.example.zdzitavetskaya_darya.movie.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.MovieFragment;

public class MainNavigator {

    FragmentManager fragmentManager;
    int resid;

    public MainNavigator(FragmentManager fragmentManager, int resid){
        this.fragmentManager = fragmentManager;
        this.resid = resid;
    }

    public void firstTabNavigatin(String screenName){
        //swith for screen name
             fragmentManager.beginTransaction().replace(resid,new MovieFragment()).commit();

    }
}
