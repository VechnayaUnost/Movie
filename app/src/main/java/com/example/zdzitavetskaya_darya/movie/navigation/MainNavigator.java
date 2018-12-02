package com.example.zdzitavetskaya_darya.movie.navigation;

import android.support.v4.app.FragmentManager;

import com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.MovieFragment;
import com.example.zdzitavetskaya_darya.movie.constants.Constants;
import com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.UpcomingMovieFragment;

public class MainNavigator {

    private FragmentManager fragmentManager;
    private int resId;

    public MainNavigator(FragmentManager fragmentManager, int resId){
        this.fragmentManager = fragmentManager;
        this.resId = resId;
    }

    public void bottomNavigation(String screenName){
        //switch for screen name
             switch (screenName) {
                 case Constants.TRENDS_SCREEN: fragmentManager.beginTransaction().replace(resId, new MovieFragment()).commit();
                            break;
                 case Constants.UPCOMING_SCREEN: fragmentManager.beginTransaction().replace(resId, new UpcomingMovieFragment()).commit();
                            break;
             }
    }
}
