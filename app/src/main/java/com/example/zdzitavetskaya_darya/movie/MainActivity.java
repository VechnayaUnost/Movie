package com.example.zdzitavetskaya_darya.movie;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.MovieFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    private FragmentTransaction fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            fragment = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container_frame_layout, new MovieFragment());
            fragment.commit();
        }

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navigation_trends:
                    fragment = getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_container_frame_layout, new MovieFragment());
                    fragment.commit();
                    return true;
                case R.id.navigation_upcoming:
                    mTextMessage.setText(R.string.title_upcoming);
                    return true;
                case R.id.navigation_favourites:
                    mTextMessage.setText(R.string.title_favourites);
                    return true;
            }
            return false;
        });

        mTextMessage = findViewById(R.id.message);

    }

}
