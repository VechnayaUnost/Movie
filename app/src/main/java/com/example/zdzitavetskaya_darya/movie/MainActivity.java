package com.example.zdzitavetskaya_darya.movie;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import com.example.zdzitavetskaya_darya.movie.constants.ScreenConstants;
import com.example.zdzitavetskaya_darya.movie.navigation.MainNavigator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    private MainNavigator navigator;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            navigator = new MainNavigator(getSupportFragmentManager(), R.id.main_container_frame_layout);
            navigator.bottomNavigation(ScreenConstants.TRENDS_SCREEN);
        }

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navigation_trends:
                    navigator.bottomNavigation(ScreenConstants.TRENDS_SCREEN);
                    return true;
                case R.id.navigation_upcoming:
                    navigator.bottomNavigation(ScreenConstants.UPCOMING_SCREEN);
                    return true;
                case R.id.navigation_favourites:
                    navigator.bottomNavigation(ScreenConstants.FAVOURITE_SCREEN);
                    return true;
            }
            return false;
        });
    }
}
