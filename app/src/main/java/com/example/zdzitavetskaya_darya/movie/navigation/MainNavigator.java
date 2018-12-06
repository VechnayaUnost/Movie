package com.example.zdzitavetskaya_darya.movie.navigation;

import android.support.v4.app.FragmentManager;

import com.example.zdzitavetskaya_darya.movie.constants.ScreenConstants;
import com.example.zdzitavetskaya_darya.movie.presentation.favouritePresentation.FavouriteFragment;
import com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.MovieFragment;
import com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.UpcomingMovieFragment;

public final class MainNavigator {

    private final FragmentManager fragmentManager;
    private final int resId;

    public MainNavigator(final FragmentManager fragmentManager, final int resId){
        this.fragmentManager = fragmentManager;
        this.resId = resId;
    }

    public void bottomNavigation(final String screenName){
        //switch for screen name
             switch (screenName) {
                 case ScreenConstants.TRENDS_SCREEN: fragmentManager.beginTransaction().replace(resId, new MovieFragment()).commit();
                            break;
                 case ScreenConstants.UPCOMING_SCREEN: fragmentManager.beginTransaction().replace(resId, new UpcomingMovieFragment()).commit();
                            break;
                 case ScreenConstants.FAVOURITE_SCREEN: fragmentManager.beginTransaction().replace(resId, new FavouriteFragment()).commit();
                            break;
             }
    }
}
