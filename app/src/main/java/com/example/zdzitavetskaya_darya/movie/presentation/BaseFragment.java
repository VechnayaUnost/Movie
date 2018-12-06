package com.example.zdzitavetskaya_darya.movie.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;

public abstract class BaseFragment extends MvpAppCompatFragment {

    public abstract RecyclerView getRecycler();

    public abstract int getLayoutFragment();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayoutFragment(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        getRecycler().setLayoutManager(layoutManager);

        final MoviesAdapter adapter = new MoviesAdapter(new ArrayList<>());
        getRecycler().setAdapter(adapter);
    }
}
